package com.dihaitech.oa.controller.action.managerRole;

import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;
import com.dihaitech.oa.model.ManagerRole;
import com.dihaitech.oa.service.IManagerRoleService;

/**
 * 用户角色Action
 * 
 * @author cg
 *
 * @date 2014-12-16
 */
 @SuppressWarnings("serial")
public class ManagerRoleAction extends BaseAction {
	private ManagerRole managerRole = new ManagerRole();
	private IManagerRoleService managerRoleService;
	
	public ManagerRole getManagerRole() {
		return managerRole;
	}

	public void setManagerRole(ManagerRole managerRole) {
		this.managerRole = managerRole;
	}
	public IManagerRoleService getManagerRoleService() {
		return managerRoleService;
	}

	public void setManagerRoleService(IManagerRoleService managerRoleService) {
		this.managerRoleService = managerRoleService;
	}
	/* 
	 * 用户角色查询
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

			Page pageInfo = managerRoleService.selectManagerRole(managerRole,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<ManagerRole> resultList = this.managerRoleService.selectManagerRole(managerRole,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","managerRoleAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("ManagerRole json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 用户角色
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 用户角色
	 * @return
	 */
	public String addSave(){
		managerRoleService.addSave(managerRole);
		return "addSave";
	}
	
	/**
	 * 修改 用户角色
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			managerRole.setId(id);
		}else{
			return null;
		}
		
		ManagerRole managerRoleVO = managerRoleService.selectManagerRoleById(managerRole);
		this.getRequest().setAttribute("managerRole", managerRoleVO);
		return "edit";
	}
	
	/**
	 * 保存修改 用户角色
	 * @return
	 */
	public String editSave(){
		managerRoleService.editSave(managerRole);
		return "editSave";
	}
	
	/**
	 * 删除 用户角色
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		managerRoleService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 用户角色
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
			managerRoleService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}