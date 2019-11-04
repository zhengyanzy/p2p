package com.zy.p2p.base.domain;

import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.base.utils.MD5;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Account extends BaseDomain {
    private int version;
    private String tradePassword; //交易密码
    private BigDecimal usableAmount = BidConst.ZERO;  //账户可用余额
    private BigDecimal freezedAmount = BidConst.ZERO; //账户冻结金额
    private BigDecimal unReceiveInterest = BidConst.ZERO;//账户待收利息（不是利率）
    private BigDecimal unReceivePrincipal = BidConst.ZERO;//账户待收本金
    private BigDecimal unReturnAmount = BidConst.ZERO;    //账户待还金额
    private BigDecimal remainBorrowLimit = BidConst.INIT_BORROW_LIMIT;//账户剩余授信额度(默认5000.0000)
    private BigDecimal borrowLimitAmount = BidConst.INIT_BORROW_LIMIT;//账户授信额度(默认5000.0000)

    private String verifyCode; //做数据校验的

    public String getVerifyCode() {
        return MD5.encode(usableAmount.hashCode() + " "
                + freezedAmount.hashCode());
    }

    public boolean checkVerifyCode() {
        return MD5.encode(
                usableAmount.hashCode() + " " + freezedAmount.hashCode())
                .equals(verifyCode);
    }

    //计算总额
    public BigDecimal getTotalAmount() {
        BigDecimal all = usableAmount.add(this.freezedAmount)
                .add(this.unReceivePrincipal);
        return all;
    }
}
