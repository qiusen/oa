package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Dict;
import com.dihaitech.oa.util.Page;

/**
 * 字典 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
public interface IDictService {
	/**
	 * 查询 Dict Page 对象
	 * @param dict
	 * @param pageSize
	 * @return
	 */
	public Page selectDict(Dict dict, int pageSize);

	/**
	 * 分页查找 字典
	 * @param dict
	 * @param page
	 * @return
	 */
	public List<Dict> selectDict(Dict dict, Page page);
	
	/**
	 * 查询所有 字典
	 * @return
	 */
	public List<Dict> selectAll();
	
	/**
	 * 根据 ID 查找 字典 
	 * @param dict
	 * @return
	 */
	public Dict selectDictById(Dict dict);
	
	/**
	 * 添加 字典 
	 * @param dict
	 * @return
	 */
	public int addSave(Dict dict);
	
	/**
	 * 修改 字典 
	 * @param dict
	 * @return
	 */
	public int editSave(Dict dict);
	
	/**
	 * 根据 ID 删除 字典 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
