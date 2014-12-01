package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Province;
import com.dihaitech.oa.util.Page;

/**
 * 省 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
public interface IProvinceService {
	/**
	 * 查询 Province Page 对象
	 * @param province
	 * @param pageSize
	 * @return
	 */
	public Page selectProvince(Province province, int pageSize);

	/**
	 * 分页查找 省
	 * @param province
	 * @param page
	 * @return
	 */
	public List<Province> selectProvince(Province province, Page page);
	
	/**
	 * 查询所有 省
	 * @return
	 */
	public List<Province> selectAll();
	
	/**
	 * 根据 ID 查找 省 
	 * @param province
	 * @return
	 */
	public Province selectProvinceById(Province province);
	
	/**
	 * 添加 省 
	 * @param province
	 * @return
	 */
	public int addSave(Province province);
	
	/**
	 * 修改 省 
	 * @param province
	 * @return
	 */
	public int editSave(Province province);
	
	/**
	 * 根据 ID 删除 省 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据CODES 查询所有省
	 * @param province
	 * @return
	 */
	public List<Province> selectProvinceByCodes(Province province);
}
