package com.zy.p2p.base.query;

import com.zy.p2p.base.query.base.AuditQueryObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户风控文件查询对象
 *
 * @author Administrator
 *
 */
@Getter
@Setter
public class UserFileQueryObject extends AuditQueryObject {
	private Long applierId;
}
