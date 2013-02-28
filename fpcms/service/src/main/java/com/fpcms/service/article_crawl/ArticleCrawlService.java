package com.fpcms.service.article_crawl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.duowan.common.util.DateConvertUtils;
import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.common.random_gen_article.BaiduTopBuzzUtil;
import com.fpcms.common.random_gen_article.NaipanArticleGeneratorUtil;
import com.fpcms.common.util.ApplicationContextUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.EmptySearchResultException;
import com.fpcms.common.util.GoogleTranslateUtil;
import com.fpcms.common.util.RegexUtil;
import com.fpcms.common.util.SearchEngineUtil;
import com.fpcms.common.util.URLEncoderUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsKeyValueService;

/**
 * 从其它网站进行文章采集的service
 * 
 * @author badqiu
 *
 */
public class ArticleCrawlService implements ApplicationContextAware,InitializingBean{
	private static Logger logger = LoggerFactory.getLogger(ArticleCrawlService.class);
	
	private List<SinglePageCrawler> singlePageCrawlerList = new ArrayList<SinglePageCrawler>();
	private HtmlPageCrawler htmlPageCrawler = new HtmlPageCrawlerImpl();
	private CmsContentService cmsContentService;
	private ApplicationContext applicationContext;
	private CmsKeyValueService cmsKeyValueService;
	
	public void loadSinglePageCrawlerList() {
		singlePageCrawlerList = ApplicationContextUtil.getBeans(applicationContext,SinglePageCrawler.class);
	}

	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}
	
	public void setCmsKeyValueService(CmsKeyValueService cmsKeyValueService) {
		this.cmsKeyValueService = cmsKeyValueService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 爬网站
	 */
	public void crawlAllSite() {
		for(SinglePageCrawler crawler : singlePageCrawlerList) {
			crawler.setHtmlPageCrawler(htmlPageCrawler);
			crawler.execute();
		}
	}
	
	/**
	 * 爬热门关键词
	 */
	public void crawlAllBuzzKeyword() {
		Set<String> buzzList = BaiduTopBuzzUtil.getBaiduBuzzs();
		
		for(final String buzz : buzzList) {
			CmsKeyValue cmsKeyValue = new CmsKeyValue(Constants.KEY_VALUE_GROUP_SEARCH_BUZZ,buzz);
			if(cmsKeyValueService.exist(cmsKeyValue)) {
				logger.info("ignore search,already_search_buzz:"+buzz);
				continue;
			}
			
			cmsKeyValueService.create(cmsKeyValue);
			
			final String finalSearchKeyword = URLEncoderUtil.encode(buzz + " " + DateConvertUtils.format(new Date(), "yyyy年MM月"));
			String searchUrl = "https://www.google.com.hk/search?num=20&hl=zh-CN&safe=strict&tbs=qdr:d&q="+finalSearchKeyword;
			SinglePageCrawler crawler = new SinglePageCrawler();
			crawler.setUrlList(searchUrl);
			crawler.setSourceLang("zh-CN");
			crawler.setExcludeUriRegexList(".*google.*",".*youtube.*",".*blogger.*");
			crawler.setHtmlPageCrawler(new HtmlPageCrawlerImpl() {
				@Override
				public void prepareCmsContent(CmsContent c) {
					c.setTitle("图片故事-"+c.getTitle());
				}
			});
			crawler.execute();
		}
		
	}


	
	/**
	 * 合并过于短小的文章
	 */
	public void mergeSmallArticle() {
		DateRange createdRange = new DateRange(DateUtils.addDays(new Date(),-5), new Date());
		Page<CmsContent> page = cmsContentService.findPage(new PageQuery(1000), Constants.CRAWL_SITE, Constants.CRAWL_CHANNEL_CODE, createdRange);
		List<CmsContent> list = page.getItemList();
		
		for(int i = 0; i < list.size(); i+=2) {
			if(i + 1 >= list.size()) {
				break;
			}
			
			CmsContent one = list.get(i);
			CmsContent two = list.get(i+1);
			int MERGE_MIN_LENGTH = 450;
			if(one.getContent().length() < MERGE_MIN_LENGTH || two.getContent().length() < MERGE_MIN_LENGTH) {
				logger.info("mrege small cms_content,id:"+one.getId()+" with id:"+two.getId()+", one title:"+one.getTitle()+", two title:"+two.getTitle());
				
				String mergeTitle = one.getTitle()+";"+two.getTitle();
				String mergeContent = "<h1>"+one.getTitle()+"</h1><p>"+one.getContent()+"</p><h1>"+two.getTitle()+"</h1><p>"+two.getContent()+"</p>";
				one.setTitle(mergeTitle);
				one.setContent(mergeContent);
				cmsContentService.update(one);
				cmsContentService.removeById(two.getId());			
			}
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
			CmsContent c = buildCmsContent(page);
			if(c != null) {
				prepareCmsContent(c);
				cmsContentService.create(c);
			}
		}
		protected void prepareCmsContent(CmsContent c) {
		}
	}
	
	private CmsContent buildCmsContent(HtmlPage page) {
		if(hasFilterKeyword(page.getTitle(),page.getContent())) {
			return null;
		}
		
		CmsContent c = new CmsContent();
		String content = null;
		String title = null;
		if("zh-cn".equalsIgnoreCase(page.getSourceLang()) || "zh-tw".equalsIgnoreCase(page.getSourceLang())) {
			content = NaipanArticleGeneratorUtil.transformArticle(GoogleTranslateUtil.reverseTwoWayTranslate(page.getContent(),"zh-CN","en"));
			title = NaipanArticleGeneratorUtil.transformArticle(GoogleTranslateUtil.reverseTwoWayTranslate(page.getTitle(),"zh-CN","en"));
//		}else if("zh-tw".equalsIgnoreCase(page.getSourceLang())) {
//			c.setTitle(NaipanArticleGeneratorUtil.transformArticle(JChineseConvertor.getInstance().t2s(page.getTitle())));
//			c.setContent(NaipanArticleGeneratorUtil.transformArticle(JChineseConvertor.getInstance().t2s(page.getContent())));
		}else {
			content = GoogleTranslateUtil.translate(page.getContent(),page.getSourceLang(),"zh-CN");
			title = GoogleTranslateUtil.translate(page.getTitle(),page.getSourceLang(),"zh-CN");
		}
		
		c.setContent(content);
		c.setTitle(title);
		
		if(hasFilterKeyword(c.getTitle(),c.getContent())) {
			return null;
		}
		if(StringUtils.isBlank(c.getContent())) {
			return null;
		}
		
		if(SearchEngineUtil.baiduKeywordsNotExist(c.getTitle())) {
			logger.info("baidu_not_exist article:"+c.getTitle());
			
			c.setSourceUrl(page.getAnchor().getHref());
			c.setSite(Constants.CRAWL_SITE);
			c.setChannelCode(Constants.CRAWL_CHANNEL_CODE);
			c.setAuthor(Constants.CRAWL_AUTHOR);
			return c;
		}else {
			throw new RuntimeException("百度已经存在该文章,title:"+c.getTitle());
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
		Assert.notNull(cmsKeyValueService,"cmsKeyValueService must be not null");
		loadSinglePageCrawlerList();
		Assert.notEmpty(singlePageCrawlerList,"singlePageCrawlerList must be not empty");
	}
	
}
