package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.util.Page;

/**
 * 角色 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public interface IRoleService {
	/**
	 * 查询 Role Page 对象
	 * @param role
	 * @param pageSize
	 * @return
	 */
	public Page selectRole(Role role, int pageSize);

	/**
	 * 分页查找 角色
	 * @param role
	 * @param page
	 * @return
	 */
	public List<Role> selectRole(Role role, Page page);
	
	/**
	 * 查询所有 角色
	 * @return
	 */
	public List<Role> selectAll();
	
	/**
	 * 根据 ID 查找 角色 
	 * @param role
	 * @return
	 */
	public Role selectRoleById(Role role);
	
	/**
	 * 添加 角色 
	 * @param role
	 * @return
	 */
	public int addSave(Role role);
	
	/**
	 * 修改 角色 
	 * @param role
	 * @return
	 */
	public int editSave(Role role);
	
	/**
	 * 根据 ID 删除 角色 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据状态查询所有角色
	 * @param role
	 * @return
	 */
	public List<Role> selectAllByStatus(Role role);
	
	/**
	 * 修改 权限 
	 * @param role
	 * @return
	 */
	public int editRights(Role role);
}
