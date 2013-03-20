package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.DomainUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.StringHelper;
import com.fpcms.common.util.Tags;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsSite;
import com.fpcms.model.CmsSitePropertyEnum;
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
		super("0 1 11 * * *");
	}
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public synchronized void execute() {
		List<CmsContent> list = findCmsContentList();
		List<CmsSite> siteList = cmsSiteService.findAll();
		RuntimeException lastException = null;
		for (CmsSite cmsSite : siteList) {
			try {
				if(list.isEmpty()) 
					break;
				assignArticle2Site(cmsSite,list);
			}catch(RuntimeException e) {
				logger.error("assign content 2 site error,on cmsSite:"+cmsSite.getSiteDomain(),e);
				lastException = e;
			}
		}
		
		if(lastException != null) {
			throw lastException;
		}
	}
	
	public void assignArticle2Site(CmsSite cmsSite,List<CmsContent> contentList) {
		String site = cmsSite.getSiteDomain();
		String siteExceptContentTags = cmsSite.getProperty(CmsSitePropertyEnum.PROP_EXPECT_CONTENT_TAGS.getCode());
		if(DomainUtil.isMainSite(site)) {
			CmsContent content = null;
			Assert.hasText(siteExceptContentTags,"siteExceptContentTags must be not empty on site:"+site);
			content = findExpectContentByTags(siteExceptContentTags,contentList);
			
			if(content == null) {
				logger.warn("not found CmsContent for site:"+site+" siteExceptContentTags:"+siteExceptContentTags);
				return;
			}
			
			content.setSite(site);
			content.setDateCreated(new Date());
			content.setChannelCode(Constants.CHANNED_CODE_NEWS);
			
			if(RandomUtil.randomTrue(65)) {
				content.setTitle(processWithSiteKeyword(content.getTitle(),"发票",cmsSite.getKeyword()));
				content.setContent(processWithSiteKeyword(content.getContent(),"发票",cmsSite.getKeyword()));
			}
			if(RandomUtil.randomTrue(45)) {
				String anchor = String.format("<a href='http://%s'>%s</a>",cmsSite.getSiteDomain(),KeywordUtil.getRandomKeyword(cmsSite.getKeyword()));
				content.setContent(StringHelper.insertAfter(content.getContent(),anchor,",","，"));
			}
			
			logger .info("assign article for site:"+site+" article.title:"+content.getTitle()+" on channel:"+content.getChannelCode());
			cmsContentService.update(content);
//			CmsContent.baiduBlogPing(content);
		}
	}
	
	public static String processWithSiteKeyword(String text,String searchString,String keywords) {
		String keyword = KeywordUtil.getRandomKeyword(keywords);
		if(StringUtils.isBlank(keyword)) {
			return text;
		}
		return StringUtils.replace(text, searchString, keyword);
	}

	private List<CmsContent> findCmsContentList() {
		CmsContentQuery query = new CmsContentQuery();
		query.setChannelCode(Constants.CRAWL_CHANNEL_CODE);
		query.setSite(Constants.CRAWL_SITE);
		query.setAuthor(Constants.CRAWL_AUTHOR);
		query.setDateCreatedBegin(DateUtils.addDays(new Date(), -15));
		query.setDateCreatedEnd(new Date());
		query.setPageSize(5000);
		
		List<CmsContent> list = new ArrayList<CmsContent>(cmsContentService.findPage(query).getItemList());
		return list;
	}

	private CmsContent findExpectContentByTags(String siteExceptContentTags,List<CmsContent> list) {
		for(ListIterator<CmsContent> it = list.listIterator();it.hasNext();) {
			CmsContent c = it.next();
			if(Tags.containOne(siteExceptContentTags, c.getTags())) {
				it.remove();
				return c;
			}
		}
		return null;
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
