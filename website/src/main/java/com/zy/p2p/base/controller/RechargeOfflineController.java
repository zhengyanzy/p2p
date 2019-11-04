package com.zy.p2p.base.controller;

import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import com.zy.p2p.business.domain.RechargeOffline;
import com.zy.p2p.business.query.RechargeOfflineQueryObject;
import com.zy.p2p.business.service.IPlatformBankInfoService;
import com.zy.p2p.business.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 前台的线下充值
 */
@Controller
public class RechargeOfflineController {
	@Autowired
	private IPlatformBankInfoService platformBankInfoService;
	@Autowired
	private IRechargeOfflineService rechargeOfflineService;

	/**
	 * 导向到线下充值页面
	 */
	@RequireLogin
	@RequestMapping("recharge")
	public String recharge(Model model) {
		model.addAttribute("banks", this.platformBankInfoService.listAll());
		return "recharge";
	}

	@RequireLogin
	@RequestMapping("recharge_list")
	public String rechargeList(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) { qo.setApplierId(UserContext.getCurrent().getId());
		model.addAttribute("pageResult", this.rechargeOfflineService.query(qo));
		return "recharge_list";
		
	}

	/**
	 * 提交线下充值单
	 */
	@RequireLogin
	@ResponseBody
	@PostMapping("recharge_save")
	public JsonResult rechargeApply(RechargeOffline recharge) {
		this.rechargeOfflineService.apply(recharge);
		return new JsonResult();
	}
}
