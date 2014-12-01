package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Province;

/**
 * 省 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-20
 */
public interface IProvinceDAO {

	/**
	 * 根据条件查询省 条数
	 * 
	 * @param province
	 * @return
	 */
	public Long getProvinceCount(Province province);

	/**
	 * 分页查找省
	 * 
	 * @param province
	 * @param page
	 * @return
	 */
	public List<Province> selectProvinceLike(Province province);

	/**
	 * 添加省
	 * 
	 * @param province
	 * @return
	 */
	public int addSaveProvince(Province province);

	/**
	 * 根据ID获取指定的省信息
	 * 
	 * @param province
	 * @return
	 */
	public Province selectProvinceById(Province province);

	/**
	 * 修改省
	 * 
	 * @param province
	 * @return
	 */
	public int editSaveProvince(Province province);

	/**
	 * 根据ID删除指定的省
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有省
	 * 
	 * @return
	 */
	public List<Province> selectAll();
	
	/**
	 * 根据IDS 查询所有省
	 * @param province
	 * @return
	 */
	public List<Province> selectProvinceByCodes(Province province);
}
