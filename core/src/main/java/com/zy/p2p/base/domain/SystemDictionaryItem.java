package com.zy.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典明细
 * 
 * @author Administrator
 * 
 */
@Setter
@Getter
public class SystemDictionaryItem extends BaseDomain {
	private Long parentId;
	private String title;
	private int sequence;  //序列
	
	/**
	 * 返回当前的json字符串
	 * @return
	 */
	public String getJsonString(){
		Map<String,Object> json=new HashMap<>();
		json.put("id",id);
		json.put("title", title);
		json.put("sequence", sequence);
		return JSONObject.toJSONString(json);
	}
}
