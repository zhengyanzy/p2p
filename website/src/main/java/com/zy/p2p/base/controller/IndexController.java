package com.zy.p2p.base.controller;

import com.zy.p2p.base.utils.BidConst;
import com.zy.p2p.business.query.BidRequestQueryObject;
import com.zy.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 网站首页
 * 
 */
@Controller
public class IndexController {

	@Autowired
	private IBidRequestService bidRequestService;

	/**
	 *	首页
	 */
	@RequestMapping("index")
	public String index(Model model) {
		// bidRequests
		model.addAttribute("bidRequests", this.bidRequestService.listIndex(5));
		return "main";
	}
}
