package com.dihaitech.oa.controller.action.logs;

import java.util.List;

import com.dihaitech.oa.common.Property;
import com.dihaitech.oa.controller.action.BaseAction;
import com.dihaitech.oa.model.Logs;
import com.dihaitech.oa.service.ILogsService;
import com.dihaitech.oa.util.Page;
import com.dihaitech.oa.util.TypeUtil;
import com.dihaitech.oa.util.json.JSONUtil;

/**
 * 日志Action
 * 
 * @author cg
 *
 * @date 2014-08-22
 */
 @SuppressWarnings("serial")
public class LogsAction extends BaseAction {
	private Logs logs = new Logs();
	private ILogsService logsService;
	
	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}
	public ILogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(ILogsService logsService) {
		this.logsService = logsService;
	}
	/* 
	 * 日志查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
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

			Page pageInfo = logsService.selectLogs(logs,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Logs> resultList = this.logsService.selectLogs(logs,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","logsAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Logs json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 日志
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 日志
	 * @return
	 */
	public String addSave(){
		logsService.addSave(logs);
		return "addSave";
	}
	
	/**
	 * 修改 日志
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			logs.setId(id);
		}else{
			return null;
		}
		
		Logs logsVO = logsService.selectLogsById(logs);
		this.getRequest().setAttribute("logs", logsVO);
		return "edit";
	}
	
	/**
	 * 保存修改 日志
	 * @return
	 */
	public String editSave(){
		logsService.editSave(logs);
		return "editSave";
	}
	
	/**
	 * 删除 日志
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		logsService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 日志
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
			logsService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}