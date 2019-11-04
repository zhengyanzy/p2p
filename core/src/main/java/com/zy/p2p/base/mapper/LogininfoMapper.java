package com.zy.p2p.base.mapper;

import com.zy.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    int seletCountByUserName(String userName);

    String selectByUserName(String username);
    Logininfo selectByUserNameAndPassword(@Param(value = "username") String username,@Param(value = "password")String password,@Param(value = "userType")int userType);

    //查看是否有管理员存在
    int countByUserType(int userType);
}