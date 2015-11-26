package com.fpcms.home.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.rapid.common.util.DateRange;
import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.util.page.PageQuery;
import com.fpcms.common.BaseController;
import com.fpcms.common.util.CmsSiteUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsDomainService;
import com.fpcms.service.CmsPropertyService;
import com.fpcms.service.CmsSiteService;

@Controller
public class HomeController extends BaseController{
	@Autowired(required=true)
	private CmsChannelService cmsChannelService;
	
	@Autowired(required=true)
	private CmsContentService cmsContentService;
	
	@Autowired(required=true)
	private CmsPropertyService cmsPropertyService;
	
	@Autowired(required=true)
	private CmsSiteService cmsSiteService;
	
	private Map<String,List<CmsContent>> subSiteNewsPageMap = new HashMap<String,List<CmsContent>>();
	
	/** 显示 */
	@RequestMapping()
	public String home(ModelMap model) throws Exception {
		model.put("home", cmsChannelService.findByChannelCode(getSite(),"home"));
		
		DateRange dateRange = new DateRange(DateUtils.addDays(new Date(),-20),new Date());
		model.put("newsPage", cmsContentService.findPage(new PageQuery(20),getSite(),Constants.CHANNED_CODE_NEWS,dateRange));
		
		Page<CmsContent> page = cmsContentService.findBySiteLike(new PageQuery(100), getSite(), Constants.CHANNED_CODE_NEWS, dateRange);
		model.put("subSiteNewsList",RandomUtil.randomSelectList(page.getItemList(),10));
		
		List<CmsSite> subSiteList = cmsSiteService.findSubSites(CmsSiteUtil.getDomain(getSite()));
		model.put("subSiteList", subSiteList);
		
		model.put("allSiteList", cmsSiteService.findAll());
		return "/home";
	}
	
}
