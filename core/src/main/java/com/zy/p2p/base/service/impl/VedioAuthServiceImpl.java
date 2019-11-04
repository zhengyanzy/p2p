package com.zy.p2p.base.service.impl;

import java.util.Date;
import java.util.List;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.domain.VedioAuth;
import com.zy.p2p.base.mapper.VedioAuthMapper;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.VedioAuthQueryObject;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.IVedioAuthService;
import com.zy.p2p.base.utils.BitStatesUtils;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VedioAuthServiceImpl implements IVedioAuthService {

    @Autowired
    private VedioAuthMapper vedioAuthMapper;

    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 分页查询
     */
    @Override
    public PageResult query(VedioAuthQueryObject qo) {
        int count = this.vedioAuthMapper.queryForCount(qo);
        if (count > 0) {
            List<VedioAuth> list = this.vedioAuthMapper.query(qo);
            return new PageResult(list, count, qo.getCurrentPage(),
                    qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }

    /**
     * 视频审核
     */
    @Override
    public void audit(Long loginInfoValue, String remark, int state) {
        // 判断用户没有视频认证
        Userinfo user = this.userinfoService.get(loginInfoValue);
        //user不为空,并且未视频认证
        if (user != null && !user.getIsVedioAuth()) {

            Logininfo applier = new Logininfo();
            applier.setId(loginInfoValue);

            // 添加一个视频认证对象,设置相关属性
            VedioAuth va = new VedioAuth();

            va.setApplier(applier);
            va.setApplyTime(new Date());
            va.setAuditor(UserContext.getCurrent());
            va.setAuditTime(new Date());
            va.setRemark(remark);
            va.setState(state);

            //无论审核是否通过都需要添加一条数据,作为记录
            this.vedioAuthMapper.insert(va);

            if (state == VedioAuth.STATE_AUDIT) {
                // 如果状态审核通过,修改用户状态码
                user.addState(BitStatesUtils.OP_VEDIO_AUTH);
                this.userinfoService.update(user);
            }
        }
        else{
            throw new RuntimeException("该用户已经审核完毕,不需要重复审核");
        }
    }

}
