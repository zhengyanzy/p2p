package com.zy.p2p.base.controller;

import com.zy.p2p.base.domain.SystemDictionaryItem;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.service.ISystemDictionaryService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户个人资料填写
 * 
 * @author Administrator
 * 
 */
@Controller
public class UserInfoController {
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private ISystemDictionaryService systemDictionaryService;
	
	@RequireLogin
	@RequestMapping("basicInfo")
	public String basicInfo(Model model){
		//添加:当前用户;
		model.addAttribute("userinfo",this.userinfoService.getUserinfo());
		//添加:所有下拉列表相关内容
		model.addAttribute("educationBackgrounds",this.systemDictionaryService.listByParentSn("educationBackground"));
		model.addAttribute("incomeGrades",this.systemDictionaryService.listByParentSn("incomeGrade"));
		model.addAttribute("marriages",this.systemDictionaryService.listByParentSn("marriage"));
		model.addAttribute("kidCounts",this.systemDictionaryService.listByParentSn("kidCount"));
		model.addAttribute("houseConditions",this.systemDictionaryService.listByParentSn("houseCondition"));
		return "userInfo";
	}
	
	@RequireLogin
	@ResponseBody
	@PostMapping("basicInfo_save")
	public JsonResult basicInfoSave(Userinfo userinfo){
		this.userinfoService.updateBasicInfo(userinfo);
		return new JsonResult();
	}

}
