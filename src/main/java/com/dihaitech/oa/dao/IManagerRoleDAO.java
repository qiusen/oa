package com.dihaitech.oa.dao;

import java.util.List;


import com.dihaitech.oa.model.ManagerRole;

/**
 * 用户角色 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-12-16
 */
public interface IManagerRoleDAO {

	/**
	 * 根据条件查询用户角色 条数
	 * 
	 * @param managerRole
	 * @return
	 */
	public Long getManagerRoleCount(ManagerRole managerRole);

	/**
	 * 分页查找用户角色
	 * 
	 * @param managerRole
	 * @param page
	 * @return
	 */
	public List<ManagerRole> selectManagerRoleLike(ManagerRole managerRole);

	/**
	 * 添加用户角色
	 * 
	 * @param managerRole
	 * @return
	 */
	public int addSaveManagerRole(ManagerRole managerRole);

	/**
	 * 根据ID获取指定的用户角色信息
	 * 
	 * @param managerRole
	 * @return
	 */
	public ManagerRole selectManagerRoleById(ManagerRole managerRole);

	/**
	 * 修改用户角色
	 * 
	 * @param managerRole
	 * @return
	 */
	public int editSaveManagerRole(ManagerRole managerRole);

	/**
	 * 根据ID删除指定的用户角色
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有用户角色
	 * 
	 * @return
	 */
	public List<ManagerRole> selectAll();
	
	/**
	 * 根据角色ID查询数量
	 * @param managerRole
	 * @return
	 */
	public long selectCountByRoleId(ManagerRole managerRole);
	
	/**
	 * 根据用户EMAIL获取权限信息
	 * @param managerRole
	 * @return
	 */
	public ManagerRole selectManagerRoleByEmail(ManagerRole managerRole);
}
