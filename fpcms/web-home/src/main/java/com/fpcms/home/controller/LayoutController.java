package com.fpcms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsPropertyService;
@Controller
public class LayoutController extends BaseController{

	@Autowired(required=true)
	private CmsChannelService cmsChannelService;
	
	@Autowired(required=true)
	private CmsPropertyService cmsPropertyService;
	
	
	@RequestMapping
	public String layout(ModelMap model) throws Exception {
		model.putAll(cmsPropertyService.findByGroup(Constants.PROPERTY_DEFAULT_GROUP));
		
		model.put("nav", cmsChannelService.findChildsByChannelCode(getSite(),"nav"));
		model.put("category", cmsChannelService.findChildsByChannelCode(getSite(),"category"));
		return "/layout";
	}
	
}
