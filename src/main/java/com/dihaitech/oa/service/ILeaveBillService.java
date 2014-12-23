package com.dihaitech.oa.service;

import java.util.List;


import com.dihaitech.oa.model.LeaveBill;
import com.dihaitech.oa.util.Page;

/**
 * 请假单 业务接口
 * 
 * @author cg
 *
 * @date 2014-12-22
 */
public interface ILeaveBillService {
	/**
	 * 查询 LeaveBill Page 对象
	 * @param leaveBill
	 * @param pageSize
	 * @return
	 */
	public Page selectLeaveBill(LeaveBill leaveBill, int pageSize);

	/**
	 * 分页查找 请假单
	 * @param leaveBill
	 * @param page
	 * @return
	 */
	public List<LeaveBill> selectLeaveBill(LeaveBill leaveBill, Page page);
	
	/**
	 * 查询所有 请假单
	 * @return
	 */
	public List<LeaveBill> selectAll();
	
	/**
	 * 根据 ID 查找 请假单 
	 * @param leaveBill
	 * @return
	 */
	public LeaveBill selectLeaveBillById(LeaveBill leaveBill);
	
	/**
	 * 添加 请假单 
	 * @param leaveBill
	 * @return
	 */
	public int addSave(LeaveBill leaveBill);
	
	/**
	 * 修改 请假单 
	 * @param leaveBill
	 * @return
	 */
	public int editSave(LeaveBill leaveBill);
	
	/**
	 * 根据 ID 删除 请假单 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据ID修改状态
	 * @param leaveBill
	 * @return
	 */
	public int editStatusById(LeaveBill leaveBill);
}
