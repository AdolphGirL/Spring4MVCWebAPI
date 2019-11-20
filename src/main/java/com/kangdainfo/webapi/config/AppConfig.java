package com.kangdainfo.webapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * 啟用@EnableTransactionManagement，在service層加入@Transactional才會生效(同<tx:annotation-driven)
 * 多個事務管理器可跟據Bean的名稱指定
 * 
 * 啟用@EnableAspectJAutoProxy，同<aop:aspectj-autoproxy/>
 */

@Configuration
@PropertySource(value = "classpath:db.properties", encoding = "UTF-8")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Autowired
	private Environment env;
	
	public AppConfig(){
		logger.info("[+] [webapi]-[AppConfig]-[Constructor create ... ]");
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("com.kangdainfo.webapi.model");
		return sessionFactory;
	}
	
	@Bean(destroyMethod = "close")
	@Scope("singleton")
	public DataSource dataSource() {
		final HikariDataSource ds = new HikariDataSource();
		
		/**池中維護的最小空閒連線數，不建議設定此值，而是讓HikariCP把連線池當做固定大小的處理，預設minimumIdle與maximumPoolSize一樣。*/
		/**default 10*/
//		ds.setMinimumIdle(10);
		ds.setMaximumPoolSize(9);
		
		/**自動提交從池中返回的連線*/
		ds.setAutoCommit(true);
		
		/**一個連接Idle狀態的最大時間，超過時，則會被釋放*/
		ds.setIdleTimeout(30000);
		
		/**連線池的使用者定義名稱，主要出現在日誌記錄和JMX管理控制檯中以識別池和池配置*/
		ds.setPoolName("IFIDataHikariCP");
		
		/**池中連線最長生命週期，建議設置比數據庫超時時間少30秒(MSSQL 預設的 timeout 時間是 600秒)*/
		ds.setMaxLifetime(1800000);
		
		/**等待連接池分配連接的最大時間，超過此時間仍沒有可以用的連接，則會發生SQLException*/
		ds.setConnectionTimeout(30000);
		
		/**如果您的驅動程序符合JDBC 4，則無需專用查詢來測試連接*/
//		ds.setConnectionTestQuery("SELECT 1");
		
		ds.setDataSourceClassName(env.getProperty("database.driverClassName"));
		ds.addDataSourceProperty("url", env.getProperty("database.url"));
		ds.addDataSourceProperty("user", env.getProperty("database.username"));
		ds.addDataSourceProperty("password", env.getProperty("database.dwp"));
		
//		ds.addDataSourceProperty("cachePrepStmts", true);				/**是否自訂義配置，true，prepStmtCacheSize、prepStmtCacheSqlLimit才會生效*/
//		ds.addDataSourceProperty("prepStmtCacheSize", 250);				/**連接池大小，官方推薦250-500 ??? */
//		ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);		/**單條SQL最大長度，官方推薦2048*/
//		
//		ds.addDataSourceProperty("useServerPrepStmts", true);
//		ds.addDataSourceProperty("useLocalSessionState", true);
//		ds.addDataSourceProperty("useLocalTransactionState", true);
//		ds.addDataSourceProperty("rewriteBatchedStatements", true);
//		ds.addDataSourceProperty("cacheResultSetMetadata", true);
//		ds.addDataSourceProperty("cacheServerConfiguration", true);
//		ds.addDataSourceProperty("elideSetAutoCommits", true);
//		ds.addDataSourceProperty("maintainTimeStats", false);
		
		/** hikari官方mysql配置
		 *  dataSource.cachePrepStmts=true
			dataSource.prepStmtCacheSize=250
			dataSource.prepStmtCacheSqlLimit=2048
			dataSource.useServerPrepStmts=true
			dataSource.useLocalSessionState=true
			dataSource.useLocalTransactionState=true
			dataSource.rewriteBatchedStatements=true
			dataSource.cacheResultSetMetadata=true
			dataSource.cacheServerConfiguration=true
			dataSource.elideSetAutoCommits=true
			dataSource.maintainTimeStats=false
		 */
		return ds;
	}
	
	private Properties hibernateProperties() {
		final Properties properties = new Properties();
		
		/**optional*/
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.default_schema", env.getProperty("hibernate.default_schema"));
		
		/**批次更新時用到，然後在一定的時候提交更改並清空Session的一級Cache(OutOfMemoryException)*/
		properties.setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
		
//		暫時不處理
//		/**fetch策略*/
//		properties.setProperty("hibernate.max_fetch_depth", "");
//		properties.setProperty("hibernate.default_batch_fetch_size", "");
//		
		/**query cache預設先關閉，即便開啟，也需要在個別使用上，加上setCacheable(true)*/
		properties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
		
		/**二級Cache是SessionFactory範圍內的快取，所有的Session共享同一個二級Cache*/
		/**EhCacheProvider echcache.xml 不設定的話，取jar檔內echcache.xml本身的設定*/
//		properties.setProperty("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
		properties.setProperty("hibernate.cache.region.factory_class", env.getProperty("hibernate.cache.region.factory_class"));
		properties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
		
//		/**把對象用易於理解的方式放入緩存，true讀取寫入都會轉換，false直接CacheEntry，運行環境還是不要開啟，省點效能*/
//		properties.put("hibernate.cache.use_structured_entries", "true");
		
		/***/
		properties.setProperty("hibernate.bytecode.use_reflection_optimizer", env.getProperty("hibernate.bytecode.use_reflection_optimizer"));
		
		return properties;
	}
	
	/**HibernateTransactionManager implements PlatformTransactionManager；DataSource -> SessionFactory -> PlatformTransactionManager*/
	/**HibernateTransactionManager線程綁定一個Hibernate Session，用以支持transactions*/
	/**		HibernateTransactionManager源碼中(JdbcTemplate和Hibernate能被同一事務管理，需要使用同一datasource、事務交由HibernateTransactionManager)
	 * 		if (this.prepareConnection && isSameConnectionForEntireSession(session)) {
				// We're allowed to change the transaction settings of the JDBC Connection.
				if (logger.isDebugEnabled()) {
					logger.debug("Preparing JDBC Connection of Hibernate Session [" + session + "]");
				}
				Connection con = ((SessionImplementor) session).connection();
				Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
				txObject.setPreviousIsolationLevel(previousIsolationLevel);
				if (this.allowResultAccessAfterCompletion && !txObject.isNewSession()) {
					int currentHoldability = con.getHoldability();
					if (currentHoldability != ResultSet.HOLD_CURSORS_OVER_COMMIT) {
						txObject.setPreviousHoldability(currentHoldability);
						con.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
					}
				}
			}
	 */
	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory().getObject());
		
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(sessionFactory().getObject());
//		txManager.setDataSource(dataSource());		
//		return txManager;
	}
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory().getObject());
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
