package com.dihaitech.oa.controller.action.workflow;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

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
	
	private RuntimeService runtimeService;
	
	private TaskService taskService;
	
	private HistoryService historyService;
	
	private RepositoryService repositoryService;
	
	private IdentityService identityService;
	
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
	
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
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
	
	
	/**
	 * 开始
	 * @return
	 */
	public String start(){
		String idStr = this.getRequest().getParameter("id");

		int id = TypeUtil.stringToInt(idStr);
		if(id<=0){
			return null;
		}
		
		String processDefinitionKey = "leaveBill";
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("type", "leaveBill");
//		variables.put("id", idStr);
		//使用“-”连接key和业务表单ID，作为businessKey 使用来关联业务，leaveBill-1
		String businessKey = processDefinitionKey + "-" + id;
		
		//当前处理人
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		String assignee = manager.getEmail();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("days", 6);
		//开始流程
		identityService.setAuthenticatedUserId(assignee);	//设置流程发起人********
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables); 
		
		//流程实例ID
		String processInstanceId = processInstance.getId();
		
		
		
		//当前处理人提交
		Task task = taskService.createTaskQuery()
					.processInstanceId(processInstanceId)
					.taskAssignee(assignee)
					.singleResult();
		
		//添加批注
		String authenticatedUserId = manager.getEmail(); 
		Authentication.setAuthenticatedUserId(authenticatedUserId);
		String message = "申请";
		taskService.addComment(task.getId(), processInstanceId, message);
		
		taskService.complete(task.getId());
		
		leaveBill.setId(id);
		leaveBill.setStatus(1);
		leaveBillService.editStatusById(leaveBill);

		return "start";
	}
	
	/**
	 * 查看审批记录
	 * @return
	 */
	public String viewComments(){
		String businessKey = this.getRequest().getParameter("businessKey");
		
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
						.processInstanceBusinessKey(businessKey)
						.singleResult();
		
		
		String processInstanceId = hpi.getId();
		
		//流程实例ID*********
		System.out.println("hpi.getId()----------" + hpi.getId());
		List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
		//流程
		long c = 0;
		StringBuffer json = new StringBuffer("\"Rows\":[");
		if(commentList!=null && commentList.size()>0){
			c = commentList.size();
			Comment commentTemp = null;
			for(int i=0;i<commentList.size();i++){
				commentTemp = commentList.get(i);
				if(i>0){
					json.append(",");
				}
				json.append("{\"id\":\""+commentTemp.getId()
						+"\",\"userId\":\""+commentTemp.getUserId()
						+"\",\"time\":\""+DateUtil.dateToString(commentTemp.getTime(), DateUtil.DATE_FORMAT_A)
						+"\",\"message\":\""+commentTemp.getFullMessage()
						+"\"}");
				
			}
		}
		json.append("], \"Total\":" + c);

		System.out.println("Task json:::::::::::::::::::" + json);
		this.getRequest().setAttribute("json", json.toString());
		
		String business = businessKey.split("-")[0];
		String bidStr = businessKey.split("-")[1];
		if(business.equals("leaveBill")){	//请假单
			int bid = TypeUtil.stringToInt(bidStr);
			if(bid<=0){
				return null;
			}
			LeaveBill leaveBill = new LeaveBill();
			leaveBill.setId(bid);
			LeaveBill leaveBillVO = leaveBillService.selectLeaveBillById(leaveBill);
			this.getRequest().setAttribute("leaveBill", leaveBillVO);
			
		}
		
		return "viewComments";
	}
	
	/**
	 * 查看当前流程图
	 * @return
	 */
	public String viewCurrentPng(){
		
		String businessKey = this.getRequest().getParameter("businessKey");
		
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
						.processInstanceBusinessKey(businessKey)
						.singleResult();
		
		//获取流程定义
		String processDefinitionId = hpi.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()	//查询act_re_prodef表
							.processDefinitionId(processDefinitionId)
							.singleResult();
		
		this.getRequest().setAttribute("processDefinition", processDefinition);
		
		//画DIV
		//先获取流程定义实体，只有流程定义实体中才有坐标信息
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		
		//根据任务ID，获取流程实例，根据流程实例获取当前活动对象ID
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
						.processInstanceId(hpi.getId())
						.singleResult();
		String activityId = processInstance.getActivityId();
		
		//查询当前活动对象,获取坐标
		ActivityImpl ai = processDefinitionEntity.findActivity(activityId);
		this.getRequest().setAttribute("ai", ai);
		
		//使用审批时的流程图JSP文件
		return "viewCurrentPng";
	}
}