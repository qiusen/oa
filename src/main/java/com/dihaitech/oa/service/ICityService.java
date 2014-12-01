package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.City;
import com.dihaitech.oa.util.Page;

/**
 * 市 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
public interface ICityService {
	/**
	 * 查询 City Page 对象
	 * @param city
	 * @param pageSize
	 * @return
	 */
	public Page selectCity(City city, int pageSize);

	/**
	 * 分页查找 市
	 * @param city
	 * @param page
	 * @return
	 */
	public List<City> selectCity(City city, Page page);
	
	/**
	 * 查询所有 市
	 * @return
	 */
	public List<City> selectAll();
	
	/**
	 * 根据 ID 查找 市 
	 * @param city
	 * @return
	 */
	public City selectCityById(City city);
	
	/**
	 * 添加 市 
	 * @param city
	 * @return
	 */
	public int addSave(City city);
	
	/**
	 * 修改 市 
	 * @param city
	 * @return
	 */
	public int editSave(City city);
	
	/**
	 * 根据 ID 删除 市 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
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
