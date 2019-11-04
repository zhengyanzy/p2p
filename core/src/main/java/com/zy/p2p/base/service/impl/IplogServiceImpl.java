package com.zy.p2p.base.service.impl;

import java.util.List;

import com.zy.p2p.base.domain.Iplog;
import com.zy.p2p.base.mapper.IplogMapper;
import com.zy.p2p.base.query.IplogQueryObject;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IplogServiceImpl implements IIplogService {

	@Autowired
	private IplogMapper iplogMapper;

	@Override
	public PageResult query(IplogQueryObject qo) {
		int totalCount = this.iplogMapper.queryForCount(qo);
		if (totalCount > 0) {
			List<Iplog> list = this.iplogMapper.query(qo);
			PageResult pageResult = new PageResult(list, totalCount, qo.getCurrentPage(),
					qo.getPageSize());
			return pageResult;
		}
		return PageResult.empty(qo.getPageSize());
	}

}
