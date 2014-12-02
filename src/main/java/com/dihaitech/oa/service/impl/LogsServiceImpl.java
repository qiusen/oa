package com.dihaitech.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.oa.dao.ILogsDAO;
import com.dihaitech.oa.model.Logs;
import com.dihaitech.oa.service.ILogsService;
import com.dihaitech.oa.util.Page;

/**
 * 日志 业务接口 ILogsService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-22
 */
public class LogsServiceImpl implements ILogsService {

	@Resource
	private ILogsDAO logsDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#addSave(com.dihaitech.oa.model.Logs)
	 */
	public int addSave(Logs logs) {
		return logsDAO.addSaveLogs(logs);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return logsDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#editSave(com.dihaitech.oa.model.Logs)
	 */
	public int editSave(Logs logs) {
		return logsDAO.editSaveLogs(logs);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.ILogsService#selectAll()
	 */
	public List<Logs> selectAll() {
		return logsDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#selectLogs(com.dihaitech.oa.model.Logs, int)
	 */
	public Page selectLogs(Logs logs, int pageSize) {
		return new Page(logsDAO.getLogsCount(logs), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#selectLogs(com.dihaitech.oa.model.Logs, com.dihaitech.oa.controller.helper.Page)
	 */
	public List<Logs> selectLogs(Logs logs, Page page) {
		logs.setStart(page.getFirstItemPos());
		logs.setPageSize(page.getPageSize());
		return logsDAO.selectLogsLike(logs);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.oa.service.ILogsService#selectLogsById(com.dihaitech.oa.model.Logs)
	 */
	public Logs selectLogsById(Logs logs) {
		return logsDAO.selectLogsById(logs);
	}
}
