package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.City;

/**
 * 市 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-20
 */
public interface ICityDAO {

	/**
	 * 根据条件查询市 条数
	 * 
	 * @param city
	 * @return
	 */
	public Long getCityCount(City city);

	/**
	 * 分页查找市
	 * 
	 * @param city
	 * @param page
	 * @return
	 */
	public List<City> selectCityLike(City city);

	/**
	 * 添加市
	 * 
	 * @param city
	 * @return
	 */
	public int addSaveCity(City city);

	/**
	 * 根据ID获取指定的市信息
	 * 
	 * @param city
	 * @return
	 */
	public City selectCityById(City city);

	/**
	 * 修改市
	 * 
	 * @param city
	 * @return
	 */
	public int editSaveCity(City city);

	/**
	 * 根据ID删除指定的市
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有市
	 * 
	 * @return
	 */
	public List<City> selectAll();
	
	/**
	 * 根据省CODE 查询下属市
	 * @param city
	 * @return
	 */
	public List<City> selectCityByProvinceCode(City city);
	
	/**
	 * 根据CODES获取所有市
	 * @param city
	 * @return
	 */
	public List<City> selectCityByCodes(City city);
	
	/**
	 * 根据市CODE，查询市
	 * @param city
	 * @return
	 */
	public City selectCityByCode(City city);
}
