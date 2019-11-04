package com.zy.p2p.base.domain;

import lombok.Data;

//用户登陆或者注册信息（信息不能删除,可以设置隐藏的操作）
@Data
public class Logininfo extends BaseDomain{
    public final static int STATE_NORNAL=0;    //普通用户状态(活跃用户)
    public final static int STATE_LOCK=1;      //用户锁定状态
    public final static int STATE_LOGOFF=-1;   //用户注销状态

    public static final int USER_MANAGER = 0;// 后台用户
    public static final int USER_CLIENT = 1;// 前台用户


    private String username;
    private String password;
    private Integer state=STATE_NORNAL;        //是否允许该用户登录（默认是0）
    private int userType;                      //判断用户是不是管理员;
}
