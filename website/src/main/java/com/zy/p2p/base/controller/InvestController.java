package com.zy.p2p.base.controller;

import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.business.query.BidRequestQueryObject;
import com.zy.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvestController {
    @Autowired
    private IBidRequestService bidRequestService;

    /**
     *	投资列表的框框
     */
    @RequestMapping("invest")
    public String investIndex() {
        return "invest";
    }

    /**
     *	投资列表明细
     */
    @PostMapping("invest_list")
    public String investList(BidRequestQueryObject qo, Model model) {
        if (qo.getBidRequestState() == -1) {
            qo.setBidRequestStates(new int[] {BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK, BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK });
        }
        model.addAttribute("pageResult", this.bidRequestService.query(qo));
        return "list/invest_list";
    }

}
