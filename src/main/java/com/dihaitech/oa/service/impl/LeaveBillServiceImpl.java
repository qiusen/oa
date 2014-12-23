package com.dihaitech.oa.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.dao.ILeaveBillDAO;
import com.dihaitech.oa.model.LeaveBill;
import com.dihaitech.oa.service.ILeaveBillService;

/**
 * 请假单 业务接口 ILeaveBillService 实现类
 * 
 * @author cg
 *
 * @date 2014-12-22
 */
public class LeaveBillServiceImpl implements ILeaveBillService {

	@Resource
	private ILeaveBillDAO leaveBillDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#addSave(com.dihaitech.oa.model.LeaveBill)
	 */
	public int addSave(LeaveBill leaveBill) {
		return leaveBillDAO.addSaveLeaveBill(leaveBill);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return leaveBillDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#editSave(com.dihaitech.oa.model.LeaveBill)
	 */
	public int editSave(LeaveBill leaveBill) {
		return leaveBillDAO.editSaveLeaveBill(leaveBill);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.ILeaveBillService#selectAll()
	 */
	public List<LeaveBill> selectAll() {
		return leaveBillDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#selectLeaveBill(com.dihaitech.oa.model.LeaveBill, int)
	 */
	public Page selectLeaveBill(LeaveBill leaveBill, int pageSize) {
		return new Page(leaveBillDAO.getLeaveBillCount(leaveBill), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#selectLeaveBill(com.dihaitech.oa.model.LeaveBill, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<LeaveBill> selectLeaveBill(LeaveBill leaveBill, Page page) {
		leaveBill.setStart(page.getFirstItemPos());
		leaveBill.setPageSize(page.getPageSize());
		return leaveBillDAO.selectLeaveBillLike(leaveBill);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILeaveBillService#selectLeaveBillById(com.dihaitech.oa.model.LeaveBill)
	 */
	public LeaveBill selectLeaveBillById(LeaveBill leaveBill) {
		return leaveBillDAO.selectLeaveBillById(leaveBill);
	}
}
