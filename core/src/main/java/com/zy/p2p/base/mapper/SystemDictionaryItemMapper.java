package com.zy.p2p.base.mapper;


import java.util.List;

import com.zy.p2p.base.domain.SystemDictionary;
import com.zy.p2p.base.domain.SystemDictionaryItem;
import com.zy.p2p.base.query.SystemDictionaryQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(@Param("id") Long id);

    List<SystemDictionaryItem> selectAll();


    /**
     *  更新字典明细
     */
    int updateByPrimaryKey(SystemDictionaryItem record);

    /**
     * 分页
     */
    /**
     * 分页相关的查询
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

    /**
     *  返回数据字典明细
     */
    List<SystemDictionaryItem> listByParentSn(String sn);
}