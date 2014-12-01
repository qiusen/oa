package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.util.Page;

/**
 * 目录 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public interface ICatalogService {
	/**
	 * 查询 Catalog Page 对象
	 * @param catalog
	 * @param pageSize
	 * @return
	 */
	public Page selectCatalog(Catalog catalog, int pageSize);

	/**
	 * 分页查找 目录
	 * @param catalog
	 * @param page
	 * @return
	 */
	public List<Catalog> selectCatalog(Catalog catalog, Page page);
	
	/**
	 * 查询所有 目录
	 * @return
	 */
	public List<Catalog> selectAll();
	
	/**
	 * 根据 ID 查找 目录 
	 * @param catalog
	 * @return
	 */
	public Catalog selectCatalogById(Catalog catalog);
	
	/**
	 * 添加 目录 
	 * @param catalog
	 * @return
	 */
	public int addSave(Catalog catalog);
	
	/**
	 * 修改 目录 
	 * @param catalog
	 * @return
	 */
	public int editSave(Catalog catalog);
	
	/**
	 * 根据 ID 删除 目录 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据IDS和状态获取目录
	 * @param catalog
	 * @return
	 */
	public List<Catalog> selectCatalogByIdsStatus(Catalog catalog);
	
	/**
	 * 根据状态获取所有目录
	 * @param catalog
	 * @return
	 */
	public List<Catalog> selectAllByStatus(Catalog catalog);
	
	/**
	 * 根据菜单和状态获取目录
	 * @param catalog
	 * @return
	 */
	public List<Catalog>  selectAllByMenuStatus(Catalog catalog);
	
}
