package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IRoleDAO;
import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.service.IRoleService;
import com.dihaitech.oa.util.Page;

/**
 * 角色 业务接口 IRoleService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDAO roleDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#addSave(com.dihaitech.acomp.model.Role)
	 */
	public int addSave(Role role) {
		return roleDAO.addSaveRole(role);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return roleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#editSave(com.dihaitech.acomp.model.Role)
	 */
	public int editSave(Role role) {
		return roleDAO.editSaveRole(role);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IRoleService#selectAll()
	 */
	public List<Role> selectAll() {
		return roleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#selectRole(com.dihaitech.acomp.model.Role, int)
	 */
	public Page selectRole(Role role, int pageSize) {
		return new Page(roleDAO.getRoleCount(role), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#selectRole(com.dihaitech.acomp.model.Role, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Role> selectRole(Role role, Page page) {
		role.setStart(page.getFirstItemPos());
		role.setPageSize(page.getPageSize());
		return roleDAO.selectRoleLike(role);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#selectRoleById(com.dihaitech.acomp.model.Role)
	 */
	public Role selectRoleById(Role role) {
		return roleDAO.selectRoleById(role);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#selectAllByStatus(com.dihaitech.acomp.model.Role)
	 */
	@Override
	public List<Role> selectAllByStatus(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.selectAllByStatus(role);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IRoleService#editRights(com.dihaitech.acomp.model.Role)
	 */
	@Override
	public int editRights(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.editRights(role);
	}
}
