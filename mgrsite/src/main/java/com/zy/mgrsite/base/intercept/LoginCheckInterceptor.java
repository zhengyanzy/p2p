package com.zy.mgrsite.base.intercept;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Logininfo current = UserContext.getCurrent();
        System.out.println("---------------------");
        if (current==null){
            System.out.println("null");
            response.sendRedirect("/templates/login.html");
            return false;
        }
        return true;
    }
}
