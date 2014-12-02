package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IProvinceDAO;
import com.dihaitech.oa.model.Province;
import com.dihaitech.oa.service.IProvinceService;
import com.dihaitech.oa.util.Page;

/**
 * 省 业务接口 IProvinceService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
public class ProvinceServiceImpl implements IProvinceService {

	@Resource
	private IProvinceDAO provinceDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#addSave(com.dihaitech.oa.model.Province)
	 */
	public int addSave(Province province) {
		return provinceDAO.addSaveProvince(province);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return provinceDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#editSave(com.dihaitech.oa.model.Province)
	 */
	public int editSave(Province province) {
		return provinceDAO.editSaveProvince(province);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.IProvinceService#selectAll()
	 */
	public List<Province> selectAll() {
		return provinceDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#selectProvince(com.dihaitech.oa.model.Province, int)
	 */
	public Page selectProvince(Province province, int pageSize) {
		return new Page(provinceDAO.getProvinceCount(province), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#selectProvince(com.dihaitech.oa.model.Province, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<Province> selectProvince(Province province, Page page) {
		province.setStart(page.getFirstItemPos());
		province.setPageSize(page.getPageSize());
		return provinceDAO.selectProvinceLike(province);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#selectProvinceById(com.dihaitech.oa.model.Province)
	 */
	public Province selectProvinceById(Province province) {
		return provinceDAO.selectProvinceById(province);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IProvinceService#selectProvinceByCodes(com.dihaitech.oa.model.Province)
	 */
	@Override
	public List<Province> selectProvinceByCodes(Province province) {
		// TODO Auto-generated method stub
		return provinceDAO.selectProvinceByCodes(province);
	}
}
