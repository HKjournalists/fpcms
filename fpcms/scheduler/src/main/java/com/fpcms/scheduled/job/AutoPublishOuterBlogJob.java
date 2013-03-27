package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.fpcms.common.util.HtmlFormatUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.ThreadUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
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
		posterList.add(new AccountBlogPosterDecorator(new ChinaUnixBlogPoster(),"blogtg123","abc123"));
		posterList.add(new AccountBlogPosterDecorator(new ChinaUnixBlogPoster(),"bbstg123","abc123"));
//		posterList.add(new AccountBlogPosterDecorator(new OschinaBlogPoster(),"fpqqchao@gmail.com","6367c48dd193d56ea7b0baad25b19455e529f5ee"));
		
		posterList.add(new MetaWeblogBlogPoster("http://sh292did.blog.163.com/","fpqqchao@gmail.com","asdf@1234"));
		posterList.add(new MetaWeblogBlogPoster("http://blog.sina.com.cn/u/3099457992","fpqqchao@gmail.com","asdf@1234"));
		posterList.add(new MetaWeblogBlogPoster("http://blog.sina.com.cn/u/3225400392","blogtg123@gmail.com","asdf@1234"));
		posterList.add(new MetaWeblogBlogPoster("http://blog.sina.com.cn/u/3225401060","bbstg123@gmail.com","asdf@1234"));
		posterList.add(new MetaWeblogBlogPoster("http://blogtg123.blog.com/","blogtg123@gmail.com","abc123"));
		
		MetaWeblogBlogPoster cto51 = new MetaWeblogBlogPoster("http://51ctoblog.blog.51cto.com","fpqqchao@gmail.com","abc123");
		cto51.setCategories("【创作类型:原创】","开发技术");
		posterList.add(cto51);
	}
	
	@Override
	public synchronized void execute() {
		final List<HtmlPage> pageList = cralwerForPageList();
		postAllBlog(pageList);
	}

	void postAllBlog(final List<HtmlPage> pageList) {
		
		RandomLinkPrecessor randomLinkPrecessor = new RandomLinkPrecessor();
		for(BlogPoster poster : posterList) {
			try {
				HtmlPage page = getRandomValidPage(pageList);
				if(page == null) {
					break;
				}
				String transformTitle = NaipanArticleGeneratorUtil.transformArticle(page.getTitle());
				String content = randomLinkPrecessor.execute(NaipanArticleGeneratorUtil.transformArticle(page.getContent()));
				
				Assert.notNull(content,"content must be not null");
				Assert.isTrue(content.length() > 300,"post blog content must great 300,title:"+transformTitle);
				
				Blog blog = new Blog(transformTitle,content);
				poster.postBlog(blog);
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
			if(StringUtils.length(StringUtils.remove(StringUtils.trim(page.getContent())," ")) < 300) {
				continue;
			}
			CmsKeyValue cmsKeyValue = newOuterBlogCmsKeyValue(page.getAnchor());
			if(cmsKeyValueService.exist(cmsKeyValue)) {
				continue;
			}
			cmsKeyValueService.create(cmsKeyValue);
			return page;
		}
	}

	private static CmsKeyValue newOuterBlogCmsKeyValue(Anchor anchor) {
		CmsKeyValue cmsKeyValue = new CmsKeyValue("outerBlog", anchor.getHref());
		return cmsKeyValue;
	}

	public class RandomLinkPrecessor {
		Set<String> useedLink = new HashSet();
		private String execute(String input) {
			Assert.hasText(input);
			Assert.isTrue(input.length() > 200);
			String transformArticle = NaipanArticleGeneratorUtil.transformArticle(input);
			StringBuilder content = new StringBuilder(transformArticle);
			content.insert(Math.min(content.length(),200), selectRandomDomain());
			content.append(selectRandomDomain());
			return "<pre>"+content.toString()+"</pre>"; //TODO 该段文本如果没有<pre>格式，会存在问题，
		}
		
		private String selectRandomDomain() {
			CmsDomain domain = null;
			for(int i = 0; i < 10; i++) {
				domain = cmsDomainService.randomSelectDomain();
				Assert.notNull(domain,"not found any random CmsDomain");
				String link =  "http://www."+domain.getDomain();
				if(useedLink.contains(link)) {
					continue;
				}
				useedLink.add(link);
				return " "+link+" ";
			}
			return domain.getYesterdayOuterLinked();
		}
	
		private String selectRandomSite() {
			List<CmsSite> siteList = cmsSiteService.findAll();
			CmsSite site = RandomUtil.randomSelect(siteList);
			Assert.notNull(site,"not found any random CmsDomain");
			String link =  site.getYesterdayOuterLinked();
			return link;
		}
	}
	
	private List<HtmlPage> cralwerForPageList() {
		SinglePageCrawler cralwer = new SinglePageCrawler();
		cralwer.setSourceLang("zh-CN");
		cralwer.setAcceptUrlRegexList("http://www.oschina.net/code/snippet_.*","http://\\w+.blog.51cto.com/\\d+/\\d+","http://blog.csdn.net/.*/article/details/\\d+");
		cralwer.setUrlList("http://www.oschina.net/code/list/1/java",
				"http://blog.csdn.net/code/index.html","http://blog.csdn.net/www/index.html",
				"http://blog.csdn.net/web/index.html","http://blog.csdn.net/database/index.html",
				"http://blog.51cto.com/original/","http://blog.51cto.com/original.php?cid=0&page=2");
		
		final List<HtmlPage> pageList = new ArrayList<HtmlPage>();
		cralwer.setHtmlPageCrawler(new HtmlPageCrawler() {
			@Override
			public void visit(HtmlPage page) {
				ThreadUtil.sleep(1000);
				pageList.add(page);
			}
			
			@Override
			public boolean shoudVisitPage(Anchor a) {
				CmsKeyValue keyValue = newOuterBlogCmsKeyValue(a);
				if(cmsKeyValueService.exist(keyValue)) {
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
