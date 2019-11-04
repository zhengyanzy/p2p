package com.zy.p2p.base.service.impl;

import java.util.Properties;

import com.zy.p2p.base.service.IMailService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;

@Service
public class MailServiceImpl implements IMailService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Override
    public void sendMail(String target, String title, String content) {
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            // 设置SMTP服务器地址
            sender.setHost(host);
            // 创建好一个邮件对象
            MimeMessage message = sender.createMimeMessage();
            // 创建一个邮件助手
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            // 通过heloper设置邮件相关内容
            // 设置目标
            helper.setTo(target);
            // 设置from
            //helper.setFrom("Admin@xmg.com"); // 这个地方一定要和properties中的username一样
            // 这里有两个参数,第一个form是用来规定发件人的邮箱地址的,第二个是发件人的名字,可以自定义自定义
            helper.setFrom("1847003070@qq.com", "系统管理员");

            // 设置邮件标题
            helper.setSubject(title);
            // 设置邮件内容
            helper.setText(content, true);

            // 设置发送邮件的账号和密码
            sender.setUsername(username);
            sender.setPassword(password);

            //
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");// 设置发送邮件需要身份认证
            prop.put("mail.smtp.timeout", "25000");// 设置发送超时时间
            sender.setJavaMailProperties(prop);

            // 发送邮件
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送邮件失败!");
        }
    }

}
