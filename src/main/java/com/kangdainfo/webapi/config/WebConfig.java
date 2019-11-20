package com.kangdainfo.webapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * servlet-context.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.kangdainfo.webapi")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	
	public WebConfig(){
		logger.info("[+] [webapi]-[WebConfig]-[Constructor create ... ]");
	}
	
	/**EnableWebMvc is equivalent to <mvc:annotation-driven />*/
	/**ComponentScan annotation is equivalent to <context:component-scan>*/
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**<mvc:default-servlet-handler />*/
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		/**
		 * Note that if org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler is used, 
		 * then requests will always be forwarded to the default servlet and NoHandlerFoundException would never be thrown
		 * in case.
		 */
		configurer.enable();
	}
	
	/**<mvc:resources location="/statics/" mapping="/resources/**"/>*/
	/**<mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/statics/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
