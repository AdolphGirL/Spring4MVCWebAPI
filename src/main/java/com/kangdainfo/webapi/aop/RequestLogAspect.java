package com.kangdainfo.webapi.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestLogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);
	
//	public RequestLogAspect(){
//		logger.info("[+] [webapi]-[RequestLogAspect]-[Constructor create ... ]");
//	}
	
	@Pointcut("execution(* com.kangdainfo.webapi.controller.*.*(..))")
//	@Pointcut("within(@org.springframework.stereotype.RestController *)")
	public void controllerLog() {
		
	}
	
	@Before(value = "controllerLog()")
	public void doBefore(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		WebLog WebLog = new WebLog(request.getRequestURL().toString(), request.getRemoteAddr(), classMethod, args);
		logger.info("[+] [webapi]-[RequestLogAspect]-[doBefore request: {}]", WebLog);
	}
	
	@After(value = "controllerLog()")
	public void doAfter(){
		
	}
	
	@AfterReturning(returning = "result", pointcut = "controllerLog()")
	public void doAfterReturn(Object result){
//		暫時不紀錄
//		logger.info("[+] [webapi]-[RequestLogAspect]-[doAfterReturn result: {}]", ToStringBuilder.reflectionToString(result, ToStringStyle.DEFAULT_STYLE));
	}
	
	class WebLog {
		
		private String url;
		private String ip;
		private String classMethod;
		private Object[] args;
		
		public WebLog(String url, String ip, String classMethod, Object[] args) {
			this.url = url;
			this.ip = ip;
			this.classMethod = classMethod;
			this.args = args;
		}
		
		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
		
		public String getIp() {
			return ip;
		}
		
		public void setIp(String ip) {
			this.ip = ip;
		}
		
		public String getClassMethod() {
			return classMethod;
		}
		
		public void setClassMethod(String classMethod) {
			this.classMethod = classMethod;
		}
		
		public Object[] getArgs() {
			return args;
		}
		
		public void setArgs(Object[] args) {
			this.args = args;
		}
		
		@Override
		public String toString(){
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
		
	}
	

}
