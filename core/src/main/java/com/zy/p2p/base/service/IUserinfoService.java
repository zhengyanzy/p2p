package com.zy.p2p.base.service;

import com.zy.p2p.base.domain.Userinfo;

import java.util.List;
import java.util.Map;

public interface IUserinfoService {
    /**
     *  创建用户信息
     */
    void add(Userinfo ui);

    /**
     *  获取用户信息
     */
    Userinfo get(Long id);

    /**
     *  获取当前登录用户的用户信息
     */
    Userinfo getUserinfo();


    /**
     *  更新用户信息(乐观锁控制)
     */
    public void update(Userinfo userinfo);

    /**
     *  更新用户基本数据
     */
    void updateBasicInfo(Userinfo userinfo);

    /**
     * 用于用户的autcomplate
     * 返回的MAP:{id:logininfoId,username:username}
     */
    List<Map<String, Object>> autoComplate(String keyword);
}
