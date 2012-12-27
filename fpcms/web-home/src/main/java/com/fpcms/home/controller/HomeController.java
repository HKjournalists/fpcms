package com.fpcms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsContentQuery;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsPropertyService;

@Controller
public class HomeController extends BaseController{
	@Autowired(required=true)
	private CmsChannelService cmsChannelService;
	
	@Autowired(required=true)
	private CmsContentService cmsContentService;
	
	@Autowired(required=true)
	private CmsPropertyService cmsPropertyService;
	
	
	/** 显示 */
	@RequestMapping()
	public String home(ModelMap model) throws Exception {
		model.put("nav", cmsChannelService.findChildsByChannelCode(getSite(),"nav"));
//		model.put("area", cmsChannelService.findChildsByChannelCode("area"));
		
		CmsContentQuery query = new CmsContentQuery();
		query.setPageSize(20);
		query.setChannelCode(Constants.CHANNED_CODE_NEWS);
		query.setSite(getSite());
		model.put("newsPage", cmsContentService.findPage(query));
		
		model.put("hot_news", cmsContentService.findByChannelCode(getSite(),"hot_news"));
		model.put("news", cmsContentService.findByChannelCode(getSite(),Constants.CHANNED_CODE_NEWS));
		
		model.put("category", cmsChannelService.findChildsByChannelCode(getSite(),"category"));
		model.putAll(cmsPropertyService.findByGroup(Constants.PROPERTY_DEFAULT_GROUP));
		return "/home";
	}
	
}
