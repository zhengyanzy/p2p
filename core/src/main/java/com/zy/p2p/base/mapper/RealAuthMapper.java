package com.zy.p2p.base.mapper;


import com.zy.p2p.base.domain.RealAuth;
import com.zy.p2p.base.query.RealAuthQueryObject;

import java.util.List;

/**
 * 实名认证对象列表
 */
public interface RealAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RealAuth record);

    /**
     *  分页查询 实名认证审核列表
     */
    int queryForCount(RealAuthQueryObject qo);
    List<RealAuth> query(RealAuthQueryObject qo);
}