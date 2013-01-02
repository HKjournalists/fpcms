package com.fpcms.home.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.BaseController;
import com.fpcms.model.CmsChannel;
import com.fpcms.service.CmsChannelService;

@Controller
public class SitemapController extends BaseController{
	private CmsChannelService cmsChannelService;
	
	public void setCmsChannelService(CmsChannelService cmsChannelService) {
		this.cmsChannelService = cmsChannelService;
	}

	@RequestMapping("/sitemap.xml")
	public String sitemap(ModelMap model) throws Exception {
		String site = getSite();
		model.put("site", site);
		List<CmsChannel> navChannelList = cmsChannelService.findChildsByChannelCode(site, CmsChannel.NAV.getChannelCode());
		model.put("navChannelList", navChannelList);
		
		return "/sitemap";
	}
	
}
