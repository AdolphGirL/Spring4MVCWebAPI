package com.kangdainfo.webapi.util;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.google.gson.Gson;

/**
 * 自訂義回傳的ModelAndView，JSON
 *
 */
public class JsonDataModelAndView extends ModelAndView {
	
	private static final Gson UTIL_GSON = new Gson();
	private Object objectJson;
	
	public JsonDataModelAndView(Object objectJson){
		super();
		this.objectJson= objectJson;
	}
	
	@Override
	public View getView() {
		return new View() {

			@Override
			public String getContentType() {
				 return "application/json;charset=utf-8";
			}

			@Override
			public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				response.setContentType(getContentType());
				
				ServletOutputStream out = response.getOutputStream();
				byte[] tmp = null;
				String json = null;
				try{
					if(objectJson != null){
						json = UTIL_GSON.toJson(objectJson);
						tmp = json.getBytes("utf-8");
						out.write(tmp);
						out.flush();
					}
				}catch(Exception x){
					x.printStackTrace();
				}finally{
					try{
						if(out != null){
							out.close();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
					tmp = null;
					json = null;
				}
			}
			
		};
	}
	
	
}
