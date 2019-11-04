package com.zy.p2p.base.controller;


import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.query.IplogQueryObject;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IIplogService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class IplogController {
    @Autowired
    private IIplogService iIplogService;

    @RequireLogin
    @GetMapping(value = "/ipLog")
    public String iplogList(@ModelAttribute("qo")IplogQueryObject qo, Model model){
        Logininfo logininfo = UserContext.getCurrent();
        model.addAttribute("logininfo",logininfo);
        return "iplog_list";
    }
    @RequireLogin
    @PostMapping(value = "/iplog_list")
    public String iplog_list(@ModelAttribute("qo")IplogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        qo.setUserType(Logininfo.USER_CLIENT);
        PageResult query = iIplogService.query(qo);
        model.addAttribute("pageResult",query);
        return "list/iplog_list";
    }
}
