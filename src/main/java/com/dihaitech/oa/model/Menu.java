package com.dihaitech.oa.model;

import java.util.Date;		            

/**
 * 菜单
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
@SuppressWarnings("serial")
public class Menu extends BaseModel{
	
	/**
	 * 菜单名称
	 */
	private String menuname;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 排序
	 */
	private Integer ordernum;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
