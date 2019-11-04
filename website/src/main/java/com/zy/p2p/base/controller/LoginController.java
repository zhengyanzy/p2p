package com.zy.p2p.base.controller;


import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.service.LogininfoService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LogininfoService loginInfoService;


    //提供给前台和后台登录
    @ResponseBody
    @PostMapping("/login")
    public JsonResult login(String username, String password) {
        JsonResult json = new JsonResult();
        //前端登录
        Boolean aBoolean = this.loginInfoService.login(username, password,Logininfo.USER_CLIENT);
        if (aBoolean==false){
            return new JsonResult(false,"用户名或密码错误");
        }
        return json;
    }

    @RequireLogin
    @RequestMapping("/logout")
    public String logout() {
        UserContext.logout();
        return "../../login";
    }
}
