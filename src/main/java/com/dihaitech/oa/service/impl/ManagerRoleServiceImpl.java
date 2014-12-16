package com.dihaitech.oa.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.dao.IManagerRoleDAO;
import com.dihaitech.oa.model.ManagerRole;
import com.dihaitech.oa.service.IManagerRoleService;

/**
 * 用户角色 业务接口 IManagerRoleService 实现类
 * 
 * @author cg
 *
 * @date 2014-12-16
 */
public class ManagerRoleServiceImpl implements IManagerRoleService {

	@Resource
	private IManagerRoleDAO managerRoleDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#addSave(com.dihaitech.oa.model.ManagerRole)
	 */
	public int addSave(ManagerRole managerRole) {
		return managerRoleDAO.addSaveManagerRole(managerRole);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return managerRoleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#editSave(com.dihaitech.oa.model.ManagerRole)
	 */
	public int editSave(ManagerRole managerRole) {
		return managerRoleDAO.editSaveManagerRole(managerRole);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.IManagerRoleService#selectAll()
	 */
	public List<ManagerRole> selectAll() {
		return managerRoleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#selectManagerRole(com.dihaitech.oa.model.ManagerRole, int)
	 */
	public Page selectManagerRole(ManagerRole managerRole, int pageSize) {
		return new Page(managerRoleDAO.getManagerRoleCount(managerRole), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#selectManagerRole(com.dihaitech.oa.model.ManagerRole, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<ManagerRole> selectManagerRole(ManagerRole managerRole, Page page) {
		managerRole.setStart(page.getFirstItemPos());
		managerRole.setPageSize(page.getPageSize());
		return managerRoleDAO.selectManagerRoleLike(managerRole);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#selectManagerRoleById(com.dihaitech.oa.model.ManagerRole)
	 */
	public ManagerRole selectManagerRoleById(ManagerRole managerRole) {
		return managerRoleDAO.selectManagerRoleById(managerRole);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#selectCountByRoleId(com.dihaitech.oa.model.ManagerRole)
	 */
	@Override
	public long selectCountByRoleId(ManagerRole managerRole) {
		// TODO Auto-generated method stub
		return managerRoleDAO.selectCountByRoleId(managerRole);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.IManagerRoleService#selectManagerRoleByEmail(com.dihaitech.oa.model.ManagerRole)
	 */
	@Override
	public ManagerRole selectManagerRoleByEmail(ManagerRole managerRole) {
		// TODO Auto-generated method stub
		return managerRoleDAO.selectManagerRoleByEmail(managerRole);
	}
}
