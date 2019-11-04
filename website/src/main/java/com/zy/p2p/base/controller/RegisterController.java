package com.zy.p2p.base.controller;


import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.service.LogininfoService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class RegisterController {

    @Autowired
    private LogininfoService logininfoService;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResult register(Logininfo logininfo){
        Boolean register = logininfoService.register(logininfo.getUsername(), logininfo.getPassword());
        if (register==false){
            return new JsonResult(false,"用户名已经存在");
        }
        return new JsonResult(true,"用户名注册成功");
    }

    @ResponseBody
    @RequestMapping(value = "/CheckUsername",method = RequestMethod.POST)
    public Boolean CheckUsername(String username){

        Boolean aBoolean = logininfoService.CheckUsername(username);
        return aBoolean;
    }
}