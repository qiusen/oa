package com.dihaitech.oa.controller.action.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Manager;
import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.service.IManagerService;
import com.dihaitech.oa.service.IRoleService;
import com.dihaitech.oa.util.MD5Util;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.json.JSONUtil;

/**
 * 管理员Action
 * 
 * @author cg
 *
 * @date 2013-05-22
 */
 @SuppressWarnings("serial")
public class ManagerAction extends BaseAction {
	 private static final Log logger = LogFactory.getLog(ManagerAction.class);
	 
	private Manager manager = new Manager();
	private IManagerService managerService;
	
	private IRoleService roleService;
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public IManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(IManagerService managerService) {
		this.managerService = managerService;
	}
	
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/* 
	 * 管理员查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
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
			}else{
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
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = managerService.selectManager(manager,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(1);
			}
			
			List<Manager> resultList = this.managerService.selectManager(manager,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","managerAction");
			
			Role role = new Role();
			role.setStatus(1);
			List<Role> roleList = roleService.selectAllByStatus(role);
			this.getRequest().setAttribute("roleList", roleList);
			
			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Manager json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 管理员
	 * @return
	 */
	public String add(){
		Role role = new Role();
		role.setStatus(1);
		List<Role> roleList = roleService.selectAllByStatus(role);
		this.getRequest().setAttribute("roleList", roleList);
		return "add";
	}
	
	/**
	 * 保存添加 管理员
	 * @return
	 */
	public String addSave(){
		manager.setCreatetime(new Date());
		manager.setPassword(MD5Util.stringToMD5(manager.getPassword()));
		managerService.addSave(manager);
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		//记录日志
		MDC.put("username", managerVO.getUsername());	//用户名
		MDC.put("nickname", managerVO.getNickname());	//昵称
		MDC.put("ip", this.getRealIP());	//IP
		MDC.put("act", "addManager");	//添加管理员
		logger.info(managerVO.getNickname() + " 添加管理员 " + manager.getNickname());
		return "addSave";
	}
	
	/**
	 * 修改 管理员
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		if(idStr!=null && idStr.length()>0){
			try{
				id = Integer.parseInt(idStr);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(id>0){
			manager.setId(id);
		}else{
			return null;
		}
		
		Manager managerVO = managerService.selectManagerById(manager);
		this.getRequest().setAttribute("manager", managerVO);
		
		Role role = new Role();
		role.setStatus(1);
		List<Role> roleList = roleService.selectAllByStatus(role);
		this.getRequest().setAttribute("roleList", roleList);
		
		return "edit";
	}
	
	/**
	 * 保存修改 管理员
	 * @return
	 */
	public String editSave(){
		
		if(manager.getPassword()!=null && manager.getPassword().trim().length()>0){
			manager.setPassword(MD5Util.stringToMD5(manager.getPassword()));
		}
		managerService.editSave(manager);
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		//记录日志
		MDC.put("username", managerVO.getUsername());	//用户名
		MDC.put("nickname", managerVO.getNickname());	//昵称
		MDC.put("ip", this.getRealIP());	//IP
		MDC.put("act", "editManager");	//修改管理员
		logger.info(managerVO.getNickname() + " 修改管理员 " + manager.getNickname());
		return "editSave";
	}
	
	/**
	 * 删除 管理员
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		managerService.deleteByIds(strbuf.toString());
		
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
		//记录日志
		this.recordLogs(logger, "editModule", managerVO.getNickname() + " 删除管理员 ID号：" + id);
		
		return "deleteSuccess";
	}
	
	/**
	 * 删除 管理员
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
			managerService.deleteByIds(strbuf.toString());
			
			Manager managerVO = (Manager)this.getSession().getAttribute("manager");
			//记录日志
			MDC.put("username", managerVO.getUsername());	//用户名
			MDC.put("nickname", managerVO.getNickname());	//昵称
			MDC.put("ip", this.getRealIP());	//IP
			MDC.put("act", "deleteManager");	//删除管理员
			logger.info(managerVO.getNickname() + " 删除管理员 条件：" + strbuf.toString());
			
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}