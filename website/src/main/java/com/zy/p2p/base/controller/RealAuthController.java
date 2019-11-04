package com.zy.p2p.base.controller;

import javax.servlet.ServletContext;

import com.zy.p2p.base.domain.RealAuth;
import com.zy.p2p.base.domain.Userinfo;
import com.zy.p2p.base.service.IRealAuthService;
import com.zy.p2p.base.service.IUserinfoService;
import com.zy.p2p.base.utils.JsonResult;
import com.zy.p2p.base.utils.RequireLogin;
import com.zy.p2p.base.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


/**
 * 实名认证控制
 *
 * @author Administrator
 */
@Controller
public class RealAuthController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private ServletContext servletContext;

    /**
     * 访问实名认证
     */
    @RequireLogin
    @RequestMapping("realAuth")
    public String realAuth(Model model) {
        // 1,得到当前Userinfo
        Userinfo userinfo = this.userinfoService.getUserinfo();
        // 已认证状态
        if (userinfo.getIsRealAuth()) {
            //根据userinfo上的realAuthId得到实名认证对象,并放到model;
            RealAuth realAuth = this.realAuthService.get(userinfo.getRealAuthId());
            model.addAttribute("realAuth", realAuth);
            model.addAttribute("auditing", false);//设置审核成功
            model.addAttribute("userinfo", this.userinfoService.getUserinfo());
            return "realAuth_result";
        }
        else {
            //已认证，待审核状态
            if (userinfo.getRealAuthId() != null) {
                model.addAttribute("auditing", true);
                return "realAuth_result";
            }
            //未认证，未审核状态
            else {
                return "realAuth";
            }
        }
    }


    /**
     *  图片上传
	 *  千万不要加requiredLogin
	 */
    @ResponseBody
	@PostMapping("realAuthUpload")
	public HashMap<String, String> realAuthUpload(MultipartFile file) {
		// 先得到basepath
		String basePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		String filePath = "/upload/" + fileName;
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("file_path",filePath);
        return stringStringHashMap;
	}

	/**
	 *  申请实名认证
	 */
	@RequireLogin
	@ResponseBody
    @PostMapping("realAuth_save")
	public JsonResult realAuthSave(RealAuth realAuth) {
		realAuthService.apply(realAuth);
		return new JsonResult();
	}
}
