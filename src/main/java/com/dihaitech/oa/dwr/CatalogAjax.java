package com.dihaitech.oa.dwr;

import java.util.List;

import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.service.ICatalogService;

/**
 * 目录AJAX
 * @author nathan
 *
 */
public class CatalogAjax {

	private ICatalogService catalogService;
	
	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}

	/**
	 * 根据菜单ID获取所有目录D
	 * @param menuId
	 * @return
	 */
	public List<Catalog> selectCatalogByMenu(int menuId){
		List<Catalog> catalogList = null;
		
		Catalog catalog = new Catalog();
		catalog.setMenuId(menuId);
		catalog.setStatus(1);
		catalogList = catalogService.selectAllByMenuStatus(catalog);
		System.out.println("----------" + catalogList.size());
		return catalogList;
	}
}
