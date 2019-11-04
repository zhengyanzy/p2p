package com.zy.p2p.base.controller;

import java.math.BigDecimal;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.business.query.BidQueryObject;
import com.zy.p2p.business.service.IBidRequestService;
import com.zy.p2p.business.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;


/**
 * 投标
 * 
 * @author Administrator
 * 
 */
@Controller
public class BidController {

	@Autowired
	private IBidRequestService bidRequestService;
	@Autowired
	private IBidService bidService;

	@RequireLogin
	@ResponseBody
	@PostMapping("borrow_bid")
	public JsonResult bid(Long bidRequestId, BigDecimal amount) {
		this.bidRequestService.bid(bidRequestId, amount);
		return new JsonResult();
	}

	@RequireLogin
	@GetMapping("bid_list")
	public String bid_list() {
		return "bid_list";
	}


	@RequireLogin
	@GetMapping("bid_list_td")
	public String bid_list_tr(Model model,BidQueryObject qo) {
		Logininfo current = UserContext.getCurrent();
		qo.setBidUser_id(current.getId());
		PageResult query = bidService.query(qo);
		model.addAttribute("pageResult",query);
		return "list/bid_list_td";
	}
}
