package com.zy.mgrsite.base;

import com.zy.p2p.base.query.RealAuthQueryObject;
import com.zy.p2p.base.service.IRealAuthService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 实名认证审核相关
 * 
 * @author Administrator
 * 
 */
@Controller
public class RealAuthController {

	@Autowired
	private IRealAuthService realAuthService;


	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute("qo") RealAuthQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.realAuthService.query(qo));
		return "realAuth/list";
	}
	
	/**
	 * 实名认证审核
	 */
	@ResponseBody
	@PostMapping("realAuth_audit")
	public JsonResult realAuthAudit(Long id, String remark, int state){
		this.realAuthService.audit(id,remark,state);
		return new JsonResult();
	}
}
