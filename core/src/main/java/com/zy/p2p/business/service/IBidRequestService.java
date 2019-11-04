package com.zy.p2p.business.service;

import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.business.domain.BidRequest;
import com.zy.p2p.business.domain.BidRequestAuditHistory;
import com.zy.p2p.business.query.BidRequestQueryObject;
import com.zy.p2p.business.query.PaymentScheduleQueryObject;

import java.math.BigDecimal;
import java.util.List;



/**
 * 借款相关
 */
public interface IBidRequestService {

	/**
	 *	更新借款
	 */
	void update(BidRequest bidRequest);

	/**
	 *	获取借款
	 */
	BidRequest get(Long id);

	/**
	 * 前台判断用户是否具有申请借款的权利
	 */
	boolean canApplyBidRequeset(Long logininfoId);

	/**
	 *	前台申请借款
	 */
	void apply(BidRequest bidRequest);

	/**
	 * 根据一个标查询该标的审核历史
	 */
	List<BidRequestAuditHistory> listAuditHistoryByBidRequest(Long id);

	/**
	 *	后台审核列表
	 */
	PageResult query(BidRequestQueryObject qo);

	/**
	 * 后台发标前对用户的借款请求审核
	 */
	void publishAudit(Long id, String remark, int state);

	/**
	 * 查询首页借款列表
	 */
	List<BidRequest> listIndex(int size);

	/**
	 * 投标
	 */
	void bid(Long bidRequestId, BigDecimal amount);

	/**
	 * 满标一审
	 */
	void fullAudit1(Long id, String remark, int state);

	/**
	 * 满标二审
	 */
	void fullAudit2(Long id, String remark, int state);

	PageResult queryPaymentSchedule(PaymentScheduleQueryObject qo);

	/**
	 * 借款人还钱
	 */
	void doReturnMoney(Long id);
}
