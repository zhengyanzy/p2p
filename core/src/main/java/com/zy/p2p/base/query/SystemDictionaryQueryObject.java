package com.zy.p2p.base.query;

import com.zy.p2p.base.query.base.QueryObject;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典查询对象
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class SystemDictionaryQueryObject extends QueryObject {

	private String keyword;
	private Long parentId;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}
}
