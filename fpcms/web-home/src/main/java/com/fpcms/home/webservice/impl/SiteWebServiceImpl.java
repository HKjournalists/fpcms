package com.fpcms.home.webservice.impl;

import java.util.Map;

import com.fpcms.home.webservice.SiteWebService;
import com.fpcms.service.CmsPropertyService;
import com.fpcms.service.CmsSiteService;

public class SiteWebServiceImpl implements SiteWebService{
	private CmsSiteService cmsSiteService;
	
	

	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}



	public Map<String,String> getSiteProperties(String site) {
		return cmsSiteService.getSiteProperties(site);
	}
	
}
