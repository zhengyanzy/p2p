package com.zy.p2p.base.controller;

import com.sun.media.sound.MidiOutDeviceProvider;
import com.sun.tracing.dtrace.Attributes;
import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserinfoService userinfoService;

    @RequireLogin
    @GetMapping(value = "/person")
    public String persion(Model model){

        Account account = accountService.getAccount();
        Userinfo userinfo = userinfoService.getUserinfo();

        model.addAttribute("account",account);
        model.addAttribute("userinfo",userinfo);

        return "person";
    }


}
