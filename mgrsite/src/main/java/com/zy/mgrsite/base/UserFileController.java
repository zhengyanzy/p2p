package com.zy.mgrsite.base;

import com.zy.p2p.base.query.UserFileQueryObject;
import com.zy.p2p.base.service.IUserFileService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 风控资料审核
 * 
 * @author Administrator
 * 
 */
@Controller
public class UserFileController {
	
	@Autowired
	private IUserFileService userFileService;


	/**
	 * 分页查询所有需要审核的数据
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("userFileAuth")
	public String userFileAuthList(@ModelAttribute("qo") UserFileQueryObject qo, Model model){
		model.addAttribute("pageResult",this.userFileService.query(qo));
		return "userFileAuth/list";
	}

	/**
	 * 审核
	 * @param id
	 * @param score
	 * @param remark
	 * @param state :审核状态
	 * @return
	 */
	@RequestMapping("userFile_audit")
	@ResponseBody
	public JsonResult audit(Long id, int score, String remark, int state){
		this.userFileService.audit(id,score,remark,state);
		return new JsonResult();
	}

}
