package com.zy.p2p.base.service.impl;

import com.sun.tracing.dtrace.Attributes;
import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.domain.Iplog;
import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.mapper.IplogMapper;
import com.zy.p2p.base.mapper.LogininfoMapper;
import com.zy.p2p.base.mapper.UserinfoMapper;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.LogininfoService;
import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class LogininfoServiceImpl implements LogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;


    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IplogMapper iplogMapper;

    //用于存放正在注册的用户名的缓存
    private static ConcurrentHashMap<String,Object> userRegisterName= new ConcurrentHashMap<String,Object>();

    //注册功能（注册服务只暴露username和password,如果直接暴露一个对象,用户可能会传入一些Logininfo重要属性值）
    //void register(Logininfo logininfo){};(不安全)
    public Boolean register(String username, String password) {
        if (null == username && null == password) {
            return false;
        }
        //查询该用户名在数据库中是否被注册
        Boolean aBoolean = CheckUsername(username);
        if (aBoolean==false){
            return false;
        }


        //保证并发情况下不会出现用户同时注册的问题
        Object registeringUsername = userRegisterName.get(username);
        if (registeringUsername!=null){
            return false;
        }
        synchronized (logininfoMapper){
            registeringUsername = userRegisterName.get(username);
            if (registeringUsername!=null){
                return false;
            }
            //将正在注册的用户添加到缓存中
            userRegisterName.put(username,new Object());
        }
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername(username);
        logininfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        logininfo.setState(Logininfo.STATE_NORNAL);
        logininfo.setUserType(Logininfo.USER_CLIENT);
        logininfoMapper.insert(logininfo);
        //删除缓存的用户
        userRegisterName.remove(username);

        // 初始化  账户信息和userinfo
        Account account = new Account();
        Userinfo userinfo = new Userinfo();

        account.setId(logininfo.getId());
        this.accountService.add(account);
        userinfo.setId(logininfo.getId());
        this.userinfoService.add(userinfo);
        return true;
    }

    //查询用户是否存在
    public Boolean CheckUsername(String username){
        return !(logininfoMapper.seletCountByUserName(username)>0);
    }

    @Override
    public Boolean login(String username, String password,int userType) {

        //编写日志代理
        //保存登录日志信息
        Iplog iplog = new Iplog();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String remoteAddr = request.getRemoteAddr();
        iplog.setIp(remoteAddr);
        iplog.setLoginTime(new Date());
        iplog.setUserName(username);
        iplog.setUserType(userType);

        //可以直接根据用户名和密码进行查询,可能会不好
        Logininfo logininfo = logininfoMapper.selectByUserNameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()),userType);
        if (logininfo==null){
            iplog.setLoginState(Iplog.STATE_FAILED);
            iplogMapper.insert(iplog);
            return false;
        }
        iplog.setLoginState(Iplog.STATE_SUCCESS);
        iplogMapper.insert(iplog);

        //将用户信息放到UserContext中
        UserContext.putCurrent(logininfo);
        return true;
    }

    @Override
    public void initAdmin() {
        int count = logininfoMapper.countByUserType(Logininfo.USER_MANAGER);
        if(count==0){
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(BidConst.DEFAULT_ADMIN_NAME);
            logininfo.setPassword(DigestUtils.md5DigestAsHex(BidConst.DEFAULT_ADMIN_PASSWORD.getBytes()));
            logininfo.setUserType(0);
            logininfo.setState(Logininfo.USER_MANAGER);
            logininfo.setState(Logininfo.STATE_NORNAL);
            logininfoMapper.insert(logininfo);
        }
    }
}