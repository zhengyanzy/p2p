package com.zy.p2p.base.controller;

import java.math.BigDecimal;

import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.service.IAccountService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.business.service.IMoneyWithdrawService;
import com.zy.p2p.business.service.IUserBankinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 提现相关
 * 
 * @author Administrator
 * 
 */
@Controller
public class MoneyWithdrawController {

	@Autowired
	private IMoneyWithdrawService moneyWithdrawService;
	
	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IUserBankinfoService userBankinfoService;

	@Autowired
	private IAccountService accountService;

	/**
	 * 导向到提现申请界面
	 */
	@RequireLogin
	@RequestMapping("moneyWithdraw")
	public String moenyWithdraw(Model model) {
		Userinfo current = this.userinfoService.getUserinfo();
		model.addAttribute("haveProcessing", current.getHasWithdrawProcess());
		model.addAttribute("bankInfo", this.userBankinfoService.getByUser(current.getId()));
		model.addAttribute("account", this.accountService.getAccount());
		return "moneyWithdraw_apply";
	}
	
	/*8
	 * 提现申请
	 */
	@RequireLogin
	@ResponseBody
	@PostMapping("moneyWithdraw_apply")
	public JsonResult apply(BigDecimal moneyAmount){
		this.moneyWithdrawService.apply(moneyAmount);
		return new JsonResult();
	}
	
}
