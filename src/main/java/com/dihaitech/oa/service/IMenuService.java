package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.util.Page;

/**
 * 菜单 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public interface IMenuService {
	/**
	 * 查询 Menu Page 对象
	 * @param menu
	 * @param pageSize
	 * @return
	 */
	public Page selectMenu(Menu menu, int pageSize);

	/**
	 * 分页查找 菜单
	 * @param menu
	 * @param page
	 * @return
	 */
	public List<Menu> selectMenu(Menu menu, Page page);
	
	/**
	 * 查询所有 菜单
	 * @return
	 */
	public List<Menu> selectAll();
	
	/**
	 * 根据 ID 查找 菜单 
	 * @param menu
	 * @return
	 */
	public Menu selectMenuById(Menu menu);
	
	/**
	 * 添加 菜单 
	 * @param menu
	 * @return
	 */
	public int addSave(Menu menu);
	
	/**
	 * 修改 菜单 
	 * @param menu
	 * @return
	 */
	public int editSave(Menu menu);
	
	/**
	 * 根据 ID 删除 菜单 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据IDS和状态查询菜单
	 * @param menu
	 * @return
	 */
	public List<Menu> selectMenuByIdsStatus(Menu menu);
	
	/**
	 * 根据状态查询所有菜单
	 * @param menu
	 * @return
	 */
	public List<Menu> selectAllByStatus(Menu menu);
}
