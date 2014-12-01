package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Role;

/**
 * 角色 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-18
 */
public interface IRoleDAO {

	/**
	 * 根据条件查询角色 条数
	 * 
	 * @param role
	 * @return
	 */
	public Long getRoleCount(Role role);

	/**
	 * 分页查找角色
	 * 
	 * @param role
	 * @param page
	 * @return
	 */
	public List<Role> selectRoleLike(Role role);

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	public int addSaveRole(Role role);

	/**
	 * 根据ID获取指定的角色信息
	 * 
	 * @param role
	 * @return
	 */
	public Role selectRoleById(Role role);

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	public int editSaveRole(Role role);

	/**
	 * 根据ID删除指定的角色
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	public List<Role> selectAll();
	
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
