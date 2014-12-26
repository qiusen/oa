package com.dihaitech.oa.controller.action.approve;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.LeaveBill;
import com.dihaitech.oa.service.ILeaveBillService;
import com.dihaitech.oa.util.DateUtil;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.tserver.managercenter.Manager;

/**
 * 流程审批
 * @author qiusen
 *
 */
public class WorkflowApproveAction extends BaseAction {

	private static final long serialVersionUID = 547701589942382090L;
	
	private ILeaveBillService leaveBillService;
	
	private RuntimeService runtimeService;
	
	private TaskService taskService;
	
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
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		//获取所有需要审批的任务
		try{
			//当前处理人
			Manager manager = (Manager)this.getSession().getAttribute("manager");
			String assignee = manager.getEmail();
			
			List<Task> taskList = taskService.createTaskQuery()
						.taskAssignee(assignee)	//当前用户需要处理的任务
						.orderByTaskCreateTime()
						.asc()
						.list();
			
			//流程
			long c = 0;
			StringBuffer json = new StringBuffer("\"Rows\":[");
			if(taskList!=null && taskList.size()>0){
				c = taskList.size();
				Task taskTemp = null;
				for(int i=0;i<taskList.size();i++){
					taskTemp = taskList.get(i);
					if(i>0){
						json.append(",");
					}
					json.append("{\"id\":\""+taskTemp.getId()
							+"\",\"name\":\""+taskTemp.getName()
							+"\",\"createTime\":\""+DateUtil.dateToString(taskTemp.getCreateTime(), DateUtil.DATE_FORMAT_A)
							+"\",\"assignee\":\""+taskTemp.getAssignee()
							+"\"}");
					
				}
			}
			json.append("], \"Total\":" + c);
	
			System.out.println("Task json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 审批
	 * @return
	 */
	public String approve(){
		String taskId = this.getRequest().getParameter("taskId");
		Task task = taskService.createTaskQuery()
					.taskId(taskId)
					.singleResult();
		this.getRequest().setAttribute("task", task);
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
						.processInstanceId(task.getProcessInstanceId())
						.singleResult();
		
		String businesskey = processInstance.getBusinessKey();
		String business = businesskey.split("-")[0];
		String bidStr = businesskey.split("-")[1];
		if(business.equals("leaveBill")){	//请假单
			int bid = TypeUtil.stringToInt(bidStr);
			if(bid<=0){
				return null;
			}
			LeaveBill leaveBill = new LeaveBill();
			leaveBill.setId(bid);
			LeaveBill leaveBillVO = leaveBillService.selectLeaveBillById(leaveBill);
			this.getRequest().setAttribute("leaveBill", leaveBillVO);
			
			return "leaveBillApprove";
		}
		
		if(business.equals("beeBill")){	//报销单
			return "beeBillApprove";
		}
		
		return null;
	}
	
	/**
	 * 提交审批
	 * @return
	 */
	public String approveComplete(){
		
		String bid = this.getRequest().getParameter("bid");
		
		String taskId = this.getRequest().getParameter("taskId");
		String message = this.getRequest().getParameter("message");
		
		String cv = this.getRequest().getParameter("cv");
		
		Task task = taskService.createTaskQuery()
					.taskId(taskId)
					.singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		//添加批注
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		String authenticatedUserId = manager.getEmail(); 
		Authentication.setAuthenticatedUserId(authenticatedUserId);     
		taskService.addComment(taskId, processInstanceId, message);
		
		//提交任务
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("cv", cv);
		taskService.complete(taskId, variables);
		
		//判断流程是否结束
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
								.processInstanceId(processInstanceId)
								.singleResult();     
		if(pi==null){          
			//如果流程实例为空，则流程结束了 
			int leaveBillId = TypeUtil.stringToInt(bid);
			if(leaveBillId>0){
				LeaveBill leaveBill = new LeaveBill();
				leaveBill.setId(leaveBillId);
				leaveBill.setStatus(2);		//表单状态为已完成
				leaveBillService.editStatusById(leaveBill);
			}
			
		}
		
		return "approveComplete";
	}
}
