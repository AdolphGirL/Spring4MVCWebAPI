package com.kangdainfo.webapi.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ch.qos.logback.classic.ViewStatusMessagesServlet;

/**web.xml*/
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * view層之外的配置
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	/**
	 * servlet-context.xml設定
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * DispatcherServlet映射路徑
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		/**setLoadOnStartup(1)，extends AbstractAnnotationConfigDispatcherServletInitializer已設定*/
		
		/**filter*/
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}
}

//另一種配置，但未撰寫少了AppConfig的配置
//public class ApplicationInitializer implements WebApplicationInitializer {
//
//	@Override
//	public void onStartup(ServletContext container) throws ServletException {
//		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//		ctx.register(AppConfig.class);
//		ctx.setServletContext(container);
//
//		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/");
//		
//		FilterRegistration.Dynamic encodingFilter = container.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
//		encodingFilter.setInitParameter("encoding", "UTF-8");
//		encodingFilter.setInitParameter("forceEncoding", "true");
//		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//	}
//	
//}
