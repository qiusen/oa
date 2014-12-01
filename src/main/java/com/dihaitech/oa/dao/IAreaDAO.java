package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Area;

/**
 * 区 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-21
 */
public interface IAreaDAO {

	/**
	 * 根据条件查询区 条数
	 * 
	 * @param area
	 * @return
	 */
	public Long getAreaCount(Area area);

	/**
	 * 分页查找区
	 * 
	 * @param area
	 * @param page
	 * @return
	 */
	public List<Area> selectAreaLike(Area area);

	/**
	 * 添加区
	 * 
	 * @param area
	 * @return
	 */
	public int addSaveArea(Area area);

	/**
	 * 根据ID获取指定的区信息
	 * 
	 * @param area
	 * @return
	 */
	public Area selectAreaById(Area area);

	/**
	 * 修改区
	 * 
	 * @param area
	 * @return
	 */
	public int editSaveArea(Area area);

	/**
	 * 根据ID删除指定的区
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有区
	 * 
	 * @return
	 */
	public List<Area> selectAll();
}
