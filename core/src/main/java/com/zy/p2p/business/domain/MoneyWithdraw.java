package com.zy.p2p.business.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.DateFormatter;

import com.alibaba.fastjson.JSONObject;
import com.zy.p2p.base.domain.BaseAuditDomain;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


/**
 * 提现申请
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class MoneyWithdraw extends BaseAuditDomain {

	private BigDecimal amount;// 提现金额
	private BigDecimal charge;// 提现手续费

	//不能直接关联银行账号表,原因银行账号表是可能会修改的,如果修改了,那么以前的体现的记录的银行卡就会被改,虽然说不会对转账有影响,但是提现记录被改了
	private String bankName;// 银行名称
	private String accountName;// 开户人姓名
	private String accountNumber;// 银行账号
	private String bankForkName;// 开户支行

	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", this.applier.getUsername());
		json.put("realName", accountName);
		json.put("applyTime", DateFormat.getDateTimeInstance()
				.format(applyTime));
		json.put("bankName", bankName);
		json.put("accountNumber", accountNumber);
		json.put("bankforkname", bankForkName);
		json.put("moneyAmount", amount);
		return JSONObject.toJSONString(json);
	}
}
