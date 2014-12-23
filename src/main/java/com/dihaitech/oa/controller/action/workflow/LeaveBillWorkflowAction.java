package com.dihaitech.oa.controller.action.workflow;

import java.util.Date;
import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.LeaveBill;
import com.dihaitech.oa.service.ILeaveBillService;
import com.dihaitech.oa.util.DateUtil;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;
import com.dihaitech.tserver.managercenter.Manager;

/**
 * 请假单流程Action
 * 
 * @author cg
 *
 * @date 2014-12-22
 */
 @SuppressWarnings("serial")
public class LeaveBillWorkflowAction extends BaseAction {
	private LeaveBill leaveBill = new LeaveBill();
	private ILeaveBillService leaveBillService;
	
	public LeaveBill getLeaveBill() {
		return leaveBill;
	}

	public void setLeaveBill(LeaveBill leaveBill) {
		this.leaveBill = leaveBill;
	}
	public ILeaveBillService getLeaveBillService() {
		return leaveBillService;
	}

	public void setLeaveBillService(ILeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}
	/* 
	 * 请假单查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		//只能看到当前人的工作流
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		leaveBill.setEmail(manager.getEmail());
		
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = leaveBillService.selectLeaveBill(leaveBill,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<LeaveBill> resultList = this.leaveBillService.selectLeaveBill(leaveBill,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","leaveBillAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("LeaveBill json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 请假单
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 请假单
	 * @return
	 */
	public String addSave(){
		String begintimeStr = this.getRequest().getParameter("begintime");
		String endtimeStr = this.getRequest().getParameter("endtime");
		
		Date begintime = DateUtil.stringToDate(begintimeStr, DateUtil.TIME_FORM);
		Date endtime = DateUtil.stringToDate(endtimeStr, DateUtil.TIME_FORM);
		
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		
		leaveBill.setEmail(manager.getEmail());
		
		leaveBill.setBegintime(begintime);
		leaveBill.setEndtime(endtime);
		
		leaveBill.setCreator(manager.getEmail());
		leaveBill.setCreatetime(new Date());
		leaveBill.setUpdator(manager.getEmail());
		leaveBill.setUpdatetime(new Date());
		
		leaveBill.setStatus(0);
		leaveBillService.addSave(leaveBill);
		return "addSave";
	}
	
	/**
	 * 修改 请假单
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			leaveBill.setId(id);
		}else{
			return null;
		}
		
		LeaveBill leaveBillVO = leaveBillService.selectLeaveBillById(leaveBill);
		this.getRequest().setAttribute("leaveBill", leaveBillVO);
		
		
		if(leaveBillVO!=null){
			Date begintime = leaveBillVO.getBegintime();
			Date endtime = leaveBillVO.getEndtime();
			
			String beginDate = DateUtil.dateToString(begintime, DateUtil.DATE_FORM);
			String endDate = DateUtil.dateToString(endtime, DateUtil.DATE_FORM);
			
			String beginHour = DateUtil.dateToString(begintime, "hh");
			String endHour = DateUtil.dateToString(endtime, "hh");
			
			String beginMinute = DateUtil.dateToString(begintime, "mm");
			String endMinute = DateUtil.dateToString(endtime, "mm");
			
			this.getRequest().setAttribute("beginDate", beginDate);
			this.getRequest().setAttribute("endDate", endDate);
			
			this.getRequest().setAttribute("beginHour", beginHour);
			this.getRequest().setAttribute("endHour", endHour);
			
			this.getRequest().setAttribute("beginMinute", beginMinute);
			this.getRequest().setAttribute("endMinute", endMinute);
		}
		return "edit";
	}
	
	/**
	 * 保存修改 请假单
	 * @return
	 */
	public String editSave(){
		String begintimeStr = this.getRequest().getParameter("begintime");
		String endtimeStr = this.getRequest().getParameter("endtime");
		
		Date begintime = DateUtil.stringToDate(begintimeStr, DateUtil.TIME_FORM);
		Date endtime = DateUtil.stringToDate(endtimeStr, DateUtil.TIME_FORM);
		
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		
		leaveBill.setEmail(manager.getEmail());
		
		leaveBill.setBegintime(begintime);
		leaveBill.setEndtime(endtime);
		
		leaveBill.setUpdator(manager.getEmail());
		leaveBill.setUpdatetime(new Date());
		
		leaveBill.setStatus(0);
		leaveBillService.editSave(leaveBill);
		return "editSave";
	}
	
	/**
	 * 删除 请假单
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		leaveBillService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 请假单
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			leaveBillService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
	
	
	public String start(){
		return "start";
	}
}