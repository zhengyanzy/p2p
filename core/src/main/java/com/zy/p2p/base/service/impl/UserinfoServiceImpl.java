package com.zy.p2p.base.service.impl;

import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.mapper.UserinfoMapper;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.BitStatesUtils;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserinfoServiceImpl implements IUserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 创建用户信息
     */
    @Override
    public void add(Userinfo userinfo) {
        this.userinfoMapper.insert(userinfo);
    }

    /**
     * 获取用户信息
     */
    @Override
    public Userinfo get(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取当前登录用户的用户信息
     */
    @Override
    public Userinfo getUserinfo() {
        return get(UserContext.getCurrent().getId());
    }

    /**
     * 更新用户信息(乐观锁控制)
     */
    @Override
    public void update(Userinfo userinfo) {
        int ret = userinfoMapper.updateByPrimaryKey(userinfo);
        if (ret == 0) {
            throw new RuntimeException("乐观锁失败,Userinfo:" + userinfo.getId());
        }
    }

    /**
     * 更新用户基本数据
     */
    @Override
    public void updateBasicInfo(Userinfo userinfo) {
        Userinfo old = userinfoService.getUserinfo();
        // 拷贝这次要修改的内容
        old.setEducationBackground(userinfo.getEducationBackground());
        old.setHouseCondition(userinfo.getHouseCondition());
        old.setIncomeGrade(userinfo.getIncomeGrade());
        old.setKidCount(userinfo.getKidCount());
        old.setMarriage(userinfo.getMarriage());
        old.setPhoneNumber(userinfo.getPhoneNumber());

        // 判断用户是否绑定了基本信息
        if (!old.getIsBasicInfo()) {
            old.addState(BitStatesUtils.OP_BASIC_INFO);
        }
        this.update(old);
    }

    /**
     * 用于用户的autcomplate
     * 返回的MAP:{id:logininfoId,username:username}
     */
    @Override
    public List<Map<String, Object>> autoComplate(String keyword) {
        return this.userinfoMapper.autocomplate(keyword);
    }

}
