package com.dihaitech.oa.model;

import java.util.Date;
import java.util.Map;

/**
 * 管理员
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
@SuppressWarnings("serial")
public class Manager extends BaseModel{
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 角色ID
	 */
	private Integer roleId;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 最后登录时间
	 */
	private Date logintime;
	
	/**
	 * 最后登录IP地址
	 */
	private String loginip;
	
	/**
	 * 权限列表
	 */
	private Map<String, Module> rightsMap;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public Map<String, Module> getRightsMap() {
		return rightsMap;
	}
	public void setRightsMap(Map<String, Module> rightsMap) {
		this.rightsMap = rightsMap;
	}
	
	
}
