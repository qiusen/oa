package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Module;
import com.dihaitech.oa.util.Page;

/**
 * 模块 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-19
 */
public interface IModuleService {
	/**
	 * 查询 Module Page 对象
	 * @param module
	 * @param pageSize
	 * @return
	 */
	public Page selectModule(Module module, int pageSize);

	/**
	 * 分页查找 模块
	 * @param module
	 * @param page
	 * @return
	 */
	public List<Module> selectModule(Module module, Page page);
	
	/**
	 * 查询所有 模块
	 * @return
	 */
	public List<Module> selectAll();
	
	/**
	 * 根据 ID 查找 模块 
	 * @param module
	 * @return
	 */
	public Module selectModuleById(Module module);
	
	/**
	 * 添加 模块 
	 * @param module
	 * @return
	 */
	public int addSave(Module module);
	
	/**
	 * 修改 模块 
	 * @param module
	 * @return
	 */
	public int editSave(Module module);
	
	/**
	 * 根据 ID 删除 模块 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
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
