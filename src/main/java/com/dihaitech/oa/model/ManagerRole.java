package com.dihaitech.oa.model;

import java.util.Date;

/**
 * 用户角色
 * 
 * @author cg
 *
 * @date 2014-12-16
 */
@SuppressWarnings("serial")
public class ManagerRole extends BaseModel{
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 角色ID
	 */
	private Integer roleId;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 修改时间
	 */
	private Date updatetime;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
