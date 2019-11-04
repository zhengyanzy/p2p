package com.zy.p2p.base.service;


import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import org.springframework.stereotype.Service;


public interface LogininfoService {

    /**
     *注册用户账号
     */
    public Boolean register(String username, String password);

    /**
     *处理用户名和密码是否正确
     */
    public Boolean login(String username, String password,int userType);

    /**
     *检查用户名是否存在
     */
    public Boolean CheckUsername(String username);

    /**
     * 初始化管理员账号
     */
    void initAdmin();
}

