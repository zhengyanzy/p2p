package com.zy.p2p.base.mapper;

import com.zy.p2p.base.domain.SystemDictionary;
import com.zy.p2p.base.domain.VedioAuth;
import com.zy.p2p.base.query.SystemDictionaryQueryObject;
import com.zy.p2p.base.query.VedioAuthQueryObject;

import java.util.List;

public interface VedioAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VedioAuth record);

    VedioAuth selectByPrimaryKey(Long id);

    List<VedioAuth> selectAll();

    int updateByPrimaryKey(VedioAuth record);

    /**
     * 分页的方法
     */
    int queryForCount(VedioAuthQueryObject qo);
    List<VedioAuth> query(VedioAuthQueryObject qo);
}