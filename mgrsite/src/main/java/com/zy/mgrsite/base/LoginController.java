package com.zy.mgrsite.base;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.service.LogininfoService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 后台登陆
 */
@Controller
public class LoginController {

    @Autowired
    private LogininfoService logininfoService;

    /**
     * 后台登陆
     */
    @ResponseBody
    @RequestMapping("login")
    public JsonResult login(String username, String password, HttpServletRequest request) {
        JsonResult json = new JsonResult();
        Boolean aBoolean = this.logininfoService.login(username, password, Logininfo.USER_MANAGER);
        if (aBoolean == false) {
            json.setSuccess(false);
            json.setMsg("用户名或者密码错误!");
        }
        return json;
    }

    /**
     * 后台首页
     */
    @RequestMapping("/index")
    public String index() {
        return "main";
    }
}
