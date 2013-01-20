/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.home.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.WebUtil;
import com.fpcms.model.CmsChannel;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsChannelQuery;
import com.fpcms.query.CmsContentQuery;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsContentService;


/**
 * [CmsChannel] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController{
	
	private CmsChannelService cmsChannelService;
	private CmsContentService cmsContentService;
	
	private final String LIST_ACTION = "redirect:/admin/cmschannel/refreshParent.jsp";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsChannelService(CmsChannelService service) {
		this.cmsChannelService = service;
	}
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}



	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
	}
	
	/** 列表 */
	@RequestMapping("/show/{channelCode}.do")
	public String show(ModelMap model,CmsChannelQuery query,@PathVariable("channelCode") String channelCode,HttpServletRequest request,HttpServletResponse response) {
		CmsChannel cmsChannel = cmsChannelService.findByChannelCode(getSite(),channelCode);
		if(StringUtils.isBlank(cmsChannel.getContent())) {
			return "redirect:/channel/showContentList/"+channelCode+"/1.do";
		}
		
		model.put("cmsChannel", cmsChannel);
		
		return "/channel/show";
	}
	
	@RequestMapping("/showContentList/{channelCode}/{page}.do")
	public String showContentList(ModelMap model,HttpServletRequest request,@PathVariable("channelCode") String channelCode,@PathVariable("page") int page) {
		CmsChannel cmsChannel = cmsChannelService.findByChannelCode(getSite(),channelCode);
		
		DateRange dateRange = new DateRange(DateUtils.addDays(new Date(),-45),new Date());
		Page<CmsContent> cmsContentPage = cmsContentService.findPage(new PageQuery(page,20),getSite(),channelCode,dateRange);
		
		model.put("cmsChannel", cmsChannel);
		model.put("page", cmsContentPage);
		return "/channel/showContentList";
	}
	
}

