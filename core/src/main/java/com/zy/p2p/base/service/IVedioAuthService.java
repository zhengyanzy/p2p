package com.zy.p2p.base.service;


import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.VedioAuthQueryObject;

/**
 * 视频认证服务
 * 
 * @author Administrator
 * 
 */
public interface IVedioAuthService {

	/**
	 *	需要审核的视频的列表
	 */
	PageResult query(VedioAuthQueryObject qo);

	/**
	 * 视频审核逻辑
	 * @param loginInfoValue
	 * @param remark
	 * @param state
	 */
	void audit(Long loginInfoValue, String remark, int state);

}
