package com.kangdainfo.webapi.controller;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.kangdainfo.webapi.service.TestService;
//import com.kangdainfo.webapi.util.Common;
//import com.kangdainfo.webapi.util.JsonDataModelAndView;

@RestController
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	@Autowired
//	private TestService testService;
//	
//	@Autowired
//	private Common common;
	
//	@GetMapping("/jdbc")
//	public JsonDataModelAndView logJdbc(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>(1);
//		testService.testLogJdbcSql();
//		map.put("msg", "logJdbc done");
//		return new JsonDataModelAndView(map);
//	}
//	
//	@GetMapping("/hql")
//	public JsonDataModelAndView logHibernateSQL(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>(1);
//		testService.testLogHibernateSql();
//		map.put("msg", "logHibernateSQL done");
//		return new JsonDataModelAndView(map);
//	}
	
	@GetMapping("/alive")
	public ResponseEntity<?> checkalive(HttpServletRequest request) {
//		logger.info("[+] [HomeController]-[checkalive]-[request uri and ip]: " + request.getRequestURI() + "、" + request.getRemoteAddr());
		return ResponseEntity.ok().body("alive");
	}
	
//	@GetMapping("/mixed")
//	public ResponseEntity<?> testMixed(HttpServletRequest request) {
//	//	logger.info("[+] [HomeController]-[testMixed]-[request uri and ip]: " + request.getRequestURI() + "、" + request.getRemoteAddr());
//		
////		測試多執行緒
////		Executor executor = Executors.newFixedThreadPool(100);
////		for (int i = 0; i < 100; i++){ 
////			executor.execute(new Runnable(){
////
////				@Override
////				public void run() {
////					logger.info("[+] [TestServiceImpl]-[testMixed]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
////					testService.testMixed();
////				}
////				
////			});
////		}
//				
//		return ResponseEntity.ok().body("mixed");
//	}
	
//	@GetMapping("/orm")
//	public ResponseEntity<?> orm(HttpServletRequest request) {
////		logger.info("[+] [HomeController]-[orm]-[request uri and ip]: " + request.getRequestURI() + "、" + request.getRemoteAddr());
////		testService.testOrm();
//		return ResponseEntity.ok().body("orm");
//	}
	
//	@GetMapping("/multi")
//	public ResponseEntity<?> testMultiThread(HttpServletRequest request) {
////		logger.info("[+] [HomeController]-[multi]-[request uri and ip]: " + request.getRequestURI() + "、" + request.getRemoteAddr());
//		
////		測試多執行緒
////		Executor executor = Executors.newFixedThreadPool(100);
////		for (int i = 0; i < 100; i++) {
////			if(i == 0){
////				executor.execute(new Runnable(){
////
////					@Override
////					public void run() {
////						logger.info("[+] [TestServiceImpl]-[testMultiThread]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
////						testService.testMultiO();
////					}
////					
////				});
////			}else if(i%2 == 0){
////				executor.execute(new Runnable(){
////
////					@Override
////					public void run() {
////						logger.info("[+] [TestServiceImpl]-[testMultiThread]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
////						testService.testMultiO();
////					}
////					
////				});
////			}else{
////				executor.execute(new Runnable(){
////
////					@Override
////					public void run() {
////						logger.info("[+] [TestServiceImpl]-[testMultiThread]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
////						testService.testMultiT();
////					}
////					
////				});
////			}
////			
////
////		}
//				
//		return ResponseEntity.ok().body("multi");
//	}
	
//	/**暫時不啟用，用web.xml的404設定
//	 * 用來捕捉，404
//	 * @param request
//	 * @return
//	 */
//	@GetMapping("*")
//	public JsonDataModelAndView noMapping(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<String, Object>(1);
//		map.put("errmsg", "請求路徑不正確，請確認");
//		return new JsonDataModelAndView(map);
//	}
	
//	@GetMapping("/cache")
//	public ResponseEntity<?> testCache(HttpServletRequest request) {
////		logger.info("[+] [HomeController]-[testCache]-[request uri and ip]: " + request.getRequestURI() + "、" + request.getRemoteAddr());
//		
////		測試多執行緒
//		Executor executor = Executors.newFixedThreadPool(100);
//		for (int i = 0; i < 5; i++) {
//			if(i == 0){
//				executor.execute(new Runnable(){
//
//					@Override
//					public void run() {
//						logger.info("[+] [TestServiceImpl]-[testCache]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
//						testService.testCacheQueryTest();
//					}
//					
//				});
//			}else if(i%2 == 0){
//				executor.execute(new Runnable(){
//
//					@Override
//					public void run() {
//						logger.info("[+] [TestServiceImpl]-[testCache]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
//						testService.testCacheQueryTest();
//					}
//					
//				});
//			}else{
//				executor.execute(new Runnable(){
//
//					@Override
//					public void run() {
//						logger.info("[+] [TestServiceImpl]-[testCache]-[Runnable]-[thread name {}]", Thread.currentThread().getName());
//						testService.testNonCacheQueryTest();
//					}
//					
//				});
//			}
//		}
//				
//		return ResponseEntity.ok().body("cache");
//	}
	
}
