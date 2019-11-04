package com.zy.p2p.business.mapper;


import com.zy.p2p.business.domain.SystemAccount;
import org.springframework.stereotype.Repository;


public interface SystemAccountMapper {

	/**
	 * 创建系统账号，只有系统启动中才会被创建
	 * @param record
	 * @return
	 */
	int insert(SystemAccount record);

	/**
	 * 返回当前活动的那个系统账户
	 * 
	 * @return
	 */
	SystemAccount selectCurrent();

	int updateByPrimaryKey(SystemAccount record);
}