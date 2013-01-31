package com.fpcms.common.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	/**
	 * 通过301 header永久重定向
	 * @param response
	 * @param location
	 */
	public static void send301Redirect(HttpServletResponse response, String location) {
		response.addHeader("Location", location);
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	}

	
	/**
	 * 根据lastModified时间，判断有没有修改
	 * @param request
	 * @param response
	 * @param lastModified
	 * @return
	 */
	public static boolean isNotModified(HttpServletRequest request,
			HttpServletResponse response, Date lastModified) {
		if(AppModeUtil.isDevAppMode()) {
			return false;
		}
		if(lastModified == null) {
			return false;
		}
		response.setDateHeader("Last-Modified", lastModified.getTime());
		
		String ifModifiedSince = request.getHeader("If-Modified-Since");
		if(ifModifiedSince == null) {
			return false;
		}
		Date ifModifiedSenceDate = new Date(ifModifiedSince);
		
//		System.out.println("ifModifiedSince:"+ifModifiedSince+" ifModifiedSenceDate:"+DateFormatUtils.format(ifModifiedSenceDate, "yyyy-MM-dd HH:mm:ss") + " dateLastModified:"+DateFormatUtils.format(lastModified, "yyyy-MM-dd HH:mm:ss"));
		if(ifModifiedSenceDate.equals(lastModified)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return true;
		}
		return false;
	}

}
