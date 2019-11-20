package com.kangdainfo.webapi.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.orm.hibernate5.HibernateCallback;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.kangdainfo.webapi.model.bo.Exp0001Db;
import com.kangdainfo.webapi.model.dao.ImfDao;
import com.kangdainfo.webapi.model.jdbcbo.Exp0001DbRowMapper;

@Repository
public class ImfDaoImpl implements ImfDao {
	
private static final Logger logger = LoggerFactory.getLogger(ImfDaoImpl.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Override
	public List<?> loadSQLNamedParam(String queryString, String[] paramName, Object[] value) {
		return loadSQLNamedParam(queryString, paramName, value, false);
	}

	@Override
	public List<?> loadSQLNamedParam(String queryString , String[] paramName , Object[] value, boolean cache) {
		try{
			if(cache){
				this.hibernateTemplate.setCacheQueries(true);
				return this.hibernateTemplate.findByNamedParam(queryString, paramName, value);
			}else{
				return this.hibernateTemplate.findByNamedParam(queryString, paramName, value);
			}
		}catch(Exception x){
			logger.error("[+] [ImfDaoImpl]-[loadSQLNamedParam]-[發生未預期錯誤]", x);
			return null;
		}
	}
	
	@Override
	public Object loadObjectNamedParam(String queryString, String[] paramName, Object[] value) {
		return loadObjectNamedParam(queryString, paramName, value, false);
	}

	@Override
	public Object loadObjectNamedParam(String queryString, String[] paramName, Object[] value, boolean cache) {
		List<?> list = null;
		try {
			if (cache) {
				this.hibernateTemplate.setCacheQueries(true);
				list = this.hibernateTemplate.findByNamedParam(queryString, paramName, value);
			} else {
				list = this.hibernateTemplate.findByNamedParam(queryString, paramName, value);
			}

			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception x) {
			logger.error("[+] [ImfDaoImpl]-[loadSQLNamedParam]-[發生未預期錯誤]", x);
			return null;
		} finally {
			list = null;
		}
	}
	
	@Override
	public int loadCountNoParam(String queryString) {
		Number number = ((Number) hibernateTemplate.execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				try {
					Query query = session.createQuery("select count(*) " + queryString);
					return query.uniqueResult();
				} catch (Exception e) {
					logger.error("[+] [loadCountNoParam]-[發生錯誤]", e);
					throw new HibernateException(e);
				}
			}
		}));
		
		if (number != null) {
			return number.intValue();
		}
		return 0;
	}
	
	@Override
	public List<?> loadNoParam(String queryString) {
		return (List<?>) hibernateTemplate.execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				try {
					Query query = session.createQuery(queryString);
					return query.list();
				} catch (Exception e) {
					logger.error("[+] [loadNoParam]-[發生錯誤]", e);
					throw new HibernateException(e);
				}
			}
		});
	}
	
	@Override
	public Object loadObjectNoParam(String queryString) {
		List<?> list = null;
		try{
			list = loadNoParam(queryString);
			if (list != null && list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		}catch(Exception x){
			logger.error("[+] [ImfDaoImpl]-[loadObjectNoParam]-[發生未預期錯誤]", x);
			return null;
		}finally{
			list = null;
		}
	}

	@Override
	public int jdbcCount(String sql, Object[] args) {
		try{
			return jdbcTemplate.queryForObject(sql, args, Integer.class);
		}catch(Exception x){
			logger.error("[+] [ImfDaoImpl]-[jdbcCount]-[發生未預期錯誤]", x);
			return 0;
		}
	}
	
	@Override
	public String jdbcSingleField(String sql, Object[] args) {
		/* 這個方式查詢，只要查不到值或與預期有差，會噴錯，但不會影響程式；
		try {
			return jdbcTemplate.queryForObject(sql, args, String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("[+] [ImfDaoImpl]-[jdbcSingleField]-[查無資料]", e);
			return "";
		} catch (Exception x) {
			logger.error("[+] [ImfDaoImpl]-[jdbcSingleField]-[發生未預期錯誤]", x);
			return "";
		}
		*/
		try{
			return jdbcTemplate.query(sql, args, new ResultSetExtractor<String>(){
				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						return rs.getString(1);
					}
					return null;
				}
			});
		}catch(Exception x){
			logger.error("[+] [ImfDaoImpl]-[jdbcSingleField]-[發生未預期錯誤]", x);
			return null;
		}
	}
	
	@Override
	public Map<String, Object> jdbcSingleMapField(String sql, Object[] args) {
		try{
			return jdbcTemplate.queryForMap(sql, args);
		}catch(Exception x){
			logger.error("[+] [ImfDaoImpl]-[jdbcSingleMapField]-[發生未預期錯誤]", x);
			return null;
		}
	}
	
	@Override
	public Exp0001Db genJDBCExp0001Db(String simpId) {
		if(StringUtils.isEmpty(simpId)){
			return null;
		}
		
		List<Exp0001Db> dataList = null;
		try{
			
			/**query查無資料時，才會返回空，不會像queryForObject會發生錯誤*/
			dataList = jdbcTemplate.query(" select ID, GENERAL_RATE, ENHANCE_RATE, EACHCASE_RATE, " + 
										  " NBATCH, NDOUBLE, NBATCH2, NDOUBLE2 from EXP0001_DB where SIMPLIFY_KIND = ? ",
												new String[]{simpId}, new Exp0001DbRowMapper());
			
			if(dataList!=null && dataList.size()>0){
				return dataList.get(0);
			}else{
				return null;
			}
			
		}catch(Exception e){
			logger.error("[+] [ImfDaoImpl]-[genJDBCExp0001Db]-[發生未預期錯誤]", e);
		}finally{
			dataList = null;
		}
		
		return null;
	}
	
}
