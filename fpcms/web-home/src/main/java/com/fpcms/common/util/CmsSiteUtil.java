package com.fpcms.common.util;

import javax.servlet.http.HttpServletRequest;

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
	
}
