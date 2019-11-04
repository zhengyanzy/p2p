package com.zy.p2p.base.service;

import com.zy.p2p.base.query.IplogQueryObject;
import com.zy.p2p.base.query.PageResult;

public interface IIplogService {

	/**
	 * 分页查询
	 */
	PageResult query(IplogQueryObject qo);
}
