package com.dihaitech.oa.controller.action.role;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Catalog;
import com.dihaitech.oa.model.ManagerRole;
import com.dihaitech.oa.model.Menu;
import com.dihaitech.oa.model.Module;
import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.service.ICatalogService;
import com.dihaitech.oa.service.IManagerRoleService;
import com.dihaitech.oa.service.IMenuService;
import com.dihaitech.oa.service.IModuleService;
import com.dihaitech.oa.service.IRoleService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;
import com.dihaitech.tserver.managercenter.Manager;

/**
 * 角色Action
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
@SuppressWarnings("serial")
public class RoleAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(RoleAction.class);
	private Role role = new Role();
	private IRoleService roleService;

	private IModuleService moduleService;

	private IMenuService menuService;

	private ICatalogService catalogService;
	
	private IManagerRoleService managerRoleService;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public ICatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(ICatalogService catalogService) {
		this.catalogService = catalogService;
	}
	

	public IManagerRoleService getManagerRoleService() {
		return managerRoleService;
	}

	public void setManagerRoleService(IManagerRoleService managerRoleService) {
		this.managerRoleService = managerRoleService;
	}

	/*
	 * 角色查询
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			if (pageSizeStr != null && pageSizeStr.length() > 0) {
				try {
					pageSize = Integer.parseInt(pageSizeStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				pageSize = Property.PAGESIZE;
			}
			if (pageNoStr != null && pageNoStr.length() > 0) {
				try {
					pageNo = Integer.parseInt(pageNoStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			} else {
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = roleService.selectRole(role,
					this.getManagerPageSize());

			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(1);
			}

			List<Role> resultList = this.roleService.selectRole(role, pageInfo);

			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName", "roleAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)
					+ ", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Role json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加 角色
	 * 
	 * @return
	 */
	public String add() {
		return "add";
	}

	/**
	 * 保存添加 角色
	 * 
	 * @return
	 */
	public String addSave() {
		role.setCreatetime(new Date());
		roleService.addSave(role);

		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		// 记录日志
		MDC.put("email", managerVO.getEmail()); // 用户名
		MDC.put("nickname", managerVO.getNickname()); // 昵称
		MDC.put("ip", this.getRealIP()); // IP
		MDC.put("act", "addRole"); // 添加角色
		logger.info(managerVO.getNickname() + " 添加角色 " + role.getRolename());

		return "addSave";
	}

	/**
	 * 修改 角色
	 * 
	 * @return
	 */
	public String edit() {
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		if (idStr != null && idStr.length() > 0) {
			try {
				id = Integer.parseInt(idStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (id > 0) {
			role.setId(id);
		} else {
			return null;
		}

		Role roleVO = roleService.selectRoleById(role);
		this.getRequest().setAttribute("role", roleVO);
		return "edit";
	}

	/**
	 * 保存修改 角色
	 * 
	 * @return
	 */
	public String editSave() {
		roleService.editSave(role);

		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		// 记录日志
		MDC.put("email", managerVO.getEmail()); // 用户名
		MDC.put("nickname", managerVO.getNickname()); // 昵称
		MDC.put("ip", this.getRealIP()); // IP
		MDC.put("act", "editRole"); // 修改角色
		logger.info(managerVO.getNickname() + " 修改角色 " + role.getRolename());

		return "editSave";
	}

	/**
	 * 删除 角色
	 * 
	 * @return
	 */
	public String delete() {
		String idstr = this.getRequest().getParameter("id");
		int id = TypeUtil.stringToInt(idstr);
		if (id > 0) {
			ManagerRole managerRole = new ManagerRole();
			managerRole.setRoleId(id);
			long c = managerRoleService.selectCountByRoleId(managerRole);
			
			if (c > 0) { // 角色下有用户，不允许删除
				this.getRequest().setAttribute("errCode", "hasManager");
				return "forward";
			} else {
				StringBuffer strbuf = new StringBuffer(" where id =");
				strbuf.append(idstr);
				roleService.deleteByIds(strbuf.toString());

				Manager managerVO = (Manager) this.getSession().getAttribute(
						"manager");
				// 记录日志
				MDC.put("email", managerVO.getEmail()); // 用户名
				MDC.put("nickname", managerVO.getNickname()); // 昵称
				MDC.put("ip", this.getRealIP()); // IP
				MDC.put("act", "deleteRole"); // 删除角色
				logger.info(managerVO.getNickname() + " 删除角色ID号： " + idstr);
			}
		}

		return "deleteSuccess";
	}

	/**
	 * 删除 角色
	 * 
	 * @return
	 */
	public String deleteByIds() {
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
			roleService.deleteByIds(strbuf.toString());

			Manager managerVO = (Manager) this.getSession().getAttribute(
					"manager");
			// 记录日志
			MDC.put("email", managerVO.getEmail()); // 用户名
			MDC.put("nickname", managerVO.getNickname()); // 昵称
			MDC.put("ip", this.getRealIP()); // IP
			MDC.put("act", "deleteRole"); // 删除角色
			logger.info(managerVO.getNickname() + " 删除角色ID号： "
					+ strbuf.toString());

			return "deleteSuccess";
		}
		return "deleteFailure";
	}

	/**
	 * 分配权限
	 * 
	 * @return
	 */
	public String giveRights() {
		String idStr = this.getRequest().getParameter("id");
		int id = TypeUtil.stringToInt(idStr);
		if (id > 0) {
			role.setId(id);
		} else {
			return null;
		}
		Role roleVO = roleService.selectRoleById(role);
		this.getRequest().setAttribute("role", roleVO);

		String rightsStr = roleVO.getRights();
		String[] rights = null;
		if (rightsStr != null && rightsStr.trim().length() > 0) {
			rights = rightsStr.split(",");
			this.getRequest().setAttribute("rights", rights);
		}

		Module module = new Module();
		module.setStatus(1);
		List<Module> moduleList = moduleService.selectAllByStatus(module);
		this.getRequest().setAttribute("moduleList", moduleList);

		// 菜单
		Menu menu = new Menu();
		menu.setStatus(1);
		List<Menu> menuList = menuService.selectAllByStatus(menu);
		this.getRequest().setAttribute("menuList", menuList);

		// 目录
		Catalog catalog = new Catalog();
		catalog.setStatus(1);
		List<Catalog> catalogList = catalogService.selectAllByStatus(catalog);
		this.getRequest().setAttribute("catalogList", catalogList);
		
		StringBuffer strbuf = new StringBuffer("[");
		
		strbuf.append("{ id: 'mm', pid: -1, text: '主要菜单' , type:0 }");
		
		if(menuList!=null && menuList.size()>0){
			Menu menuTemp = null;
			for(int i=0;i<menuList.size();i++){
				menuTemp = menuList.get(i);
				strbuf.append(",{ id: 'm" + menuTemp.getId() + "', pid: 'mm', text: '" + menuTemp.getMenuname() + "' ,type: 1}");
			}
		}
		
		if(catalogList!=null && catalogList.size()>0){
			Catalog catalogTemp = null;
			for(int i=0;i<catalogList.size();i++){
				catalogTemp = catalogList.get(i);
				strbuf.append(",{ id: 'c" + catalogTemp.getId() + "', pid: 'm" + catalogTemp.getMenuId() + "', text: '" + catalogTemp.getCatalogname() + "' ,type: 2}");
			}
		}
		
		if(moduleList!=null && moduleList.size()>0){
			Module moduleTemp = null;
			for(int i=0;i<moduleList.size();i++){
				moduleTemp = moduleList.get(i);
				boolean isChecked = false;
				
				if(rights!=null && rights.length>0){
					for(int j=0;j<rights.length;j++){
						if(moduleTemp.getId().intValue() == TypeUtil.stringToInt(rights[j])){
							isChecked = true;
							break;
						}
					}
				}
				
				strbuf.append(",{ id: " + moduleTemp.getId() + ", pid: 'c" + moduleTemp.getCatalogId() + "', text: '" + moduleTemp.getModulename() + "' ,type: 3");
				
				if(isChecked){
					strbuf.append(", ischecked: true");
				}
				
				strbuf.append("}");
				
			}
		}
		

		strbuf.append("]");
		
		this.getRequest().setAttribute("data", strbuf.toString());
		return "giveRights";
	}

	/**
	 * 保存权限
	 * 
	 * @return
	 */
	public String saveRights() {
//		String[] modules = this.getRequest().getParameterValues("module");
//
//		if (modules != null && modules.length > 0) {
//			StringBuffer strbuf = new StringBuffer(",");
//			for (int i = 0; i < modules.length; i++) {
//				strbuf.append(modules[i] + ",");
//			}
//			role.setRights(strbuf.toString());

			roleService.editRights(role);

			Manager managerVO = (Manager) this.getSession().getAttribute(
					"manager");
			
			this.recordLogs(logger, "giveRoleRights", managerVO.getNickname() + " 给角色 " + role.getId()
					+ " 赋权： " + role.getRights());
//		}

		return "saveRights";
	}
}