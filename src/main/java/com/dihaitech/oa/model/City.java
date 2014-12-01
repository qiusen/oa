package com.dihaitech.oa.model;


/**
 * 市
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
@SuppressWarnings("serial")
public class City extends BaseModel{
	
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 所属省编号
	 */
	private String provinceCode;
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
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
