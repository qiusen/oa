package com.dihaitech.oa.dao;

import java.util.List;

import com.dihaitech.oa.model.Logs;

/**
 * 日志 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-22
 */
public interface ILogsDAO {

	/**
	 * 根据条件查询日志 条数
	 * 
	 * @param logs
	 * @return
	 */
	public Long getLogsCount(Logs logs);

	/**
	 * 分页查找日志
	 * 
	 * @param logs
	 * @param page
	 * @return
	 */
	public List<Logs> selectLogsLike(Logs logs);

	/**
	 * 添加日志
	 * 
	 * @param logs
	 * @return
	 */
	public int addSaveLogs(Logs logs);

	/**
	 * 根据ID获取指定的日志信息
	 * 
	 * @param logs
	 * @return
	 */
	public Logs selectLogsById(Logs logs);

	/**
	 * 修改日志
	 * 
	 * @param logs
	 * @return
	 */
	public int editSaveLogs(Logs logs);

	/**
	 * 根据ID删除指定的日志
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有日志
	 * 
	 * @return
	 */
	public List<Logs> selectAll();
}
