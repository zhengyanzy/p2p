package com.zy.p2p.base.mapper;

import com.zy.p2p.base.domain.UserFile;
import com.zy.p2p.base.domain.UserFile;
import com.zy.p2p.base.query.UserFileQueryObject;
import com.zy.p2p.base.query.base.AuditQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserfileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFile record);

    UserFile selectByPrimaryKey(Long id);

    List<UserFile> selectAll();

    int updateByPrimaryKey(UserFile record);

    /**
     *
     * @param logininfoId
     * @param hasType
     * @return
     */
    List<UserFile> listFilesByHasType(@Param(value = "logininfoId") Long logininfoId, @Param(value = "hasType") boolean hasType);

    /**
     *  后台审核风控材料 分页
     */
    int queryForCount(AuditQueryObject qo);
    List<UserFile> query(UserFileQueryObject qo);
}