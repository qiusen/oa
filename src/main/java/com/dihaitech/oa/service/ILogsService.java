package com.dihaitech.oa.service;

import java.util.List;

import com.dihaitech.oa.model.Logs;
import com.dihaitech.oa.util.Page;

/**
 * 日志 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-22
 */
public interface ILogsService {
	/**
	 * 查询 Logs Page 对象
	 * @param logs
	 * @param pageSize
	 * @return
	 */
	public Page selectLogs(Logs logs, int pageSize);

	/**
	 * 分页查找 日志
	 * @param logs
	 * @param page
	 * @return
	 */
	public List<Logs> selectLogs(Logs logs, Page page);
	
	/**
	 * 查询所有 日志
	 * @return
	 */
	public List<Logs> selectAll();
	
	/**
	 * 根据 ID 查找 日志 
	 * @param logs
	 * @return
	 */
	public Logs selectLogsById(Logs logs);
	
	/**
	 * 添加 日志 
	 * @param logs
	 * @return
	 */
	public int addSave(Logs logs);
	
	/**
	 * 修改 日志 
	 * @param logs
	 * @return
	 */
	public int editSave(Logs logs);
	
	/**
	 * 根据 ID 删除 日志 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
