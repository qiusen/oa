package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Menu;

/**
 * 菜单 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-18
 */
public interface IMenuDAO {

	/**
	 * 根据条件查询菜单 条数
	 * 
	 * @param menu
	 * @return
	 */
	public Long getMenuCount(Menu menu);

	/**
	 * 分页查找菜单
	 * 
	 * @param menu
	 * @param page
	 * @return
	 */
	public List<Menu> selectMenuLike(Menu menu);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @return
	 */
	public int addSaveMenu(Menu menu);

	/**
	 * 根据ID获取指定的菜单信息
	 * 
	 * @param menu
	 * @return
	 */
	public Menu selectMenuById(Menu menu);

	/**
	 * 修改菜单
	 * 
	 * @param menu
	 * @return
	 */
	public int editSaveMenu(Menu menu);

	/**
	 * 根据ID删除指定的菜单
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有菜单
	 * 
	 * @return
	 */
	public List<Menu> selectAll();
	
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
