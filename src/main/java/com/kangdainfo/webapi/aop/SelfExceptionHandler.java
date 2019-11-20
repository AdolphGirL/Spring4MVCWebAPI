package com.kangdainfo.webapi.aop;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.kangdainfo.webapi.util.JsonDataModelAndView;
import org.springframework.http.HttpStatus;

/**
 * 自訂義處理exception handler
 */
@ControllerAdvice 
public class SelfExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SelfExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public JsonDataModelAndView internalServerException(Exception x, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(1);
		logger.error("[+] [webapi]-[SelfExceptionHandler]-[internalServerException]-[發生未預期錯誤]", x);
		map.put("errmsg", "發生內部異常錯誤");
		return new JsonDataModelAndView(map);
	}
	
}
