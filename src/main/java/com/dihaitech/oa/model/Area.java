package com.dihaitech.oa.model;


/**
 * 区
 * 
 * @author cg
 *
 * @date 2014-08-21
 */
@SuppressWarnings("serial")
public class Area extends BaseModel{
	
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 所属市编号
	 */
	private String cityCode;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
