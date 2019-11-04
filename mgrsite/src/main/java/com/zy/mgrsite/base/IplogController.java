package com.zy.mgrsite.base;

import com.zy.p2p.base.query.IplogQueryObject;
import com.zy.p2p.base.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


/**
 * 后台查询登陆日志controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class IplogController {

	@Autowired
	private IIplogService iplogService;

	@RequestMapping("/ipLog")
	public String ipLog(@ModelAttribute("qo") IplogQueryObject qo, Model model) {
		model.addAttribute("pageResult", iplogService.query(qo));
		return "iplog/list";
	}
}

