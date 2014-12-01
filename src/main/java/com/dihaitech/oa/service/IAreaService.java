package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Area;
import com.dihaitech.oa.util.Page;

/**
 * 区 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-21
 */
public interface IAreaService {
	/**
	 * 查询 Area Page 对象
	 * @param area
	 * @param pageSize
	 * @return
	 */
	public Page selectArea(Area area, int pageSize);

	/**
	 * 分页查找 区
	 * @param area
	 * @param page
	 * @return
	 */
	public List<Area> selectArea(Area area, Page page);
	
	/**
	 * 查询所有 区
	 * @return
	 */
	public List<Area> selectAll();
	
	/**
	 * 根据 ID 查找 区 
	 * @param area
	 * @return
	 */
	public Area selectAreaById(Area area);
	
	/**
	 * 添加 区 
	 * @param area
	 * @return
	 */
	public int addSave(Area area);
	
	/**
	 * 修改 区 
	 * @param area
	 * @return
	 */
	public int editSave(Area area);
	
	/**
	 * 根据 ID 删除 区 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
