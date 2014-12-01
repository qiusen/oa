package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.IModuleDAO;
import com.dihaitech.oa.model.Module;
import com.dihaitech.oa.service.IModuleService;
import com.dihaitech.oa.util.Page;

/**
 * 模块 业务接口 IModuleService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-19
 */
public class ModuleServiceImpl implements IModuleService {

	@Resource
	private IModuleDAO moduleDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#addSave(com.dihaitech.acomp.model.Module)
	 */
	public int addSave(Module module) {
		return moduleDAO.addSaveModule(module);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return moduleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#editSave(com.dihaitech.acomp.model.Module)
	 */
	public int editSave(Module module) {
		return moduleDAO.editSaveModule(module);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IModuleService#selectAll()
	 */
	public List<Module> selectAll() {
		return moduleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#selectModule(com.dihaitech.acomp.model.Module, int)
	 */
	public Page selectModule(Module module, int pageSize) {
		return new Page(moduleDAO.getModuleCount(module), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#selectModule(com.dihaitech.acomp.model.Module, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Module> selectModule(Module module, Page page) {
		module.setStart(page.getFirstItemPos());
		module.setPageSize(page.getPageSize());
		return moduleDAO.selectModuleLike(module);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#selectModuleById(com.dihaitech.acomp.model.Module)
	 */
	public Module selectModuleById(Module module) {
		return moduleDAO.selectModuleById(module);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#selectModuleByIdStr(com.dihaitech.acomp.model.Module)
	 */
	@Override
	public List<Module> selectModuleByIdStr(Module module) {
		// TODO Auto-generated method stub
		return moduleDAO.selectModuleByIdStr(module);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IModuleService#selectAllByStatus(com.dihaitech.acomp.model.Module)
	 */
	@Override
	public List<Module> selectAllByStatus(Module module) {
		// TODO Auto-generated method stub
		return moduleDAO.selectAllByStatus(module);
	}
}
