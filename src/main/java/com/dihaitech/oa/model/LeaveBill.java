package com.dihaitech.oa.model;

import java.util.Date;

/**
 * 请假单
 * 
 * @author cg
 *
 * @date 2014-12-22
 */
@SuppressWarnings("serial")
public class LeaveBill extends BaseModel{
	
	/**
	 * 申请人EMAIL
	 */
	private String email;
	
	/**
	 * 名称
	 */
	private String reason;
	
	/**
	 * 类型：1、病假；2、事假；3、年假；4、调休；5、婚假；6、产检假；7、陪产假；8、丧假
	 */
	private Integer type;
	
	/**
	 * 内容
	 */
	private String description;
	
	/**
	 * 状态：0、未开始；1、审批中；2、已完成
	 */
	private Integer status;
	
	/**
	 * 开始时间
	 */
	private Date begintime;
	
	/**
	 * 结束时间
	 */
	private Date endtime;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 修改人
	 */
	private String updator;
	
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
}
