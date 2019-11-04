package com.zy.p2p.base.event;

import com.zy.p2p.base.domain.RealAuth;
import lombok.Getter;

import org.springframework.context.ApplicationEvent;


/**
 * 实名认证审核通过的消息
 * 
 * @author Administrator
 * 
 */
@Getter
public class RealAuthSuccessEvent extends ApplicationEvent {
	/**
	 * 事件关联的对象
	 */
	private RealAuth realAuth;

	public RealAuthSuccessEvent(Object source, RealAuth realAuth) {
		super(source);
		this.realAuth = realAuth;
	}
}
