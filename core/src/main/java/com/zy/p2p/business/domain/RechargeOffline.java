package com.zy.p2p.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zy.p2p.base.domain.BaseAuditDomain;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;


/**
 * 线下充值单
 */
@Getter
@Setter
public class RechargeOffline extends BaseAuditDomain {

	private PlatformBankInfo bankInfo; //系统银行账号
	private String tradeCode;// 交易号
	private BigDecimal amount;// 充值金额
	private Date tradeTime;// 充值时间(和申请审核时间不一样)
	private String note;// 充值说明

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", this.applier.getUsername());
		json.put("tradeCode", tradeCode);
		json.put("amount", amount);
		json.put("tradeTime", tradeTime);
		return JSONObject.toJSONString(json);
	}

}
