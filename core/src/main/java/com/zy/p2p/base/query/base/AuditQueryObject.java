package com.zy.p2p.base.query.base;

import java.util.Date;

import com.zy.p2p.base.query.base.QueryObject;
import com.zy.p2p.base.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 基本的审核查询对象
 *
 * @author Administrator
 *
 */
@Setter
@Getter
public class AuditQueryObject extends QueryObject {
	private int state = -1; //表示查询用户的审核状态(-1:全部)
	private Date beginDate;
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate == null ? null : DateUtil.endOfDay(endDate);
	}
}
