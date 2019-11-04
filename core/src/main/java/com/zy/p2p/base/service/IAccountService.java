package com.zy.p2p.base.service;

import com.zy.p2p.base.domain.Account;

public interface IAccountService{

    /**
     *更新用户账号
     */
    public void update(Account account);
    /**
     *添加账号
     */
    void add(Account account);

    /**
     *  获取账号信息
     */
    Account get(Long id);

    /**
     *  获取当前登录账号的账户信息
     */
    Account getAccount();
}
