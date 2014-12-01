package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IDictDAO;
import com.dihaitech.oa.model.Dict;
import com.dihaitech.oa.service.IDictService;
import com.dihaitech.oa.util.Page;

/**
 * 字典 业务接口 IDictService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
public class DictServiceImpl implements IDictService {

	@Resource
	private IDictDAO dictDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#addSave(com.dihaitech.acomp.model.Dict)
	 */
	public int addSave(Dict dict) {
		return dictDAO.addSaveDict(dict);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return dictDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#editSave(com.dihaitech.acomp.model.Dict)
	 */
	public int editSave(Dict dict) {
		return dictDAO.editSaveDict(dict);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IDictService#selectAll()
	 */
	public List<Dict> selectAll() {
		return dictDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#selectDict(com.dihaitech.acomp.model.Dict, int)
	 */
	public Page selectDict(Dict dict, int pageSize) {
		return new Page(dictDAO.getDictCount(dict), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#selectDict(com.dihaitech.acomp.model.Dict, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Dict> selectDict(Dict dict, Page page) {
		dict.setStart(page.getFirstItemPos());
		dict.setPageSize(page.getPageSize());
		return dictDAO.selectDictLike(dict);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IDictService#selectDictById(com.dihaitech.acomp.model.Dict)
	 */
	public Dict selectDictById(Dict dict) {
		return dictDAO.selectDictById(dict);
	}
}
