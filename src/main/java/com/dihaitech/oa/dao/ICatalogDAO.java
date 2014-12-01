package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Catalog;

/**
 * 目录 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-18
 */
public interface ICatalogDAO {

	/**
	 * 根据条件查询目录 条数
	 * 
	 * @param catalog
	 * @return
	 */
	public Long getCatalogCount(Catalog catalog);

	/**
	 * 分页查找目录
	 * 
	 * @param catalog
	 * @param page
	 * @return
	 */
	public List<Catalog> selectCatalogLike(Catalog catalog);

	/**
	 * 添加目录
	 * 
	 * @param catalog
	 * @return
	 */
	public int addSaveCatalog(Catalog catalog);

	/**
	 * 根据ID获取指定的目录信息
	 * 
	 * @param catalog
	 * @return
	 */
	public Catalog selectCatalogById(Catalog catalog);

	/**
	 * 修改目录
	 * 
	 * @param catalog
	 * @return
	 */
	public int editSaveCatalog(Catalog catalog);

	/**
	 * 根据ID删除指定的目录
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有目录
	 * 
	 * @return
	 */
	public List<Catalog> selectAll();
	
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
