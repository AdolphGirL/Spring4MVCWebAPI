package com.kangdainfo.webapi.model.dao;

public interface TestDao {
	
	public void mixed();

	public void orm();
	
	public void multiO();
	
	public void multiT();
	
	public void logJdbcSql();
	
	public void logHibernateSql();
	
	public void cacheQueryTest();
	
	public void nonCacheQueryTest();
	
}
