package com.zy.p2p.base.utils;

import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.vo.VerifyCodeVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserContext {

    public static final String USER_IN_SESSION = "logininfo";
    public static final String VERIFYCODE_IN_SESSION = "verifycode_in_session";

    /**
     * 反向获取request的方法,请查看RequestContextListener.requestInitialized打包过程
     * @return
     */
    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 将用户信息添加到session中
     */
    public static void putCurrent(Logininfo current) {
        // 得到session,并把current放到session中
        getSession().setAttribute(USER_IN_SESSION, current);
    }

    /**
     * 获取session用户信息
     */
    public static Logininfo getCurrent() {
        return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
    }


    /**
     *将生成的验证码存放到session中
     */
    public static void putVerifyCode(VerifyCodeVO vc) {
        getSession().setAttribute(VERIFYCODE_IN_SESSION, vc);
    }

    /**
     * 得到当前的短信验证码
     * @return
     */
    public static VerifyCodeVO getCurrentVerifyCode() {
        return (VerifyCodeVO) getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }

    public static void logout(){
        getSession().invalidate();
    }
}