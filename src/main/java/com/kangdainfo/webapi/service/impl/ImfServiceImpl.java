package com.kangdainfo.webapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kangdainfo.webapi.model.dao.ImfDao;
import com.kangdainfo.webapi.service.ImfService;

@Service
@Transactional(readOnly = true)
public class ImfServiceImpl implements ImfService {
	
	private static final Logger logger = LoggerFactory.getLogger(ImfServiceImpl.class);
	
	@Autowired
	private ImfDao imfDao;
	
}
