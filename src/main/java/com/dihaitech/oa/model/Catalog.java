package com.dihaitech.oa.model;

import java.util.Date;		            

/**
 * 目录
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
@SuppressWarnings("serial")
public class Catalog extends BaseModel{
	
	/**
	 * 目录名称
	 */
	private String catalogname;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 排序
	 */
	private Integer ordernum;
	
	/**
	 * 所属菜单ID
	 */
	private Integer menuId;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	public String getCatalogname() {
		return catalogname;
	}
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
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
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
