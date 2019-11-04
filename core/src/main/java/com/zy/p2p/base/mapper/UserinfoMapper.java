package com.zy.p2p.base.mapper;

import com.zy.p2p.base.domain.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserinfoMapper {
    int deleteByPrimaryKey(Long id);

    void insert(Userinfo record);

    Userinfo selectByPrimaryKey(Long id);

    List<Userinfo> selectAll();

    /**
     *  更新用户信息(乐观锁控制)
     */
    int updateByPrimaryKey(Userinfo userinfo);

    /**
     * 用于后台审核视频,添加用户的autcomplate（自动补全）
     * 返回的MAP:{id:logininfoId,username:username}
     */
    List<Map<String, Object>> autocomplate(String keyword);
}