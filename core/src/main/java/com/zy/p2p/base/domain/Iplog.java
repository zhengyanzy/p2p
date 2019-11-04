package com.zy.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 登陆日志
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class Iplog extends BaseDomain {

	public static final int STATE_SUCCESS = 1;
	public static final int STATE_FAILED = 0;

	private String ip;

	private Date loginTime;
	private String userName;  //记录请求登录用户名
	private int loginState;

	private int userType;     //用户的登录类型

	//直接在前端判断了
//	public String getStateDisplay() {
//		return loginState == STATE_SUCCESS ? "登陆成功" : "登陆失败";
//	}
	//显示用户的登录情况
	public String getUserTypeDisplay() {
		return userType == Logininfo.USER_CLIENT ? "前端用户" : "后台管理员";
	}

}
