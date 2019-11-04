package com.zy.p2p.business.service.impl;

import java.util.Date;
import java.util.List;

import com.zy.p2p.base.domain.Account;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.business.domain.RechargeOffline;
import com.zy.p2p.business.mapper.RechargeOfflineMapper;
import com.zy.p2p.business.query.RechargeOfflineQueryObject;
import com.zy.p2p.business.service.IAccountFlowService;
import com.zy.p2p.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;



@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService {

	@Autowired
	private RechargeOfflineMapper rechargeOfflineMapper;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IAccountFlowService accountFlowService;

	@Autowired
	private ApplicationContext ctx;

	@Override
	public void apply(RechargeOffline recharge) {
		recharge.setApplier(UserContext.getCurrent());
		recharge.setApplyTime(new Date());
		recharge.setState(RechargeOffline.STATE_NORMAL);

		//判断此交易号是否已经被审核过（防止重复添加）
		this.rechargeOfflineMapper.insert(recharge);
	}

	@Override
	public PageResult query(RechargeOfflineQueryObject qo) {
		int count = this.rechargeOfflineMapper.queryForCount(qo);
		if (count > 0) {
			List<RechargeOffline> list = this.rechargeOfflineMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(),
					qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	/**
	 * 后台线下充值审核
	 */
	@Override
	public void audit(Long id, String remark, int state) {
		// 查询线下充值对象;设置相关属性;
		RechargeOffline rechargeOffline = this.rechargeOfflineMapper.selectByPrimaryKey(id);
		if (rechargeOffline != null && rechargeOffline.getState() == RechargeOffline.STATE_NORMAL) {
			// 审核通过
			rechargeOffline.setAuditor(UserContext.getCurrent());
			rechargeOffline.setAuditTime(new Date());
			rechargeOffline.setRemark(remark);
			rechargeOffline.setState(state);
			if (state == RechargeOffline.STATE_AUDIT) {
				// **1,得到申请人的账户对象;
				Account applierAccount = this.accountService.get(rechargeOffline.getApplier().getId());
				// **2,增加账户的可用余额;
				applierAccount.setUsableAmount(applierAccount.getUsableAmount().add(rechargeOffline.getAmount()));
				// **3,生成一条充值流水
				this.accountFlowService.rechargeFlow(rechargeOffline, applierAccount);
				this.accountService.update(applierAccount);
				//this.ctx.publishEvent(new RechargeOfflineSuccessEvent(this, r));
			}
			this.rechargeOfflineMapper.updateByPrimaryKey(rechargeOffline);
		}
	}

}
