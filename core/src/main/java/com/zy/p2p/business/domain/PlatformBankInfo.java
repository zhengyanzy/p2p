package com.zy.p2p.business.domain;

import java.util.HashMap;
import java.util.Map;

import com.zy.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.JSONObject;


/**
 * 平台账号
 */
@Setter
@Getter
public class PlatformBankInfo extends BaseDomain {

	private String bankName;// 银行名称
	private String accountName;// 开户人姓名
	private String accountNumber;// 银行账号
	private String bankForkName;// 开户支行

	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("bankName", this.bankName);
		json.put("accountName", accountName);
		json.put("accountNumber", accountNumber);
		json.put("bankForkName", bankForkName);
		return JSONObject.toJSONString(json);
	}

}
