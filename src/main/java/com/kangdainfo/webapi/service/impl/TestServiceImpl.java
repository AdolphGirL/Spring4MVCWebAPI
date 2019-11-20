package com.kangdainfo.webapi.service.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangdainfo.webapi.model.dao.TestDao;
import com.kangdainfo.webapi.model.dao.impl.TestDaoImpl;
import com.kangdainfo.webapi.service.TestService;

@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {
	
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private TestDao testDao;
	
	@Transactional
	@Override
	public void testMixed() {
		testDao.mixed();
	}

	@Transactional
	@Override
	public void testOrm() {
		testDao.orm();
	}

	@Transactional
	@Override
	public void testMultiO() {
		testDao.multiO();
	}

	@Transactional
	@Override
	public void testMultiT() {
		testDao.multiT();
	}

	@Override
	public void testLogJdbcSql() {
		testDao.logJdbcSql();
	}

	@Override
	public void testLogHibernateSql() {
		testDao.logHibernateSql();
	}

	@Override
	public void testCacheQueryTest() {
		testDao.cacheQueryTest();
	}

	@Override
	public void testNonCacheQueryTest() {
		testDao.nonCacheQueryTest();
	}
	
	
	
}
