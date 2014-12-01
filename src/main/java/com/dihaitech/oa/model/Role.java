package com.dihaitech.oa.model;

import java.util.Date;

/**
 * 角色
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
@SuppressWarnings("serial")
public class Role extends BaseModel{
	
	/**
	 * 角色名称
	 */
	private String rolename;
	
	/**
	 * 角色状态
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 权限
	 */
	private String rights;
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
}
