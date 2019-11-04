package com.zy.p2p.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.zy.p2p.base.domain.UserFile;
import com.zy.p2p.base.service.ISystemDictionaryService;
import com.zy.p2p.base.service.IUserFileService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UploadUtil;
import com.zy.p2p.base.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



/**
 * 风控资料相关
 * 
 * @author Administrator
 * 
 */
@Controller
public class UserFileController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private IUserFileService userFileService;

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	@RequireLogin
	@RequestMapping("userFile")
	public String userFile(Model model, HttpServletRequest request) {
		// 查找该用户的未选择文件类型的风控材料图片（还没有提交的风控材料图片）
		List<UserFile> userFiles = this.userFileService.listFilesByHasType(UserContext.getCurrent().getId(), false);

		//该用户有未选择文件类型的风控材料图片,去设置文件类型
		if (userFiles.size() > 0) {
			model.addAttribute("fileTypes", this.systemDictionaryService.listByParentSn("userFileType"));
			model.addAttribute("userFiles", userFiles);
			return "userFiles_commit";
		}
		else {
			// 查找该用户的已经选择文件类型的风控材料图片
			userFiles = this.userFileService.listFilesByHasType(UserContext.getCurrent().getId(), true);
			model.addAttribute("userFiles", userFiles);
			return "userFiles";
		}
	}

	/**
	 * 处理上传用户风控文件
	 */
	@ResponseBody
	@PostMapping("userFileUpload")
	public void userFileUpload(MultipartFile file) {
		String basePath = this.servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		fileName = "/upload/" + fileName;
		this.userFileService.apply(fileName);
	}

	/**
	 * 处理用户风控文件类型选择
	 */
	@RequireLogin
	@ResponseBody
	@PostMapping("userFile_selectType")
	public JsonResult userFileSelectType(Long[] fileType, Long[] id) {
		if (fileType != null && id != null) {
			this.userFileService.batchUpdateFileType(id, fileType);
		}
		return new JsonResult();
	}
}
