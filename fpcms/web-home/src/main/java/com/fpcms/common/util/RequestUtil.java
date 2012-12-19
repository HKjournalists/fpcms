package com.fpcms.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtil {

	public static String getAndCreateSession(HttpServletRequest request,String name) {
		String overrideValue = request.getParameter(name);
		if(StringUtils.isNotBlank(overrideValue)) {
			request.getSession().setAttribute(name, overrideValue);
			return overrideValue;
		}
		
		String value = (String)request.getSession().getAttribute(name);
		if(StringUtils.isNotBlank(value)) {
			request.getSession().setAttribute(name, value);
		}
		return value;
	}
	
}
