package com.fpcms.home.webservice.impl;

import java.util.Map;

import com.fpcms.home.webservice.PropertyWebService;
import com.fpcms.service.CmsPropertyService;

public class PropertyWebServiceImpl implements PropertyWebService{
	private CmsPropertyService cmsPropertyService;
	
	public void setCmsPropertyService(CmsPropertyService cmsPropertyService) {
		this.cmsPropertyService = cmsPropertyService;
	}

	public Map<String,String> getSiteProperty(String site) {
		return cmsPropertyService.findByGroup(site);
	}
	
}
