package com.zy.p2p.base.service;

import com.zy.p2p.base.domain.SystemDictionary;
import com.zy.p2p.base.domain.SystemDictionaryItem;
import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;


/**
 * 数据字典相关服务
 * 
 * @author Administrator
 * 
 */
public interface ISystemDictionaryService {

	/**
	 * 数据字典分类分页查询
	 * 
	 * @return
	 */
	PageResult queryDics(SystemDictionaryQueryObject qo);

	/**
	 * 查询所有的数据字典明细
	 */
	List<SystemDictionary> listAllDics();

	/**
	 * 根据数据字典分类 查询明细
	 * 
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> listByParentSn(String sn);

	/**
	 * 修改或者保存数据字典分类
	 * 
	 * @param dictionary
	 */
	void saveOrUpdateDic(SystemDictionary dictionary);

	/**
	 * 数据字典明细的分页查询
	 */
	PageResult queryItems(SystemDictionaryQueryObject qo);

	/**
	 * 修改或者保存 数据字典明细
	 */
	void saveOrUpdateItem(SystemDictionaryItem item);
	/**
	 * 删除 数据字典明细
	 */
    void deleteDic(String id);
}
