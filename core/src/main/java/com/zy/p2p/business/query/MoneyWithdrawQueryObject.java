package com.zy.p2p.business.query;

import com.zy.p2p.base.query.base.AuditQueryObject;
import lombok.Getter;
import lombok.Setter;



/**
 * 提现申请查询对象
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class MoneyWithdrawQueryObject extends AuditQueryObject {

	private Long applierId;
}
