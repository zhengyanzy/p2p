package com.zy.p2p.base.mapper;


import com.zy.p2p.base.domain.SystemDictionary;
import com.zy.p2p.base.query.SystemDictionaryQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

    /**
     * 分页的方法
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionary> query(SystemDictionaryQueryObject qo);
}