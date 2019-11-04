package com.zy.mock.controller;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @ResponseBody
    @PostMapping("/send")
    public String send(String username,String password,String apikey,String mobile,String content){
        System.out.println("验证用户名和密码成功");
        System.out.println("发送的手机号："+mobile);
        System.out.println("发送的内容"+content);
        return "success";
    }
}

class A{
    public static void main(String[] args) {
    }
}