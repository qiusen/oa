package com.dihaitech.oa.controller.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;

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
		
		//获取Session中的manager
		Manager manager = (Manager)ServletActionContext.getRequest().getSession().getAttribute("manager");
		
		delegateTask.setAssignee(manager.getEmail());
	}

}
