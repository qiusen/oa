package com.dihaitech.oa.model;


/**
 * 省
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
@SuppressWarnings("serial")
public class Province extends BaseModel{
	
	/**
	 * 编号
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
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
}
