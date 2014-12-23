package com.dihaitech.oa.dao;

import java.util.List;


import com.dihaitech.oa.model.LeaveBill;

/**
 * 请假单 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-12-22
 */
public interface ILeaveBillDAO {

	/**
	 * 根据条件查询请假单 条数
	 * 
	 * @param leaveBill
	 * @return
	 */
	public Long getLeaveBillCount(LeaveBill leaveBill);

	/**
	 * 分页查找请假单
	 * 
	 * @param leaveBill
	 * @param page
	 * @return
	 */
	public List<LeaveBill> selectLeaveBillLike(LeaveBill leaveBill);

	/**
	 * 添加请假单
	 * 
	 * @param leaveBill
	 * @return
	 */
	public int addSaveLeaveBill(LeaveBill leaveBill);

	/**
	 * 根据ID获取指定的请假单信息
	 * 
	 * @param leaveBill
	 * @return
	 */
	public LeaveBill selectLeaveBillById(LeaveBill leaveBill);

	/**
	 * 修改请假单
	 * 
	 * @param leaveBill
	 * @return
	 */
	public int editSaveLeaveBill(LeaveBill leaveBill);

	/**
	 * 根据ID删除指定的请假单
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有请假单
	 * 
	 * @return
	 */
	public List<LeaveBill> selectAll();

	/**
	 * 根据ID修改状态
	 * @param leaveBill
	 * @return
	 */
	public int editStatusById(LeaveBill leaveBill);
}
