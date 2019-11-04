package com.zy.p2p.base.service;


import com.zy.p2p.base.domain.RealAuth;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.RealAuthQueryObject;

/**
 * 实名认证对象服务
 *
 * @author Administrator
 *
 */
public interface IRealAuthService {

	RealAuth get(Long id);


	/**
	 *	后台分页查询 审核实名认证信息
	 */
	PageResult query(RealAuthQueryObject qo);


	/**
	 *  申请实名认证
	 */
    void apply(RealAuth realAuth);


	/**
	 * 实名认证审核
	 */
    void audit(Long id, String remark, int state);


}
