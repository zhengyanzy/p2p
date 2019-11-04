package com.zy.p2p.base.service.impl;

import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.mapper.AccountMapper;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 更新用户账号
     */
    @Override
    public void update(Account account) {
        int ret = this.accountMapper.updateByPrimaryKey(account);
        if (ret == 0) {
            throw new RuntimeException("乐观锁失败,Account:" + account.getId());
        }
    }

    /**
     *添加用户账号
     */
    @Override
    public void add(Account account) {
        this.accountMapper.insert(account);
    }

    /**
     *获取用户账号
     */
    @Override
    public Account get(Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        return account;
    }

    /**
     *  获取当前登录账号的登录信息
     */
    @Override
    public Account getAccount() {
        Account account = get(UserContext.getCurrent().getId());
        return account;
    }
}