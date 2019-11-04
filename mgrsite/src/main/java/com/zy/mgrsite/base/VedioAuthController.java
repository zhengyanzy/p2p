package com.zy.mgrsite.base;

import java.util.List;
import java.util.Map;

import com.zy.p2p.base.query.VedioAuthQueryObject;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.service.IVedioAuthService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 视频认证Controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class VedioAuthController {

	@Autowired
	private IVedioAuthService vedioAuthService;
	
	@Autowired
	private IUserinfoService userinfoService;


	/**
	 *	需要审核的视频的列表
	 */
	@RequestMapping("vedioAuth")
	public String vedioAuth(@ModelAttribute("qo") VedioAuthQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.vedioAuthService.query(qo));
		return "vedioAuth/list";
	}
	
	/**
	 * 完成视频审核
	 */
	@ResponseBody
	@PostMapping("vedioAuth_audit")
	public JsonResult vedioAuthAudit(Long loginInfoValue, String remark, int state){
		try {
			this.vedioAuthService.audit(loginInfoValue,remark,state);
		}catch (Exception e){
			JsonResult jsonResult = new JsonResult();
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
			return jsonResult;
		}

		return new JsonResult();
	}
	
	/**
	 * 用于添加视频审核用户的automcomplate（自动补全）
	 */
	@ResponseBody
	@PostMapping("vedioAuth_autocomplate")
	public List<Map<String,Object>> autoComplate(String keyword){
		return this.userinfoService.autoComplate(keyword);
	}

}