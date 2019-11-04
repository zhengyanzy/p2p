package com.zy.p2p.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 邮箱验证对象
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class Mailverify extends BaseDomain {
	private Long userinfoId;// 需要绑定邮箱账号id
	private String email;   //
	private String uuid;
	private Date sendDate;  // 发送邮件的时间
}
