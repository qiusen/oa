package com.dihaitech.oa.model;

import java.util.Date;		            

/**
 * 模块
 * 
 * @author cg
 *
 * @date 2014-08-19
 */
@SuppressWarnings("serial")
public class Module extends BaseModel{
	
	/**
	 * 模块名称
	 */
	private String modulename;
	
	/**
	 * 模块URL
	 */
	private String moduleurl;
	
	/**
	 * 模块ACTION
	 */
	private String moduleact;
	
	/**
	 * 所属目录ID
	 */
	private Integer catalogId;
	
	/**
	 * 模块状态
	 */
	private Integer status;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getModuleurl() {
		return moduleurl;
	}
	public void setModuleurl(String moduleurl) {
		this.moduleurl = moduleurl;
	}
	public String getModuleact() {
		return moduleact;
	}
	public void setModuleact(String moduleact) {
		this.moduleact = moduleact;
	}
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
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
}
