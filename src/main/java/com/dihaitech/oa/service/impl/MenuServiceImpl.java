package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IMenuDAO;
import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.service.IMenuService;
import com.dihaitech.oa.util.Page;

/**
 * 菜单 业务接口 IMenuService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class MenuServiceImpl implements IMenuService {

	@Resource
	private IMenuDAO menuDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#addSave(com.dihaitech.acomp.model.Menu)
	 */
	public int addSave(Menu menu) {
		return menuDAO.addSaveMenu(menu);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return menuDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#editSave(com.dihaitech.acomp.model.Menu)
	 */
	public int editSave(Menu menu) {
		return menuDAO.editSaveMenu(menu);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IMenuService#selectAll()
	 */
	public List<Menu> selectAll() {
		return menuDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#selectMenu(com.dihaitech.acomp.model.Menu, int)
	 */
	public Page selectMenu(Menu menu, int pageSize) {
		return new Page(menuDAO.getMenuCount(menu), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#selectMenu(com.dihaitech.acomp.model.Menu, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Menu> selectMenu(Menu menu, Page page) {
		menu.setStart(page.getFirstItemPos());
		menu.setPageSize(page.getPageSize());
		return menuDAO.selectMenuLike(menu);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#selectMenuById(com.dihaitech.acomp.model.Menu)
	 */
	public Menu selectMenuById(Menu menu) {
		return menuDAO.selectMenuById(menu);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#selectMenuByIdsStatus(com.dihaitech.acomp.model.Menu)
	 */
	@Override
	public List<Menu> selectMenuByIdsStatus(Menu menu) {
		// TODO Auto-generated method stub
		return menuDAO.selectMenuByIdsStatus(menu);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IMenuService#selectAllByStatus(com.dihaitech.acomp.model.Menu)
	 */
	@Override
	public List<Menu> selectAllByStatus(Menu menu) {
		// TODO Auto-generated method stub
		return menuDAO.selectAllByStatus(menu);
	}
}
