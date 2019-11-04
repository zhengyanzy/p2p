package com.zy.p2p.base.service;

/**
 * z专门用于发送邮件的服务
 * 
 * @author Administrator
 * 
 */
public interface IMailService {

	/**
	 * 发送邮件
	 * 
	 * @param target 目标邮件地址
	 * @param title
	 * @param content
	 */
	void sendMail(String target, String title, String content);

}
