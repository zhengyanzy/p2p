package com.zy.mgrsite.base;

import com.zy.p2p.base.utils.JsonResult;
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
 * 线下充值审核
 * 
 * @author Administrator
 * 
 */
@Controller
public class RechargeOfflineController {
	@Autowired
	private IRechargeOfflineService rechargeOfflineService;

	@Autowired
	private IPlatformBankInfoService platformBankInfoService;

	@RequestMapping("rechargeOffline")
	public String rechargeOffline(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {
		model.addAttribute("banks", this.platformBankInfoService.listAll());
		model.addAttribute("pageResult", this.rechargeOfflineService.query(qo));
		return "rechargeoffline/list";
	}

	/**
	 * 线下充值审核
	 */
	@ResponseBody
	@PostMapping("rechargeOffline_audit")
	public JsonResult audit(Long id, String remark, int state) {
		this.rechargeOfflineService.audit(id, remark, state);
		return new JsonResult();
	}

}
