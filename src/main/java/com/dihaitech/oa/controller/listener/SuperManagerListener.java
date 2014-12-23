package com.dihaitech.oa.controller.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.dihaitech.oa.common.Property;
import com.dihaitech.tserver.managercenter.Department;
import com.dihaitech.tserver.managercenter.Manager;
import com.dihaitech.tserver.managercenter.ManagerCenterService;


/**
 * 获取审批人的监听
 * @author qiusen
 *
 */
public class SuperManagerListener implements TaskListener{

	private static final long serialVersionUID = 8593889605329760930L;

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		
		//获取本系统Service
//		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		
//		wac.getBean("managerService");
		
		/**获取主管，条件如下：
		 * 1、所在部门主管
		 * 2、如果自己本身是部门主管，那么查找其上级部门信息，获取主管
		 */
		//获取Session中的manager
		Manager manager = (Manager)ServletActionContext.getRequest().getSession().getAttribute("manager");
		
		//部门ID
		int departmentId = manager.getDepartment_id();
		
		String assignee = "";
		
		Department department = this.tserverDepartment(departmentId);
		if(department!=null){
			//自己是本部门主管，获取上一级部门信息
			if(department.getLeader_email().equalsIgnoreCase(manager.getEmail())){
				department = tserverDepartment(department.getSuper_id());
			}
			assignee = department.getLeader_email();
			
		}
		
		delegateTask.setAssignee(assignee);
	}
	
	/**
	 * 根据部门ID获取部门
	 * @param departmentId
	 * @return
	 */
	private Department tserverDepartment(int departmentId){
		Department department = null;
		try {
			   TTransport transport = new TFramedTransport(new TSocket(Property.MANAGERCENTER_HOST, Property.MANAGERCENTER_PORT));
			   
			   TBinaryProtocol protocol = new TBinaryProtocol(transport);
			   //TCompactProtocol protocol = new TCompactProtocol(transport);
			   
			   ManagerCenterService.Client client = new ManagerCenterService.Client(protocol);
			   transport.open();
			   
			   Map<String, String> param = new HashMap<String, String>();

			   param.put("tName", "manager");
			   param.put("sName", "selectDepartmentById");
			   param.put("pValue", String.valueOf(departmentId));
			   
			   List<Department> departmentList = client.funCallDepartment(System.currentTimeMillis(), "selectDepartmentById", param);
			   if(departmentList!=null && departmentList.size()>0){
				   department = departmentList.get(0);
			   }
			  
			   transport.close();
		} catch (TException x) {
			   x.printStackTrace();
		}
		return department;
	}
	

}
