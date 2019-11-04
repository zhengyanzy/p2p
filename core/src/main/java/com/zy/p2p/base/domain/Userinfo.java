package com.zy.p2p.base.domain;

import com.zy.p2p.base.utils.BitStatesUtils;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Userinfo extends BaseDomain {
    private int version;        //版本号(用于乐观锁)

    private long bitState = 0;  //用户状态码
    private Long realAuthId;    //实名认证对象

    private String realName;    //用户真实姓名（冗余数据）
    private String idNumber;    //身份证号（冗余数据）
    private String phoneNumber; //电话号码
    private String email;       //邮箱
    private int authScore;      //风控累计分数,用于可以贷款多少

    private SystemDictionaryItem incomeGrade; //收入等级
    private SystemDictionaryItem marriage;    //婚姻状况
    private SystemDictionaryItem kidCount;    //子女
    private SystemDictionaryItem educationBackground;//教育背景
    private SystemDictionaryItem houseCondition;     //住房条件

    /**
     * 添加状态码
     */
    public void addState(long state) {
        this.setBitState(BitStatesUtils.addState(this.bitState, state));
    }

    /**
     * 删除状态码（解除绑定）
     */
    public void removeState(long state) {
        this.setBitState(BitStatesUtils.removeState(this.bitState, state));
    }

    /**
     * 返回用户是否已经绑定手机
     */
    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE);
    }

    /**
     * 返回用户是否已经绑定邮箱
     *
     * @return
     */
    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL);
    }

    /**
     * 返回用户是否已经填写了基本资料
     *
     * @return
     */
    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
    }

    /**
     * 返回用户是否已经实名认证
     *
     * @return
     */
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    /**
     * 返回用户是否视频认证
     *
     * @return
     */
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }

    /**
     * 返回用户是否绑定银行卡
     *
     * @return
     */
    public boolean getIsBindBank() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_BANKINFO);
    }

    /**
     * 返回用户是否有一个借款在处理流程当中
     *
     * @return
     */
    public boolean getHasBidRequestProcess() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }

    /**
     * 返回用户是否有一个提现申请在处理流程当中
     *
     * @return
     */
    public boolean getHasWithdrawProcess() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_MONEYWITHDRAW_PROCESS);
    }

    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     */
    @SuppressWarnings("all")
    public String getAnonymousRealName() {
        if (StringUtils.hasLength(this.realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }
    /**
     * 获取用户身份号码的隐藏字符串
     * @return
     */
    @SuppressWarnings("all")
    public String getAnonymousIdNumber() {
        if (StringUtils.hasLength(idNumber)) {
            int len = idNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 5 && i < 10) || (i > len - 5)) {
                    replace += "*";
                } else {
                    replace += idNumber.charAt(i);
                }
            }
            return replace;
        }
        return idNumber;
    }
}