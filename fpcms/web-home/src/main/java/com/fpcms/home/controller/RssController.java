package com.fpcms.home.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import churchillobjects.rss4j.RssChannel;
import churchillobjects.rss4j.RssChannelItem;
import churchillobjects.rss4j.RssDocument;
import churchillobjects.rss4j.generator.RssGenerationException;
import churchillobjects.rss4j.generator.RssGenerator;

import com.github.rapid.common.util.DateRange;
import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.util.page.PageQuery;
import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
import com.fpcms.model.CmsContent;
import com.fpcms.service.CmsContentService;

@Controller
public class RssController extends BaseController{
	@Autowired(required=true)
	private CmsContentService cmsContentService;
	
	@RequestMapping("/rss.xml")
	public void rss(HttpServletResponse response) throws RssGenerationException, IOException {
		DateRange dateRange = new DateRange(DateUtils.addDays(new Date(),-20),new Date());
		Page<CmsContent> page = cmsContentService.findPage(new PageQuery(15), getSite(), Constants.CHANNED_CODE_NEWS, dateRange);
		
		RssChannel channel = new RssChannel();
		channel.setChannelTitle("新闻");
		String channelUrl = "http://"+getSite()+"/"+Constants.CHANNED_CODE_NEWS+".do";
		channel.setChannelUri(channelUrl);
		channel.setChannelDescription("新闻频道");
		channel.setChannelLink(channelUrl);
		channel.setChannelLanguage("zh-cn");
		for(CmsContent content : page) {
			RssChannelItem item = new RssChannelItem();
			item.setItemLink(content.getUrl());
			item.setItemTitle(content.getTitle());
			item.setItemDescription(content.getMetaDescription());
			channel.addItem(item);
		}
		
		RssDocument doc = new RssDocument();
		doc.setVersion(RssDocument.VERSION_91);
		doc.addChannel(channel);
		
		response.setContentType("text/xml;charset=UTF-8");
		RssGenerator.generateRss(doc,response.getWriter());
	}
}
