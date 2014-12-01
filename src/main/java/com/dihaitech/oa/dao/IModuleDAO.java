package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Module;

/**
 * 模块 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-19
 */
public interface IModuleDAO {

	/**
	 * 根据条件查询模块 条数
	 * 
	 * @param module
	 * @return
	 */
	public Long getModuleCount(Module module);

	/**
	 * 分页查找模块
	 * 
	 * @param module
	 * @param page
	 * @return
	 */
	public List<Module> selectModuleLike(Module module);

	/**
	 * 添加模块
	 * 
	 * @param module
	 * @return
	 */
	public int addSaveModule(Module module);

	/**
	 * 根据ID获取指定的模块信息
	 * 
	 * @param module
	 * @return
	 */
	public Module selectModuleById(Module module);

	/**
	 * 修改模块
	 * 
	 * @param module
	 * @return
	 */
	public int editSaveModule(Module module);

	/**
	 * 根据ID删除指定的模块
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有模块
	 * 
	 * @return
	 */
	public List<Module> selectAll();
	
	/**
	 * 根据ID拼接字符串查询
	 * @param module
	 * @return
	 */
	public List<Module> selectModuleByIdStr(Module module);
	
	/**
	 * 根据状态获取所有模块
	 * @return
	 */
	public List<Module> selectAllByStatus(Module module);
}
