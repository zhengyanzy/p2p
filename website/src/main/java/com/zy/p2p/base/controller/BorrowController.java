package com.zy.p2p.base.controller;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.UserFileQueryObject;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IRealAuthService;
import com.zy.p2p.base.service.IUserFileService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.business.domain.BidRequest;
import com.zy.p2p.business.query.BidRequestQueryObject;
import com.zy.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 借款申请相关的控制器
 *
 * @author Administrator
 */
@Controller
public class BorrowController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IBidRequestService bidRequestService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private IUserFileService userFileService;

    /**
     * 导向到我要借款首页(选择借款类型)
     *
     * @return
     */
    @RequestMapping("/borrow")
    public String borrowIndex(Model model) {
        Logininfo current = UserContext.getCurrent();
        // 如果没有登陆,直接返回到borrow.html静态页面
        if (current == null) {
            //请求重定向(自动前面带上项目名)
            return "redirect:borrow.html";
        } else {
            model.addAttribute("account", this.accountService.getAccount());
            System.out.println(userinfoService.getUserinfo());

            model.addAttribute("userinfo", this.userinfoService.getUserinfo());
            model.addAttribute("creditBorrowScore", BidConst.BASE_BORROW_SCORE);
            return "borrow";
        }
    }

    /**
     * 导向到借款申请页面
     */
    @RequestMapping("borrowInfo")
    public String borrowInfo(Model model) {
        Long id = UserContext.getCurrent().getId();
        // 判断当前用户是否具有申请贷款条件
        if (bidRequestService.canApplyBidRequeset(id)) {
            model.addAttribute("minBidRequestAmount", BidConst.SMALLEST_BIDREQUEST_AMOUNT);// 最小借款金额
            model.addAttribute("minBidAmount", BidConst.SMALLEST_BID_AMOUNT);// 系统规定的最小投标金额
            model.addAttribute("account", this.accountService.getAccount());
            return "borrow_apply";
        } else {
            return "borrow_apply_result";
        }
    }

    /**
     * 借款申请
     */
    @RequestMapping("borrow_apply")
    public String borrowApply(BidRequest bidRequest) {
        this.bidRequestService.apply(bidRequest);
        return "redirect:/borrowInfo.do";
    }

    /**
     * 可以允许用户不登录就访问
     * 前端访问借款明细
     */
    @RequestMapping("borrow_info")
    public String borrowInfoDetail(Long id, Model model) {
        // bidRequest;
        BidRequest bidRequest = this.bidRequestService.get(id);
        //设置不允许查看哪些bidRequest的借款不可以查看（bidRequest.getBidRequestState()==10）

        if (bidRequest != null) {
            // userInfo
            Userinfo applier = this.userinfoService.get(bidRequest.getCreateUser().getId());
            // realAuth:借款人实名认证信息
            model.addAttribute("realAuth", this.realAuthService.get(applier.getRealAuthId()));
            // userFiles:借款人风控材料
            UserFileQueryObject qo = new UserFileQueryObject();
            qo.setApplierId(applier.getId());
            qo.setPageSize(-1);
            qo.setCurrentPage(1);
            model.addAttribute("userFiles", this.userFileService.queryForList(qo));
            model.addAttribute("bidRequest", bidRequest);
            model.addAttribute("userInfo", applier);
            //用户已经登录
            if (UserContext.getCurrent() != null) {
                // self:当前登录用户是否是借款人自己
                if (UserContext.getCurrent().getId().equals(applier.getId())) {
                    model.addAttribute("self", true);
                } else {
                    // account
                    model.addAttribute("self", false);
                    model.addAttribute("account", this.accountService.getAccount());
                }
            }
            //用户未登录
            else {
                model.addAttribute("self", false);
            }
        }
        return "borrow_info";
    }

    //借款列表
    @RequestMapping("borrow_list")
    public String borrow_list() {
        return "borrow_list";
    }

    @RequestMapping("borrow_list_td")
    public String borrow_list_td(Model model, @ModelAttribute(value = "qo") BidRequestQueryObject qo) {
        //查询招标中和已还清的
        qo.setBidRequestStates(new int[]{BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK, BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
        qo.setOrderBy("bidRequestState");
        qo.setOrderType("ASC");
        PageResult pageResult = bidRequestService.query(qo);
        model.addAttribute("pageResult", pageResult);
        return "list/borrow_list";
    }
}