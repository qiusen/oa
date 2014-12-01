package com.dihaitech.oa.controller.action.common;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;

import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Manager;
import com.dihaitech.oa.model.Role;
import com.dihaitech.oa.service.IManagerService;
import com.dihaitech.oa.service.IRoleService;
import com.dihaitech.oa.util.MD5Util;

/**
 * 个人信息管理Action
 * 
 * @author nathanqiu
 *
 * @date 2013-05-22
 */
 @SuppressWarnings("serial")
public class UserAction extends BaseAction {
	 private static final Log logger = LogFactory.getLog(UserAction.class);
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
	 * 个人信息
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		
		Role role = new Role();
		role.setStatus(1);
		List<Role> roleList = roleService.selectAllByStatus(role);
		this.getRequest().setAttribute("roleList", roleList);
		
		return SUCCESS;
	}
	
	/**
	 * 保存修改 管理员
	 * @return
	 */
	public String editSave(){
		int i = managerService.editSaveUser(manager);
		if(i>0){
			Manager managerVO = (Manager)this.getSession().getAttribute("manager");
			managerVO.setNickname(manager.getNickname());
			managerVO.setEmail(manager.getEmail());
			
			this.getRequest().setAttribute("edit", 1);
			
			//记录日志
			MDC.put("username", managerVO.getUsername());	//用户名
			MDC.put("nickname", managerVO.getNickname());	//昵称
			MDC.put("ip", this.getRealIP());	//IP
			MDC.put("act", "changeInfo");	
			logger.info(managerVO.getNickname() + " 修改个人信息 ");
			
		}else{
			this.getRequest().setAttribute("edit", 2);
		}

		Role role = new Role();
		role.setStatus(1);
		List<Role> roleList = roleService.selectAllByStatus(role);
		this.getRequest().setAttribute("roleList", roleList);
		
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String editPassword(){
		return "editPassword";
	}
	
	/**
	 * 保存修改密码
	 * @return
	 */
	public String editSavePassword(){
		String newPassword = this.getRequest().getParameter("newPassword");
		Manager managerVO = (Manager)this.getSession().getAttribute("manager");
//		System.out.println("newPassword::::::" + newPassword);
//		System.out.println("manager.getPassword()::::" + manager.getPassword());
		if(manager.getPassword()!=null && MD5Util.stringToMD5(manager.getPassword()).equals(managerVO.getPassword())){
			manager.setPassword(MD5Util.stringToMD5(newPassword));
			int i = managerService.editSavePassword(manager);
			if(i>0){
				this.getRequest().setAttribute("edit", 1);
				
				//记录日志
				MDC.put("username", managerVO.getUsername());	//用户名
				MDC.put("nickname", managerVO.getNickname());	//昵称
				MDC.put("ip", this.getRealIP());	//IP
				MDC.put("act", "changePasswd");	
				logger.info(managerVO.getNickname() + " 修改个人密码 ");
				
			}else{
				this.getRequest().setAttribute("edit", 2);	//修改失改
			}
		}else{
			this.getRequest().setAttribute("edit", 3);	//与现有密码不符
		}
		
		
		return "editPassword";
	}
	
}