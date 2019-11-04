package com.zy.p2p.base.service;

import com.zy.p2p.base.domain.UserFile;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.UserFileQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * 风控资料服务
 * 
 * @author Administrator
 * 
 */
public interface IUserFileService {

	/**
	 * 上传一个风控资料对象
	 * 
	 * @param fileName
	 */
	void apply(String fileName);

	/**
	 * 列出一个用户风控资料对象 hasType:如果为true,选择有类型的,如果为false,选择没有类型的
	 * 
	 * @return
	 */
	List<UserFile> listFilesByHasType(Long logininfoId, boolean hasType);

	/**
	 * 批量的处理用户风控资料类型的选择
	 */
	void batchUpdateFileType(Long[] ids, Long[] fileTypes);

	PageResult query(UserFileQueryObject qo);

	List<UserFile> queryForList(UserFileQueryObject qo);

	/**
	 * 审核
	 * 
	 * @param id
	 * @param score
	 * @param remark
	 * @param state
	 */
	void audit(Long id, int score, String remark, int state);

}
