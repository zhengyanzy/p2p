package com.zy.p2p.base.query;

import java.util.Date;

import com.zy.p2p.base.query.base.QueryObject;
import com.zy.p2p.base.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;


/**
 *  日志查询对象
 */
@Setter
@Getter
public class IplogQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int loginState = -1;
    private String username;
    private int userType;  //用户类型

    /**
     * 因为IpLogQueryObject里面的参数都是直接让SpringMVC注入进来的 如果没有配置日期的格式,SpringMVC没法注入日期;
     * 所以,最简单的办法,通过添加@DateTimeFormat标签来告诉SpringMVC日期的注入格式
     */

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    //获取一天的最后一秒中的时间
    public Date getEndDate() {
        return endDate == null ? null : DateUtil.endOfDay(endDate);
    }

    public String getUsername() {
        return StringUtils.hasLength(username) ? username : null;
    }
}
