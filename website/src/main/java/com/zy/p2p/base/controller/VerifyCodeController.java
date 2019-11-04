package com.zy.p2p.base.controller;

import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.IVerifyCodeService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证码相关的controller
 *
 * @author Administrator
 *
 */
@Controller
public class VerifyCodeController {

    @Autowired
    private IVerifyCodeService verifyCodeService;


    /**
     * 给指定的phone发送验证码
     */
    @ResponseBody
    @PostMapping("/sendVerifyCode")
    public JsonResult sendVerifyCode(String phoneNumber) {
        JsonResult json = new JsonResult();
        try {
            verifyCodeService.sendVerifyCode(phoneNumber);
        } catch (RuntimeException re) {
            json.setMsg(re.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    /**
     * 绑定手机
     */
    @RequireLogin
    @RequestMapping("bindPhone")
    @ResponseBody
    public JsonResult bindPhone(String phoneNumber, String verifyCode) {
        JsonResult json = new JsonResult();
        try {
            verifyCodeService.bindPhone(phoneNumber, verifyCode);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }
    /**
     * 给指定的email 发送验证码
     */
    @RequireLogin
    @PostMapping("sendEmail")
    @ResponseBody
    public JsonResult sendEmail(String email) {
        JsonResult json = new JsonResult();
        try {
            verifyCodeService.sendVerifyEmail(email);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }
    /**
     * 邮箱绑定
     * 不能添加注解 @RequireLogin
     */
    @RequestMapping("bindEmail")
    public String bindEmail(String key, Model model) {
        try {
            verifyCodeService.bindEmail(key);
            model.addAttribute("success", true);
        } catch (RuntimeException re) {
            model.addAttribute("success", false);
            System.out.println(re.getMessage());
            model.addAttribute("msg", re.getMessage());
        }
        return "checkmail_result";
    }
}
