package com.fpcms.home.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.BaseController;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsSiteService;
@Controller
public class LayoutController extends BaseController{

	@Autowired(required=true)
	private CmsChannelService cmsChannelService;
	
	@Autowired(required=true)
	private CmsSiteService cmsSiteService;
	
	@RequestMapping
	public String layout(ModelMap model) throws Exception {
		model.put("nav", cmsChannelService.findChildsByChannelCode(getSite(),"nav"));
		CmsSite cmsSite = cmsSiteService.getById(getSite());
		String htmlLayout = cmsSite.getHtmlLayout();
		if(StringUtils.isBlank(htmlLayout)) {
			return "/layout";
		}else {
			return "/layout/"+htmlLayout;
		}
	}
	
}
