package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.DomainUtil;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsContentQuery;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsSiteService;

/**
 * 将采集回来的文章分发至各个网站
 * 
 * @author badqiu
 *
 */
@Service
public class DistributingArticles2SiteJob extends BaseCronJob implements InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(DistributingArticles2SiteJob.class);
	
	private CmsContentService cmsContentService;
	private CmsSiteService cmsSiteService;
	
	public DistributingArticles2SiteJob() {
//		super("0 * * * * *");
		super("0 1 8,16 * * *");
	}
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public void execute() {
		CmsContentQuery query = new CmsContentQuery();
		query.setChannelCode(Constants.CRAWL_CHANNEL_CODE);
		query.setSite(Constants.CRAWL_SITE);
		query.setAuthor(Constants.CRAWL_AUTHOR);
		query.setDateCreatedBegin(DateUtils.addDays(new Date(), -15));
		query.setDateCreatedEnd(new Date());
		query.setPageSize(5000);
		
		List<CmsContent> list = new ArrayList<CmsContent>(cmsContentService.findPage(query).getItemList());
		List<CmsSite> siteList = cmsSiteService.findAll();
		for (CmsSite cmsSite : siteList) {
			if(list.isEmpty()) 
				break;
			
			String site = cmsSite.getSiteDomain();
			if(DomainUtil.isMainSite(site)) {
				CmsContent content = list.remove(0);
				content.setSite(site);
				content.setDateCreated(new Date());
				logger .info("assign article for site:"+site+" article.title:"+content.getTitle());
				cmsContentService.update(content);
				CmsContent.baiduBlogPing(content);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		super.afterPropertiesSet();
	}
	
	@Override
	public String getJobRemark() {
		return "分发文章至网站";
	}
}
