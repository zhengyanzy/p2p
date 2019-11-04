package com.zy.p2p.business.mapper;


import com.zy.p2p.business.domain.UserBankinfo;
import org.springframework.stereotype.Repository;

public interface UserBankinfoMapper {

	int insert(UserBankinfo record);

	UserBankinfo selectByUser(Long userid);

}