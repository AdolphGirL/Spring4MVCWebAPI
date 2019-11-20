package com.kangdainfo.webapi.model.dao;

import java.util.List;
import java.util.Map;
import com.kangdainfo.webapi.model.bo.Exp0001Db;

public interface ImfDao {
	
	/**
	 * 
	 * @param queryString
	 * @param paramName
	 * @param value
	 * @return
	 */
	public List<?> loadSQLNamedParam(String queryString, String[] paramName, Object[] value);
	public List<?> loadSQLNamedParam(String queryString, String[] paramName, Object[] value, boolean cache);
	
	/**
	 * 
	 * @param queryString
	 * @param paramName
	 * @param value
	 * @return
	 */
	public Object loadObjectNamedParam(String queryString, String[] paramName, Object[] value);
	public Object loadObjectNamedParam(String queryString, String[] paramName, Object[] value, boolean cache);
	
	/**
	 * 少用
	 * @param queryString
	 * @return
	 */
	public int loadCountNoParam(String queryString);
	public List<?> loadNoParam(String queryString);
	public Object loadObjectNoParam(String queryString);
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int jdbcCount(String sql, Object[] args);
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public String jdbcSingleField(String sql, Object[] args);
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, Object> jdbcSingleMapField(String sql, Object[] args);
	
	
	/**
	 * 
	 * @param simpId
	 * @return
	 */
	public Exp0001Db genJDBCExp0001Db(String simpId);
	
}
