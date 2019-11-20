package com.kangdainfo.webapi.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kangdainfo.webapi.model.msgbo.ResBo;
import com.kangdainfo.webapi.service.ImfService;
import com.kangdainfo.webapi.util.JsonDataModelAndView;

@RestController
public class ValidController {
	
	@Autowired
	private ImfService imfService;
	
	@GetMapping("/2001/{kind}/{type}/{id}")
	public JsonDataModelAndView validateImfData(@PathVariable String kind, @PathVariable String type, @PathVariable String id, HttpServletRequest request) {
		ResBo res = new ResBo();
		
		return new JsonDataModelAndView(res);
	}
	
}
