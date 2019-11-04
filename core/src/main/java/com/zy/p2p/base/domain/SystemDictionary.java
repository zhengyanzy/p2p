package com.zy.p2p.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zy.p2p.base.utils.JsonResult;
import lombok.Getter;
import lombok.Setter;


/**
 * 数据字典分类
 * @author Administrator
 *
 */
@Setter
@Getter
public class SystemDictionary extends BaseDomain{
	private String title;    //数据字典分类名称
	private String sn;       //数据字典分类编码
	
	/**
	 * 返回当前的json字符串
	 * @return
	 */
	public String getJsonString(){
		Map<String,Object> json=new HashMap<>();
		json.put("id",id);
		json.put("sn", sn);
		json.put("title", title);
		String string = JSONObject.toJSONString(json);
		return string;
	}
}
