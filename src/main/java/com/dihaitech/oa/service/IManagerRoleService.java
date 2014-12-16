package com.dihaitech.oa.service;

import java.util.List;


import com.dihaitech.oa.model.ManagerRole;
import com.dihaitech.oa.util.Page;

/**
 * 用户角色 业务接口
 * 
 * @author cg
 *
 * @date 2014-12-16
 */
public interface IManagerRoleService {
	/**
	 * 查询 ManagerRole Page 对象
	 * @param managerRole
	 * @param pageSize
	 * @return
	 */
	public Page selectManagerRole(ManagerRole managerRole, int pageSize);

	/**
	 * 分页查找 用户角色
	 * @param managerRole
	 * @param page
	 * @return
	 */
	public List<ManagerRole> selectManagerRole(ManagerRole managerRole, Page page);
	
	/**
	 * 查询所有 用户角色
	 * @return
	 */
	public List<ManagerRole> selectAll();
	
	/**
	 * 根据 ID 查找 用户角色 
	 * @param managerRole
	 * @return
	 */
	public ManagerRole selectManagerRoleById(ManagerRole managerRole);
	
	/**
	 * 添加 用户角色 
	 * @param managerRole
	 * @return
	 */
	public int addSave(ManagerRole managerRole);
	
	/**
	 * 修改 用户角色 
	 * @param managerRole
	 * @return
	 */
	public int editSave(ManagerRole managerRole);
	
	/**
	 * 根据 ID 删除 用户角色 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
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
