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
import com.fpcms.common.util.GoogleTranslateUtil;
import com.fpcms.common.util.HtmlFormatUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RegexUtil;
import com.fpcms.common.util.SearchEngineUtil;
import com.fpcms.common.util.TextLangUtil;
import com.fpcms.common.util.URLEncoderUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsKeyValueService;
import com.fpcms.service.CmsSiteService;

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
	private CmsSiteService cmsSiteService;
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
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 爬网站
	 */
	public synchronized void crawlAllSite() {
		for(SinglePageCrawler crawler : singlePageCrawlerList) {
			crawler.setHtmlPageCrawler(htmlPageCrawler);
			crawler.execute();
		}
	}

	/**
	 * 爬每个站点的关键词
	 */
	public synchronized void crawlAllSiteKeyword() {
		for(final CmsSite cmsSite : cmsSiteService.findAll()) {
			ArrayList<String> keywordList = KeywordUtil.toTokenizerList(cmsSite.getKeyword());
			if(keywordList.isEmpty()) {
				continue;
			}
			
			String keyword = keywordList.get(0);
			crawByKeyword(keyword,keyword,new HtmlPageCrawlerImpl() {
				@Override
				protected void prepareCmsContent(CmsContent c) {
					super.prepareCmsContent(c);
					c.setSite(cmsSite.getSiteDomain());
				}
			});
		}
		
	}
	
	/**
	 * 爬热门关键词
	 */
	public synchronized void crawlAllBuzzKeyword() {
		Set<String> buzzList = BaiduTopBuzzUtil.getBaiduBuzzs();
		for(final String buzz : buzzList) {
			crawByKeyword(buzz,"buzz",new HtmlPageCrawlerImpl() {
				@Override
				public void prepareCmsContent(CmsContent c) {
					c.setTitle("图片故事-"+c.getTitle());
				}
			});
		}
	}

	/**
	 * 爬发票关键词
	 */
	public synchronized List<CmsContent> crawlKeyword(String keyword) {
		return crawlByKeyword("zh_fapiao","zh-CN",keyword,keyword,"zh-CN");
	}

	public synchronized List<CmsContent> crawlByKeyword(String tags,String sourceLang,final String searchKeyword,final String replaceKeyword,String hl) {
		final List<CmsContent> resultCollector = new ArrayList<CmsContent>();
		List<String> urls = buildSearchUrl(searchKeyword,10,hl,true);
		
		SinglePageCrawler crawler = newGoogleSinglePageCrawler(tags,sourceLang,new HtmlPageCrawlerImpl(){
			@Override
			public void visit(HtmlPage page) {
				page.setTitle(replaceWithCaseInsentisive(page.getTitle(),searchKeyword, replaceKeyword));
				page.setContent(replaceWithCaseInsentisive(page.getContent(),searchKeyword,replaceKeyword));
				CmsContent c = buildCmsContent(page,new NaipanTransformer());
				if(c != null) {
					cmsContentService.create(c);
					resultCollector.add(c);
				}
			}


		},urls.toArray(new String[0]));
		crawler.execute();
		return resultCollector;
	}

	static String replaceWithCaseInsentisive(String string,final String searchKeyword,
			final String replaceKeyword) {
		return string.replaceAll("(?i)"+searchKeyword,replaceKeyword);
	}
	
	private List<String> buildSearchUrl(String keyword,int pageCount,String hl,boolean keywordAllintitle) {
		List<String> urls = new ArrayList<String>();
		for(int i = 0; i < pageCount; i++) {
			int num = 100;
			int start = 0 * num;
			String encodeKeyword = keywordAllintitle ? URLEncoderUtil.encode("allintitle:"+keyword) : URLEncoderUtil.encode(keyword);
			String searchUrl = "https://www.google.com.hk/search?q="+encodeKeyword+"&num="+num+"&hl="+hl+"&biw=1440&bih=702&tbm=nws&start="+start+"&tbs=qdr:d";
			urls.add(searchUrl);
		}
		return urls;
	}
	
	private void crawByKeyword(final String buzz,String tags,HtmlPageCrawler htmlPageCrawler) {
		CmsKeyValue cmsKeyValue = new CmsKeyValue(Constants.KEY_VALUE_GROUP_SEARCH_BUZZ,buzz);
		if(cmsKeyValueService.exist(cmsKeyValue)) {
			logger.info("ignore search,already_search_buzz:"+buzz);
			return;
		}
		
		cmsKeyValueService.create(cmsKeyValue);
		
		final String finalSearchKeyword = URLEncoderUtil.encode(buzz + " " + DateConvertUtils.format(new Date(), "yyyy年MM月"));
		String searchUrl = "https://www.google.com.hk/search?num=10&hl=zh-CN&safe=strict&tbs=qdr:d&q="+finalSearchKeyword;
		SinglePageCrawler crawler = newGoogleSinglePageCrawler(tags,"zh-CN",htmlPageCrawler,searchUrl);
		crawler.execute();
	}

	private SinglePageCrawler newGoogleSinglePageCrawler(String tags,String sourceLang,HtmlPageCrawler htmlPageCrawler, String... searchUrl) {
		SinglePageCrawler crawler = new SinglePageCrawler();
		crawler.setUrlList(searchUrl);
		crawler.setSourceLang(sourceLang);
		crawler.setTags(tags);
		crawler.setExcludeUriRegexList(".*google.*",".*youtube.*",".*blogger.*");
		crawler.setHtmlPageCrawler(htmlPageCrawler);
		return crawler;
	}
	
	/**
	 * 合并过于短小的文章
	 */
	public synchronized void mergeSmallArticle() {
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
		return buildCmsContent(page,new GoogleTranslateTransformer());
	}
	
	public static interface Transformer {
		public String transform(String sourceLang,String content);
	}
	
	public static class GoogleTranslateTransformer implements Transformer {
		public String transform(String sourceLang,String content) {
			String transformedContent = null;
			if("zh-cn".equalsIgnoreCase(sourceLang) || "zh-tw".equalsIgnoreCase(sourceLang)) {
				transformedContent = GoogleTranslateUtil.reverseTwoWayTranslate(content,"zh-CN","en");
			}else {
				transformedContent = GoogleTranslateUtil.translate(content,sourceLang,"zh-CN");
			}
			return transformedContent;
		}
	}
	
	public static class NaipanTransformer implements Transformer {
		public String transform(String sourceLang,String content) {
			String transformedContent = null;
			if("zh-cn".equalsIgnoreCase(sourceLang) || "zh-tw".equalsIgnoreCase(sourceLang)) {
				transformedContent = NaipanArticleGeneratorUtil.transformArticle(content);
			}else {
				transformedContent = GoogleTranslateUtil.translate(content,sourceLang,"zh-CN");
			}
			return transformedContent;
		}
	}
	
	private CmsContent buildCmsContent(HtmlPage page,Transformer transformer) {
		if(hasFilterKeyword(page.getTitle(),page.getContent())) {
			return null;
		}
		
		CmsContent c = new CmsContent();
		String content = transformer.transform(page.getSourceLang(),page.getContent());
		String title = transformer.transform(page.getSourceLang(),page.getTitle());
		
		c.setContent(HtmlFormatUtil.htmlBeauty(content));
		c.setTitle(title);
		c.setTags(page.getTags());
		
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
			throw new RuntimeException("百度已经存在该文章,cmsContent.title:"+c.getTitle()+" htmlPage.title:"+page.getTitle()+" Transformer:"+transformer.getClass()+" page.sourceLang:"+page.getSourceLang());
		}
	}
	
	
	static List<String> filterWords = Arrays.asList("\\u","http://","www.","代开","开发票","买发票","卖发票","销售发票","代開");
	static List<String> filterRegex = Arrays.asList("开.*发票","发票.*代开","发票.*开","假.*发票","開.*發票");
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
