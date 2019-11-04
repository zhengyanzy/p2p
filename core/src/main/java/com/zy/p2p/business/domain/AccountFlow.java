package com.zy.p2p.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.zy.p2p.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;


/**
 * 账户流水(账户的转入或者转出)
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class AccountFlow extends BaseDomain {

	private Long accountId;// 流水是关于哪个账户的
	private BigDecimal amount;// 这次账户发生变化的金额
	private Date tradeTime;// 这次账户发生变化的时间
	private int accountType;// 资金变化类型
	private BigDecimal usableAmount;// 发生变化之后的可用余额;
	private BigDecimal freezedAmount;// 发生变化之后的冻结金额(比如投标的金额,因为需要满标二审,还有提现),最终冻结金额会减少,可用余额增加；
	private String note;//账户流水说明
}
