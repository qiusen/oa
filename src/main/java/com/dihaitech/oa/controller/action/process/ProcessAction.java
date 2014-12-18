package com.dihaitech.oa.controller.action.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.ProcessModel;

/**
 * 
 * 流程管理Action
 * @author qiusen
 *
 */
public class ProcessAction extends BaseAction {

	private static final long serialVersionUID = -3407952370687498047L;
	
	private ProcessModel processModel = new ProcessModel();
	
	private RepositoryService repositoryService;
	
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public ProcessModel getProcessModel() {
		return processModel;
	}

	public void setProcessModel(ProcessModel processModel) {
		this.processModel = processModel;
	}

	/* 
	 * 流程查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
//			String pageSizeStr = this.getRequest().getParameter("pageSize");
//			String pageNoStr = this.getRequest().getParameter("pageNo");
//			int pageSize = 0;
//			int pageNo = 0;
//			
//			pageSize = TypeUtil.stringToInt(pageSizeStr);
//			if (pageSize <= 0) {
//				pageSize = Property.PAGESIZE;
//			}
//
//			pageNo = TypeUtil.stringToInt(pageNoStr);
//			if (pageSize > 0) {
//				this.setManagerPageSize(pageSize);
//			}else{
//				this.setManagerPageSize(Property.PAGESIZE);
//			}
//			
//			
			//部署
			long dc = repositoryService.createDeploymentQuery().count();
			List<Deployment> deploymentList = repositoryService.createDeploymentQuery()
								.orderByDeploymentId()
								.asc()
								.list();
			this.getRequest().setAttribute("deploymentList", deploymentList);
			StringBuffer deploymentJson = new StringBuffer("\"Rows\":[");
			
			if(deploymentList!=null && deploymentList.size()>0){
				Deployment deploymentTemp = null;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
				
				for(int i=0;i<deploymentList.size();i++){
					deploymentTemp = deploymentList.get(i);
					if(i>0){
						deploymentJson.append(",");
					}
					deploymentJson.append("{\"id\":\""+deploymentTemp.getId()
							+"\",\"name\":\""+deploymentTemp.getName()
							+"\",\"deploymentTime\":\""+sdf.format(deploymentTemp.getDeploymentTime())
							+"\"}");
					
				}
			}
			deploymentJson.append("], \"Total\":" + dc);
			this.getRequest().setAttribute("deploymentJson", deploymentJson.toString());
			System.out.println("Deployment json:::::::::::::::::::" + deploymentJson);
			
			
			//流程
			long c = repositoryService.createProcessDefinitionQuery()
								.count();
//			Page pageInfo = new Page(c, pageSize);
//			
//			if (pageNo > 0) {
//				pageInfo.setPage(pageNo);
//			} else {
//				pageInfo.setPage(0);
//			}
			
			List<ProcessDefinition> resultList = repositoryService.createProcessDefinitionQuery()
								.orderByProcessDefinitionId()
								.asc()
								.list();
//								.listPage(pageInfo.getFirstItemPos(), pageSize);
			
			
//			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			
			this.getRequest().setAttribute("actionName","processAction");
			
			StringBuffer json = new StringBuffer("\"Rows\":[");
			
			if(resultList!=null && resultList.size()>0){
				ProcessDefinition processDefinitionTemp = null;
				for(int i=0;i<resultList.size();i++){
					processDefinitionTemp = resultList.get(i);
					if(i>0){
						json.append(",");
					}
					json.append("{\"id\":\""+processDefinitionTemp.getId()
							+"\",\"name\":\""+processDefinitionTemp.getName()
							+"\",\"key\":\""+processDefinitionTemp.getKey()
							+"\",\"fileName\":\""+processDefinitionTemp.getResourceName()
							+"\",\"pngName\":\""+processDefinitionTemp.getDiagramResourceName()
							+"\",\"deploymentId\":\""+processDefinitionTemp.getDeploymentId()
							+"\",\"version\":"+processDefinitionTemp.getVersion()+"}");
					
				}
			}
//			json.append("], \"Total\":" + pageInfo.getResultCount());
			json.append("], \"Total\":" + c);

			System.out.println("Process json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加
	 * @return
	 */
	public String addSave(){
		File processZipFile = processModel.getProcessZipFile();
		if (processZipFile != null && processModel.getProcessZipFileFileName() != null) {
			ZipInputStream zipInputStream;
			try {
				zipInputStream = new ZipInputStream(new FileInputStream(processZipFile));
			
			
				repositoryService.createDeployment()	//部署对象
									.addZipInputStream(zipInputStream)	//部署流程文件
									.name(processModel.getName())	//部署名称
									.deploy();	//执行部署
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "addSave";
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		
		String deploymentId = this.getRequest().getParameter("id");
		
		System.out.println("deploymentId::::::::" +deploymentId);
		repositoryService.deleteDeployment(deploymentId, true);
							
		
		return "deleteSuccess";
	}
	
	/**
	 * 查看流程图
	 * @return
	 */
	public String viewPng(){
		String deploymentId = this.getRequest().getParameter("deploymentId");
		String pngName = this.getRequest().getParameter("pngName");
		
		try {
			InputStream is = repositoryService.getResourceAsStream(deploymentId, pngName);
			OutputStream os = this.getResponse().getOutputStream();
			
			int b=-1;
			while((b=is.read())!=-1){
				os.write(b);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
