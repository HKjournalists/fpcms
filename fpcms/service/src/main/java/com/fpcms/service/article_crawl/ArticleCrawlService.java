package com.fpcms.service.article_crawl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.fpcms.common.random_gen_article.NaipanArticleGeneratorUtil;
import com.fpcms.common.util.ApplicationContextUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.EmptySearchResultException;
import com.fpcms.common.util.GoogleTranslateUtil;
import com.fpcms.common.util.JChineseConvertor;
import com.fpcms.common.util.RegexUtil;
import com.fpcms.common.util.SearchEngineUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.model.CmsContent;
import com.fpcms.service.CmsContentService;

/**
 * 从其它网站进行文章采集的service
 * 
 * @author badqiu
 *
 */
public class ArticleCrawlService implements ApplicationContextAware,InitializingBean{
	
	private List<SinglePageCrawler> singlePageCrawlerList = new ArrayList<SinglePageCrawler>();
	private HtmlPageCrawler htmlPageCrawler = new HtmlPageCrawlerImpl();
	private CmsContentService cmsContentService;
	private ApplicationContext applicationContext;
	
	public void loadSinglePageCrawlerList() {
		singlePageCrawlerList = ApplicationContextUtil.getBeans(applicationContext,SinglePageCrawler.class);
	}

	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void crawlAllSite() {
		for(SinglePageCrawler crawler : singlePageCrawlerList) {
			crawler.setHtmlPageCrawler(htmlPageCrawler);
			crawler.execute();
		}
	}
	
	public List<String> getInvalidUrlList() {
		List<String> invalidUrlList = new ArrayList<String>();
		for(SinglePageCrawler crawler : getSinglePageCrawlerList()) {
			for(String url : crawler.getUrlList()) {
				try {
					List<Anchor> list = crawler.getShoudVisitAnchorList(url);
					if(CollectionUtils.isEmpty(list)) {
						invalidUrlList.add(url);
					}
				}catch(Exception e) {
					invalidUrlList.add(url);
				}
			}
		}
		return invalidUrlList;
	}
	
	public List<SinglePageCrawler> getSinglePageCrawlerList() {
		return singlePageCrawlerList;
	}
	
	private class HtmlPageCrawlerImpl implements HtmlPageCrawler {

		@Override
		public boolean shoudVisitPage(Anchor a) {
			Date start = DateUtils.addDays(new Date(),-160);
			Date end = new Date();
			int count = cmsContentService.countBySourceUrl(start, end, a.getHref());
			if(count > 0) {
				return false;
			}
			return true;
		}

		@Override
		public void visit(HtmlPage page) {
			if(hasFilterKeyword(page.getTitle(),page.getContent())) {
				return;
			}
			
			CmsContent c = new CmsContent();
			if("zh-cn".equals(page.getSourceLang())) {
				c.setContent(NaipanArticleGeneratorUtil.transformArticle(page.getContent()));
				c.setTitle(NaipanArticleGeneratorUtil.transformArticle(page.getTitle()));
//			}else if("zh-tw".equalsIgnoreCase(page.getSourceLang())) {
//				c.setTitle(NaipanArticleGeneratorUtil.transformArticle(JChineseConvertor.getInstance().t2s(page.getTitle())));
//				c.setContent(NaipanArticleGeneratorUtil.transformArticle(JChineseConvertor.getInstance().t2s(page.getContent())));
			}else {
				c.setContent(GoogleTranslateUtil.translate(page.getContent(),page.getSourceLang(),"zh-CN"));
				c.setTitle(GoogleTranslateUtil.translate(page.getTitle(),page.getSourceLang(),"zh-CN"));
			}
			
			if(hasFilterKeyword(c.getTitle(),c.getContent())) {
				return;
			}
			if(StringUtils.isBlank(c.getContent())) {
				return;
			}
			
			try {
				SearchEngineUtil.baiduSearch("\""+c.getTitle()+"\"", 100, 1);
				throw new RuntimeException("百度已经存在该文章,title:"+c.getTitle());
			}catch(EmptySearchResultException e) {
				c.setSourceUrl(page.getAnchor().getHref());
				c.setSite(Constants.CRAWL_SITE);
				c.setChannelCode(Constants.CRAWL_CHANNEL_CODE);
				c.setAuthor(Constants.CRAWL_AUTHOR);
				cmsContentService.create(c);
			}
		}
		

	}
	
	static List<String> filterWords = Arrays.asList("\\u","http://","www.","代开","开发票","买发票","卖发票","销售发票");
	static List<String> filterRegex = Arrays.asList("开.*发票","发票.*代开","发票.*开","假.*发票");
	static boolean hasFilterKeyword(String... contents) {
		if(contents == null) return false;
		
		for(String c : contents) {
			if(StringUtils.isBlank(c)) {
				continue;
			}
			
			for(String keyword : filterWords) {
				if(c.contains(keyword)) {
					return true;
				}
			}
			for(String regex : filterRegex) {
				if(c.contains(regex)) {
					return true;
				}
				if(RegexUtil.findByRegexGroup(c, regex, 0) != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(applicationContext,"applicationContext must be not null");
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		loadSinglePageCrawlerList();
		Assert.notEmpty(singlePageCrawlerList,"singlePageCrawlerList must be not empty");
	}
	
}
