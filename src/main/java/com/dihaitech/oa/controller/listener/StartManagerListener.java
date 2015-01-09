package com.dihaitech.oa.controller.listener;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricProcessInstance;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dihaitech.tserver.managercenter.Manager;

/**
 * 开始流程的用户
 * @author qiusen
 *
 */
public class StartManagerListener implements TaskListener{

	private static final long serialVersionUID = 1467107128348915708L;

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		
		//获取流程实例发起人方式
		String processInstanceId = delegateTask.getProcessInstanceId();
		System.out.println("processInstanceId::::::::" + processInstanceId);
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		HistoryService historyService = (HistoryService)wac.getBean("historyService");
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
						.processInstanceId(processInstanceId)
						.singleResult();
		if(hpi!=null){
			String assignee = hpi.getStartUserId();
			delegateTask.setAssignee(assignee);
		}else{
			//获取Session中的manager
			Manager manager = (Manager)ServletActionContext.getRequest().getSession().getAttribute("manager");
			delegateTask.setAssignee(manager.getEmail());
		}
		
		
	}

}
