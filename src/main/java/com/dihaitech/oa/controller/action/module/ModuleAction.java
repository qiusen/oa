package com.dihaitech.oa.controller.action.module;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.model.Module;
import com.dihaitech.oa.service.ICatalogService;
import com.dihaitech.oa.service.IMenuService;
import com.dihaitech.oa.service.IModuleService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;
import com.dihaitech.tserver.managercenter.Manager;

/**
 * 模块Action
 * 
 * @author cg
 *
 * @date 2014-08-19
 */
 @SuppressWarnings("serial")
public class ModuleAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(ModuleAction.class);
	private Module module = new Module();
	private IModuleService moduleService;
	
	private ICatalogService catalogService;
	
	private IMenuService menuService;
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	
	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	/* 
	 * 模块查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = moduleService.selectModule(module,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Module> resultList = this.moduleService.selectModule(module,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","moduleAction");
			
			//目录
			Catalog catalog = new Catalog();
			catalog.setStatus(1);
			List<Catalog> catalogList = catalogService.selectAllByStatus(catalog);
			this.getRequest().setAttribute("catalogList", catalogList);

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Module json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 模块
	 * @return
	 */
	public String add(){
		//菜单
		Menu menu = new Menu();
		menu.setStatus(1);
		List<Menu> menuList = menuService.selectAllByStatus(menu);
		this.getRequest().setAttribute("menuList", menuList);
		
		if(menuList!=null && menuList.size()>0){
			Menu menuTemp = menuList.get(0);
			//获取同菜单下所有目录
			Catalog catalog = new Catalog();
			catalog.setMenuId(menuTemp.getId());
			catalog.setStatus(1);
			List<Catalog> catalogList = catalogService.selectAllByMenuStatus(catalog);
			this.getRequest().setAttribute("catalogList", catalogList);
		}
		return "add";
	}
	
	/**
	 * 保存添加 模块
	 * @return
	 */
	public String addSave(){
		module.setCreatetime(new Date());
		moduleService.addSave(module);
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		//记录日志
		MDC.put("email", managerVO.getEmail());	//用户名
		MDC.put("nickname", managerVO.getNickname());	//昵称
		MDC.put("ip", this.getRealIP());	//IP
		MDC.put("act", "addModule");	//添加模块
		logger.info(managerVO.getNickname() + " 添加模块 " + module.getModulename());
		return "addSave";
	}
	
	/**
	 * 修改 模块
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			module.setId(id);
		}else{
			return null;
		}
		
		Module moduleVO = moduleService.selectModuleById(module);
		this.getRequest().setAttribute("module", moduleVO);
		
		//菜单
		Menu menu = new Menu();
		menu.setStatus(1);
		List<Menu> menuList = menuService.selectAllByStatus(menu);
		this.getRequest().setAttribute("menuList", menuList);
		
		//获取同菜单下所有目录
		Catalog catalog = new Catalog();
		catalog.setId(moduleVO.getCatalogId());
		Catalog catalogVO = catalogService.selectCatalogById(catalog);
		if(catalogVO!=null){
			this.getRequest().setAttribute("catalog", catalogVO);
			
			catalog.setMenuId(catalogVO.getMenuId());
			catalog.setStatus(1);
			
			List<Catalog> catalogList = catalogService.selectAllByMenuStatus(catalog);
			this.getRequest().setAttribute("catalogList", catalogList);
		}else{	//模块上线目录被删除
			Menu menuTemp = menuList.get(0);
			//获取同菜单下所有目录
			catalog.setMenuId(menuTemp.getId());
			catalog.setStatus(1);
			List<Catalog> catalogList = catalogService.selectAllByMenuStatus(catalog);
			if(catalogList!=null){
				this.getRequest().setAttribute("catalog", catalogList.get(0));
			}
			
			this.getRequest().setAttribute("catalogList", catalogList);
		}
				
		return "edit";
	}
	
	/**
	 * 保存修改 模块
	 * @return
	 */
	public String editSave(){
		moduleService.editSave(module);
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		this.recordLogs(logger, "editModule", managerVO.getNickname() + " 修改模块 " + module.getModulename());
		return "editSave";
	}
	
	/**
	 * 删除 模块
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		moduleService.deleteByIds(strbuf.toString());
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		//记录日志
		MDC.put("email", managerVO.getEmail());	//用户名
		MDC.put("nickname", managerVO.getNickname());	//昵称
		MDC.put("ip", this.getRealIP());	//IP
		MDC.put("act", "deleteModule");	//删除模块
		logger.info(managerVO.getNickname() + " 删除模块ID号： " + id);
		
		return "deleteSuccess";
	}

	/**
	 * 删除 模块
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			moduleService.deleteByIds(strbuf.toString());
			
			Manager managerVO = (Manager)this.getSession().getAttribute("manager");
			//记录日志
			MDC.put("email", managerVO.getEmail());	//用户名
			MDC.put("nickname", managerVO.getNickname());	//昵称
			MDC.put("ip", this.getRealIP());	//IP
			MDC.put("act", "deleteModule");	//删除模块
			logger.info(managerVO.getNickname() + " 删除模块ID号： " + strbuf.toString());
			
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}