package com.zy.p2p.base.controller.interceptor;

import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginCheckInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequireLogin methodAnnotation = handlerMethod.getMethodAnnotation(RequireLogin.class);
            //如果不为null,表示方法上添加了注解,我们需要判断当前用户是否登录了
            if (methodAnnotation!=null&&UserContext.getCurrent()==null){
                response.sendRedirect("/website/login.html");
                return false;
            }
        }
        return true;
    }
}
