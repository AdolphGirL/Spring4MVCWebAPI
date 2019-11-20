package com.kangdainfo.webapi.model.dao.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.kangdainfo.webapi.model.dao.TestDao;

@Repository
public class TestDaoImpl implements TestDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;

//	@Transactional
	@Override
	public void mixed() {
//		測試五，多個Thread讀取更新同一筆資料
//		測試結果，ok
//		Imf0001Db tmp = hibernateTemplate.load(Imf0001Db.class, new java.math.BigDecimal("1"));
//		logger.info("[+] [TestDaoImpl]-[Mixed]-[start imf0001Db is {}]", tmp.getModDate());
//		tmp.setModDate("BFVGH");
//		logger.info("[+] [TestDaoImpl]-[Mixed]-[end imf0001Db is {}]", tmp.getModDate());
	}
	
	@Override
	public void orm() {
		
//		測試一，判斷，hibernatetemplate find出來的資料，其生命週期，是否有會被服務層的transaction所控管
//		測試結果，服務層的transaction，被spring HibernateTransactionManager 所控管，
//		所以雖然，hibernateTemplate有session，但仍然會受到session会受到Transaction影響
//		List<?> list = null;
//		list = hibernateTemplate.loadAll(Imf0001Db.class);
//		if(list!=null && list.size()>0){
//			for(int i=0, length = list.size(); i<length; i++){
//				Imf0001Db imf0001Db = (Imf0001Db)list.get(i);
//				logger.info("[+] [TestDaoImpl]-[orm]-[imf0001Db is {}]", imf0001Db);
//				
////				if(imf0001Db.getImf0002Dbs()!=null && imf0001Db.getImf0002Dbs().size()>0){
////					for(Object dtlObj : imf0001Db.getImf0002Dbs()){
////						Imf0002Db imf0002Db = (Imf0002Db)dtlObj;
////						logger.info("[+] [TestDaoImpl]-[orm]-[imf0002Db is {}]", imf0002Db);
////					}
////				}
//				
//				imf0001Db.setModDate("FGHJK");
//				hibernateTemplate.update(imf0001Db);
//			}
//		}
		
//		hibernateTemplate.flush();
//		hibernateTemplate.clear(); --> 清除一級緩存的資料，使用中的不會
		
//		測試二，故意讓jdbc發生錯誤，查看資料是否會回滾；以確認jdbcTemplate有被HibernateTransactionManager控管；在同一個datasource下。
//		測試結果，確定會回滾
//		list = jdbcTemplate.queryForList(" select * from Imf0001Db ");
		
//		測試三，在同一transaction中，hibernate將資料更新，jdbctemplate取出後，是否有變化
//		測試結果，如果沒有強制flush，則不會
//		list = jdbcTemplate.queryForList(" select * from Imf0001_Db ");
//		if(list!=null && list.size()>0){
//			for(int i=0; i<list.size(); i++){
//				logger.info("[+] [TestDaoImpl]-[orm]-[jdbcTemplate imf0001Db is {}]", list.get(i));
//			}
//		}
		
//		測試四，測試flush之後，一級緩存是否還有資料
//		結果，Imf0001Db這一層不會發生異常；Imf0002Db會(因為lazy，true時，返回代理實例而非資料，)
//		if(list!=null && list.size()>0){
//			for(int i=0, length = list.size(); i<length; i++){
//				Imf0001Db imf0001Db = (Imf0001Db)list.get(i);
//				logger.info("[+] [TestDaoImpl]-[orm]-[second imf0001Db is {}]", imf0001Db.getModDate());
////				
////				此處會發生錯誤，因為當flush之後，imf0002Db未lazy proxy，org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.kangdainfo.webapi.model.bo.Imf0001Db.imf0002Dbs, could not initialize proxy - no Session
////				if(imf0001Db.getImf0002Dbs()!=null && imf0001Db.getImf0002Dbs().size()>0){
////					for(Object dtlObj : imf0001Db.getImf0002Dbs()){
////						Imf0002Db imf0002Db = (Imf0002Db)dtlObj;
////						logger.info("[+] [TestDaoImpl]-[orm]-[second imf0002Db is {}]", imf0002Db);
////					}
////				}
//			}
//		}
//		list = null;
	}

	@Override
	public void multiO() {
//		Imf0001Db tmp = hibernateTemplate.load(Imf0001Db.class, new java.math.BigDecimal("1"));
//		logger.info("[+] [TestDaoImpl]-[multiO]-[start imf0001Db is {}]", tmp.getModDate());
//		tmp.setModDate("multiO");
//		logger.info("[+] [TestDaoImpl]-[multiO]-[end imf0001Db is {}]", tmp.getModDate());
	}

	@Override
	public void multiT() {
//		Imf0001Db tmp = hibernateTemplate.load(Imf0001Db.class, new java.math.BigDecimal("1"));
//		logger.info("[+] [TestDaoImpl]-[multiT]-[start imf0001Db is {}]", tmp.getModDate());
//		tmp.setModDate("multiT");
//		logger.info("[+] [TestDaoImpl]-[multiT]-[end imf0001Db is {}]", tmp.getModDate());
	}

	@Override
	public void logJdbcSql() {
//		jdbcTemplate.queryForList(" select * from Imf0001Db ");
	}

	@Override
	public void logHibernateSql() {
//		hibernateTemplate.loadAll(Imf0001Db.class);
	}

	@Override
	public void cacheQueryTest() {
//		logger.info("[+] [TestDaoImpl]-[cacheQueryTest]-[start]");
//		hibernateTemplate.setCacheQueries(true);
//		hibernateTemplate.loadAll(CommonCode.class);
//		logger.info("[+] [TestDaoImpl]-[cacheQueryTest]-[end]");
	}

	@Override
	public void nonCacheQueryTest() {
//		logger.info("[+] [TestDaoImpl]-[nonCacheQueryTest]-[start]");
//		hibernateTemplate.loadAll(CommonCode.class);
//		logger.info("[+] [TestDaoImpl]-[nonCacheQueryTest]-[end]");
	}
	
}
