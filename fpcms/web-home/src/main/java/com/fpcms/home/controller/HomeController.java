package com.fpcms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
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
		model.put("home", cmsChannelService.findByChannelCode(getSite(),"home"));
		model.put("newsPage", cmsContentService.findPage(newNewsQuery()));
		model.put("news", cmsContentService.findByChannelCode(getSite(),Constants.CHANNED_CODE_NEWS));
		return "/home";
	}


	private CmsContentQuery newNewsQuery() {
		CmsContentQuery newsQuery = new CmsContentQuery();
		newsQuery.setPageSize(20);
		newsQuery.setChannelCode(Constants.CHANNED_CODE_NEWS);
		newsQuery.setSite(getSite());
		return newsQuery;
	}
	
}
