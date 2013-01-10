package com.fpcms.home.controller;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.PageQuery;
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
		
		DateRange dateRange = new DateRange(DateUtils.addDays(new Date(),-20),new Date());
		model.put("newsPage", cmsContentService.findPage(new PageQuery(20),getSite(),Constants.CHANNED_CODE_NEWS,dateRange));
		return "/home";
	}
	
}
