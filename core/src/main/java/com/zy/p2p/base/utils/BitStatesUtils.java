package com.zy.p2p.base.utils;

/**
 * 用户状态类，记录用户在平台使用系统中所有的状态。
 */
public class BitStatesUtils {
	public final static Long OP_BIND_PHONE = 1L << 0;            //0..00000001 用户绑定手机状态码
	public final static Long OP_BIND_EMAIL = 1L << 1;            //0..00000010 用户绑定邮箱
	public final static Long OP_BASIC_INFO = 1L << 2;            //0..00000100 用户是否填写基本资料
	public final static Long OP_REAL_AUTH = 1L << 3;             //0..00001000 用户是否实名认证
	public final static Long OP_VEDIO_AUTH = 1L << 4;            //0..00010000 用户是否视频认证
	public final static Long OP_HAS_BIDREQUEST_PROCESS = 1L << 5;//0..00100000 用户是否有一个借款正在处理流程当中
	public final static Long OP_BIND_BANKINFO = 1L << 6;         //0..01000000 用户是否绑定银行卡
	public final static Long OP_HAS_MONEYWITHDRAW_PROCESS=1L<<7; //0..10000000 用户是否有一个提现申请在处理中

	/**
	 * @param states 用户当前的状态码
	 * @param value  需要判断状态码
	 * @return       返回是否存在
	 */
	public static boolean hasState(long states, long value) {
		return (states & value) != 0;
	}

	/**
	 * @param states 用户当前的状态码
	 * @param value  需要添加状态值
	 * @return       返回用户最新的状态码
	 */
	public static long addState(long states, long value) {
		if (hasState(states, value)) {
			return states;
		}
		return (states | value);
	}

	/**
	 * @param states 用户当前的状态码
	 * @param value  需要删除状态值
	 * @return       返回用户最新的状态码
	 */
	public static long removeState(long states, long value) {
		if (!hasState(states, value)) {
			return states;
		}
		return states ^ value;
	}
}