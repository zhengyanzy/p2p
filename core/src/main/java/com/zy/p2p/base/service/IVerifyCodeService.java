package com.zy.p2p.base.service;

/**
 * 手机验证码相关服务
 *
 * @author Administrator
 *
 */
public interface IVerifyCodeService {

    /**
     * 给指定的phone发送验证码
     */
    void sendVerifyCode(String phoneNumber);
    /**
     * 绑定手机号
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     *  给指定的email发送验证码
     */
    void sendVerifyEmail(String email);

    /**
     * 绑定邮箱
     */
    void bindEmail(String key);

}

