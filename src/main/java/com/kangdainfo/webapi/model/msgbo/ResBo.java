package com.kangdainfo.webapi.model.msgbo;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ResBo implements java.io.Serializable {
	
	private String result;
	private Map<String, String> errData;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Map<String, String> getErrData() {
		return errData;
	}
	public void setErrData(Map<String, String> errData) {
		this.errData = errData;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
