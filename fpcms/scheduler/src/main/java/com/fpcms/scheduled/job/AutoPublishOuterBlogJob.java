package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.common.blog_post.AccountBlogPosterDecorator;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.blog_post.BlogPoster;
import com.fpcms.common.blog_post.impl.ChinaUnixBlogPoster;
import com.fpcms.common.blog_post.impl.CnblogBlogPoster;
import com.fpcms.common.blog_post.impl.MetaWeblogBlogPoster;
import com.fpcms.common.blog_post.impl.OschinaBlogPoster;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.random_gen_article.NaipanArticleGeneratorUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.model.CmsDomain;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsDomainService;
import com.fpcms.service.CmsKeyValueService;
import com.fpcms.service.CmsSiteService;

/**
 * 自动针对外网发布外链BLOG
 * 
 * @author badqiu
 *
 */
@Service
public class AutoPublishOuterBlogJob extends BaseCronJob{
	private static Logger logger = LoggerFactory.getLogger(AutoPublishOuterBlogJob.class);
	
	private List<BlogPoster> posterList = new ArrayList<BlogPoster>();
	private CmsDomainService cmsDomainService;
	private CmsSiteService cmsSiteService;
	private CmsKeyValueService cmsKeyValueService;
	private Cache cache = CacheManager.createCache(AutoPublishOuterBlogJob.class,1000);
	
	public void setCmsDomainService(CmsDomainService cmsDomainService) {
		this.cmsDomainService = cmsDomainService;
	}
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}
	
	public void setCmsKeyValueService(CmsKeyValueService cmsKeyValueService) {
		this.cmsKeyValueService = cmsKeyValueService;
	}

	public AutoPublishOuterBlogJob() {
		super("1 30 2,5 * * *");
		posterList.add(new AccountBlogPosterDecorator(new CnblogBlogPoster(),"fpqqchao","abc123"));
		posterList.add(new AccountBlogPosterDecorator(new ChinaUnixBlogPoster(),"fpqqchao","abc123"));
		posterList.add(new AccountBlogPosterDecorator(new OschinaBlogPoster(),"fpqqchao@gmail.com","6367c48dd193d56ea7b0baad25b19455e529f5ee"));
		posterList.add(new MetaWeblogBlogPoster("http://sh292did.blog.163.com/","fpqqchao@gmail.com","asdf@1234"));
		posterList.add(new MetaWeblogBlogPoster("http://blog.sina.com.cn/u/3099457992","fpqqchao@gmail.com","asdf@1234"));
	}
	
	@Override
	public synchronized void execute() {
		final List<HtmlPage> pageList = cralwerForPageList();
		postAllBlog(pageList);
	}

	void postAllBlog(final List<HtmlPage> pageList) {
		for(BlogPoster poster : posterList) {
			try {
				HtmlPage page = getRandomValidPage(pageList);
				if(page == null) {
					break;
				}
				poster.postBlog(new Blog(NaipanArticleGeneratorUtil.transformArticle(page.getTitle()),addRandomLink(page)));
			}catch(RuntimeException e) {
				logger.error("postBlog error",e);
			}
		}
	}
	
	HtmlPage getRandomValidPage(final List<HtmlPage> pageList) {
		while(true) {
			HtmlPage page = RandomUtil.randomRemove(pageList);
			if(page == null) {
				return null;
			}
			if(StringUtils.length(page.getContent()) < 300) {
				continue;
			}
			CmsKeyValue cmsKeyValue = new CmsKeyValue("outerBlog", page.getAnchor().getHref());
			if(cmsKeyValueService.exist(cmsKeyValue)) {
				continue;
			}
			cmsKeyValueService.create(cmsKeyValue);
			return page;
		}
	}

	private String addRandomLink(HtmlPage page) {
		Assert.hasText(page.getContent());
		Assert.isTrue(page.getContent().length() > 200);
		StringBuilder content = new StringBuilder(page.getContent());
		content.insert(200, selectRandomDomain());
		content.append(selectRandomSite());
		String transformArticle = NaipanArticleGeneratorUtil.transformArticle(content.toString());
		return transformArticle.contains("\n") ? "<pre>"+transformArticle+"</pre>" : transformArticle;
	}
	
	private String selectRandomDomain() {
		List<CmsDomain> domainList = cmsDomainService.findAll();
		CmsDomain domain = RandomUtil.randomSelect(domainList);
		Assert.notNull(domain,"not found any random CmsDomain");
		String link =  domain.getYesterdayOuterLinked();
		return String.format(" <a href='%s'>%s</a>; ",link,link);
	}

	private String selectRandomSite() {
		List<CmsSite> siteList = cmsSiteService.findAll();
		CmsSite site = RandomUtil.randomSelect(siteList);
		Assert.notNull(site,"not found any random CmsDomain");
		String link =  site.getYesterdayOuterLinked();
		return String.format(" <a href='%s'>%s</a>; ",link,link);
	}
	
	private List<HtmlPage> cralwerForPageList() {
		SinglePageCrawler cralwer = new SinglePageCrawler();
		cralwer.setSourceLang("zh-CN");
		cralwer.setAcceptUrlRegexList("http://www.oschina.net/code/snippet_.*");
		cralwer.setUrlList("http://www.oschina.net/code/list/1/java");
		
		final List<HtmlPage> pageList = new ArrayList<HtmlPage>();
		cralwer.setHtmlPageCrawler(new HtmlPageCrawler() {
			@Override
			public void visit(HtmlPage page) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				pageList.add(page);
			}
			
			@Override
			public boolean shoudVisitPage(Anchor a) {
				long count = cache.incr(a.getHref(), 1);
				if(count > 1)  {
					return false;
				}
				return true;
			}
		});
		
		cralwer.execute();
		return pageList;
	}

	@Override
	public String getJobRemark() {
		return "发送BLOG至其它网站";
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Assert.notNull(cmsKeyValueService,"cmsKeyValueService must be not null");
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		Assert.notNull(cmsDomainService,"cmsDomainService must be not null");
	}
}
