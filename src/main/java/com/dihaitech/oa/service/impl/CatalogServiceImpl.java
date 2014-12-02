package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.ICatalogDAO;
import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.service.ICatalogService;
import com.dihaitech.oa.util.Page;

/**
 * 目录 业务接口 ICatalogService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class CatalogServiceImpl implements ICatalogService {

	@Resource
	private ICatalogDAO catalogDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#addSave(com.dihaitech.oa.model.Catalog)
	 */
	public int addSave(Catalog catalog) {
		return catalogDAO.addSaveCatalog(catalog);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return catalogDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#editSave(com.dihaitech.oa.model.Catalog)
	 */
	public int editSave(Catalog catalog) {
		return catalogDAO.editSaveCatalog(catalog);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.ICatalogService#selectAll()
	 */
	public List<Catalog> selectAll() {
		return catalogDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectCatalog(com.dihaitech.oa.model.Catalog, int)
	 */
	public Page selectCatalog(Catalog catalog, int pageSize) {
		return new Page(catalogDAO.getCatalogCount(catalog), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectCatalog(com.dihaitech.oa.model.Catalog, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<Catalog> selectCatalog(Catalog catalog, Page page) {
		catalog.setStart(page.getFirstItemPos());
		catalog.setPageSize(page.getPageSize());
		return catalogDAO.selectCatalogLike(catalog);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectCatalogById(com.dihaitech.oa.model.Catalog)
	 */
	public Catalog selectCatalogById(Catalog catalog) {
		return catalogDAO.selectCatalogById(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectCatalogByIdsStatus(com.dihaitech.oa.model.Catalog)
	 */
	@Override
	public List<Catalog> selectCatalogByIdsStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectCatalogByIdsStatus(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectAllByStatus(com.dihaitech.oa.model.Catalog)
	 */
	@Override
	public List<Catalog> selectAllByStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectAllByStatus(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ICatalogService#selectAllByMenuStatus(com.dihaitech.oa.model.Catalog)
	 */
	@Override
	public List<Catalog> selectAllByMenuStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectAllByMenuStatus(catalog);
	}
}
