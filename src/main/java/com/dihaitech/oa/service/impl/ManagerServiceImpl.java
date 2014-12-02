package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IManagerDAO;
import com.dihaitech.oa.model.Manager;
import com.dihaitech.oa.service.IManagerService;
import com.dihaitech.oa.util.Page;

/**
 * 管理员 业务接口 IManagerService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class ManagerServiceImpl implements IManagerService {

	@Resource
	private IManagerDAO managerDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#addSave(com.dihaitech.oa.model.Manager)
	 */
	public int addSave(Manager manager) {
		return managerDAO.addSaveManager(manager);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return managerDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#editSave(com.dihaitech.oa.model.Manager)
	 */
	public int editSave(Manager manager) {
		return managerDAO.editSaveManager(manager);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.IManagerService#selectAll()
	 */
	public List<Manager> selectAll() {
		return managerDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#selectManager(com.dihaitech.oa.model.Manager, int)
	 */
	public Page selectManager(Manager manager, int pageSize) {
		return new Page(managerDAO.getManagerCount(manager), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#selectManager(com.dihaitech.oa.model.Manager, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<Manager> selectManager(Manager manager, Page page) {
		manager.setStart(page.getFirstItemPos());
		manager.setPageSize(page.getPageSize());
		return managerDAO.selectManagerLike(manager);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#selectManagerById(com.dihaitech.oa.model.Manager)
	 */
	public Manager selectManagerById(Manager manager) {
		return managerDAO.selectManagerById(manager);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#login(com.dihaitech.oa.model.Manager)
	 */
	@Override
	public Manager login(Manager manager) {
		// TODO Auto-generated method stub
		return managerDAO.loginByUsernamePassword(manager);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#editSaveManager(com.dihaitech.oa.model.Manager)
	 */
	@Override
	public int editSaveManager(Manager manager) {
		// TODO Auto-generated method stub
		return this.managerDAO.editSaveManagerById(manager);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#editSaveUser(com.dihaitech.oa.model.Manager)
	 */
	@Override
	public int editSaveUser(Manager manager) {
		// TODO Auto-generated method stub
		return this.managerDAO.editSaveUser(manager);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#editSavePassword(com.dihaitech.oa.model.Manager)
	 */
	@Override
	public int editSavePassword(Manager manager) {
		// TODO Auto-generated method stub
		return this.managerDAO.editSavePassword(manager);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerService#selectCountByRoleId(com.dihaitech.oa.model.Manager)
	 */
	@Override
	public Long selectCountByRoleId(Manager manager) {
		// TODO Auto-generated method stub
		return this.managerDAO.getManagerCount(manager);
	}


}
