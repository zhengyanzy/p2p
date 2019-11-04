package com.zy.p2p.business.service.impl;

import java.util.List;

import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.business.domain.PlatformBankInfo;
import com.zy.p2p.business.mapper.PlatformBankInfoMapper;
import com.zy.p2p.business.query.PlatformBankInfoQueryObject;
import com.zy.p2p.business.service.IPlatformBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformBankInfoServiceImpl implements IPlatformBankInfoService {
	@Autowired
	private PlatformBankInfoMapper platformBankInfoMapper;

	@Override
	public PageResult query(PlatformBankInfoQueryObject qo) {
		int count = this.platformBankInfoMapper.queryForCount(qo);
		if (count > 0) {
			List<PlatformBankInfo> list = this.platformBankInfoMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void saveOrUpdate(PlatformBankInfo bankInfo) {
		if (bankInfo.getId() != null) {
			this.platformBankInfoMapper.updateByPrimaryKey(bankInfo);
		} else {
			this.platformBankInfoMapper.insert(bankInfo);
		}
	}

	@Override
	public List<PlatformBankInfo> listAll() {
		return this.platformBankInfoMapper.selectAll();
	}

}
