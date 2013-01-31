package com.fpcms.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsSiteService;

public class CmsSiteUtil {

	public static String getSite(HttpServletRequest request,CmsSiteService cmsSiteService) {
		String site = URLUtil.getHostSite(request.getRequestURL().toString());
		CmsSite cmsSite = cmsSiteService.getById(site);
		if(cmsSite == null) {
			return null;
		}
		return cmsSite.getSiteDomain();
	}
	
	
	public static String getDomain(String site) {
		if(StringUtils.isBlank(site)) {
			return null;
		}
		
		int first = site.lastIndexOf(".");
		if(first >= 0) {
			int second = site.substring(0,first-1).lastIndexOf(".");
			if(second >= 0) {
				return site.substring(second + 1,site.length());
			}else {
				return site;
			}
		}
		return site;
	}
	
}
