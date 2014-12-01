package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Dict;

/**
 * 字典 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-25
 */
public interface IDictDAO {

	/**
	 * 根据条件查询字典 条数
	 * 
	 * @param dict
	 * @return
	 */
	public Long getDictCount(Dict dict);

	/**
	 * 分页查找字典
	 * 
	 * @param dict
	 * @param page
	 * @return
	 */
	public List<Dict> selectDictLike(Dict dict);

	/**
	 * 添加字典
	 * 
	 * @param dict
	 * @return
	 */
	public int addSaveDict(Dict dict);

	/**
	 * 根据ID获取指定的字典信息
	 * 
	 * @param dict
	 * @return
	 */
	public Dict selectDictById(Dict dict);

	/**
	 * 修改字典
	 * 
	 * @param dict
	 * @return
	 */
	public int editSaveDict(Dict dict);

	/**
	 * 根据ID删除指定的字典
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有字典
	 * 
	 * @return
	 */
	public List<Dict> selectAll();
}
