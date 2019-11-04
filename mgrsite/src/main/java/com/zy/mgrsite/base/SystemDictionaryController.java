package com.zy.mgrsite.base;

import com.zy.p2p.base.domain.SystemDictionary;
import com.zy.p2p.base.domain.SystemDictionaryItem;
import com.zy.p2p.base.query.SystemDictionaryQueryObject;
import com.zy.p2p.base.service.ISystemDictionaryService;
import com.zy.p2p.base.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 数据字典相关
 * 
 * @author Administrator
 * 
 */
@Controller
public class SystemDictionaryController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 数据字典分类列表
	 */
	@RequestMapping("systemDictionary_list")
	public String systemDictionaryList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		model.addAttribute("pageResult", this.systemDictionaryService.queryDics(qo));
		return "systemdic/systemDictionary_list";
	}

	/**
	 * 添加/修改数据字典
	 */
	@ResponseBody
	@RequestMapping("systemDictionary_update")
	public JsonResult systemDictionaryUpdate(SystemDictionary dictionary) {
		this.systemDictionaryService.saveOrUpdateDic(dictionary);
		return new JsonResult();
	}

	/**
	 * 删除数据字典
	 */
	@RequestMapping("systemDictionary_delete")
	public String systemDictionaryDelete(String id) {
		this.systemDictionaryService.deleteDic(id);
		return "redirect:/systemDictionary_list.do";
	}


	/**
	 * 数据字典 明细列表
	 */
	@RequestMapping("systemDictionaryItem_list")
	public String systemDictionaryItemList(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
		model.addAttribute("pageResult",
				this.systemDictionaryService.queryItems(qo));
		model.addAttribute("systemDictionaryGroups",this.systemDictionaryService.listAllDics());
		return "systemdic/systemDictionaryItem_list";
	}
	
	/**
	 * 添加修改字典明细
	 */
	@ResponseBody
	@RequestMapping("systemDictionaryItem_update")
	public JsonResult systemDictionaryItemUpdate(SystemDictionaryItem item) {
		this.systemDictionaryService.saveOrUpdateItem(item);
		return new JsonResult();
	}
}
