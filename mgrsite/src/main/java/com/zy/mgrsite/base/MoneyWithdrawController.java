package com.zy.mgrsite.base;

import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.business.query.MoneyWithdrawQueryObject;
import com.zy.p2p.business.service.IMoneyWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 提现申请审核
 * 
 * @author Administrator
 * 
 */
@Controller
public class MoneyWithdrawController {

	@Autowired
	private IMoneyWithdrawService moneyWithdrawService;

	@RequestMapping("moneyWithdraw")
	public String moneyWithdrawList(
			@ModelAttribute("qo") MoneyWithdrawQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.moneyWithdrawService.query(qo));
		return "moneywithdraw/list";
	}

	@RequestMapping("moneyWithdraw_audit")
	@ResponseBody
	public JsonResult audit(Long id, String remark, int state) {
		this.moneyWithdrawService.audit(id, remark, state);
		return new JsonResult();
	}

}
