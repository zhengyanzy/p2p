package com.zy.p2p.base.service.impl;

import com.zy.p2p.base.domain.Mailverify;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.mapper.MailverifyMapper;
import com.zy.p2p.base.mapper.UserinfoMapper;
import com.zy.p2p.base.service.IMailService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.IVerifyCodeService;
import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.base.utils.BitStatesUtils;
import com.zy.p2p.base.utils.DateUtil;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.base.vo.VerifyCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Value("${sms.username}")
    private String username;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.apikey}")
    private String apiKey;

    @Value("${sms.url}")
    private String url;

    @Value("${mail.hostUrl}")
    private String hostUrl;
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private MailverifyMapper mailverifyMapper;
    @Autowired
    private IMailService mailService;

    /**
     * 给指定的phone发送验证码
     */
    @Override
    public void sendVerifyCode(String phoneNumber) {
        //判断当前是否能够发送短信
        //从session中获取最后一次发送短信的时间
        VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
        if (vc == null || DateUtil.secondsBetween(new Date(), vc.getLastSendTime()) > 90) {
            // 正常发送验证码短信
            // 生成一个验证码;
            String verifyCode = UUID.randomUUID().toString().substring(0, 4);
            // 发送短信
            try {
                // 创建一个URL对象
                URL url = new URL(this.url);
                // 通过URL得到一个HTTPURLConnection连接对象;
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 拼接POST请求的内容
                StringBuilder content = new StringBuilder(100)
                        .append("username=").append(username)
                        .append("&password=").append(password)
                        .append("&apikey=").append(apiKey).append("&mobile=")
                        .append(phoneNumber).append("&content=")
                        .append("验证码是:").append(verifyCode).append(",请在5分钟内使用");
                // 发送POST请求,POST或者GET一定要大写
                conn.setRequestMethod("POST");
                // 设置POST请求是有请求体的
                conn.setDoOutput(true);
                // 写入POST请求体
                conn.getOutputStream().write(content.toString().getBytes());
                // 得到响应流(其实就已经发送了)
                String response = StreamUtils.copyToString(
                        conn.getInputStream(), Charset.forName("UTF-8"));
                if (response.startsWith("success")) {
                    // 发送成功
                    // 把手机号码,验证码,发送时间装配到VO中并保存到session
                    vc = new VerifyCodeVO();
                    vc.setLastSendTime(new Date());
                    vc.setPhoneNumber(phoneNumber);
                    vc.setVerifyCode(verifyCode);
                    UserContext.putVerifyCode(vc);
                } else {
                    // 发送失败
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("短信发送失败!");
            }
        } else {
            throw new RuntimeException("发送过于频繁!");
        }
    }

    /**
     * 绑定手机号
     */
    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        // 检查用户是否已经绑定手机号
        Userinfo userinfo = this.getCurrent();
        if (!userinfo.getIsBindPhone()) {
            // 验证验证码和手机号是否提交是否正确
            boolean ret = verify(phoneNumber, verifyCode);
            if (ret) {
                // 如果合法,给用户绑定手机
                userinfo.addState(BitStatesUtils.OP_BIND_PHONE);
                userinfo.setPhoneNumber(phoneNumber);
                userinfoService.update(userinfo);
            } else {
                // 抛出异常;
                throw new RuntimeException("绑定手机失败!");
            }
        } else {
            throw new RuntimeException("该用户已经绑定手机,不允许重复绑定!");
        }
    }

    /**
     * 给指定的Email发送验证码
     */
    @Override
    public void sendVerifyEmail(String email) {
        // 当前用户没有绑定邮箱
        Userinfo userinfo = this.getCurrent();
        if (!userinfo.getIsBindEmail()) {
            String uuid = UUID.randomUUID().toString();
            // 构造一份要发送的邮件
            StringBuilder content = new StringBuilder(100)
                    .append("点击<a href='").append(this.hostUrl)
                    .append("bindEmail.do?key=").append(uuid)
                    .append("'>这里</a>完成邮箱绑定,有效期为")
                    .append(BidConst.VERIFYEMAIL_VAILDATE_DAY).append("天");

            try {
                System.out.println("发送邮件:" + email + "邮件内容:" + content);
                mailService.sendMail(email, "邮箱验证邮件", content.toString());
                // 构造一个MailVerify对象;
                Mailverify mv = new Mailverify();
                mv.setEmail(email);
                mv.setSendDate(new Date());
                mv.setUserinfoId(userinfo.getId());
                mv.setUuid(uuid);
                this.mailverifyMapper.insert(mv);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("验证邮件发送失败!");
            }
        }
    }

    /**
     * 绑定Email
     */
    @Override
    public void bindEmail(String uuid) {
        // 通过uuid得到mailverify对象;
        Mailverify mv = this.mailverifyMapper.selectByUuid(uuid);
        if (mv != null) {
            // 判断用户没有绑定邮箱;
            Userinfo userinfo = this.get(mv.getUserinfoId());
            if (!userinfo.getIsBindEmail()) {
                // 判断有效期;
                if (mv != null
                        && DateUtil
                        .secondsBetween(mv.getSendDate(), new Date()) <= BidConst.VERIFYEMAIL_VAILDATE_DAY * 24 * 3600) {
                    // 修改用户状态码;给用户设置邮箱
                    userinfo.addState(BitStatesUtils.OP_BIND_EMAIL);
                    userinfo.setEmail(mv.getEmail());
                    userinfoService.update(userinfo);
                    return;
                }
            }
        }
        throw new RuntimeException("绑定邮箱失败!");
    }

    public Userinfo get(Long id) {
        return this.userinfoMapper.selectByPrimaryKey(id);
    }

    public Userinfo getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }

    public boolean verify(String phoneNumber, String verifyCode) {
        VerifyCodeVO verifyCodeVO = UserContext.getCurrentVerifyCode();
        //发送了验证码
        if (verifyCodeVO != null
                // 验证手机号
                && verifyCodeVO.getPhoneNumber().equals(phoneNumber)
                // 验证短信验证码
                && verifyCodeVO.getVerifyCode().equalsIgnoreCase(verifyCode)
                // 验证短信验证码是否在有效期
                && DateUtil.secondsBetween(new Date(), verifyCodeVO.getLastSendTime()) <= BidConst.VERIFYCODE_VAILDATE_SECOND) {
            return true;
        }
        return false;
    }
}
