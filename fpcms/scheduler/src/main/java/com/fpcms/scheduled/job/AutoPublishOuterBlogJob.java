package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duowan.common.util.page.Page;
import com.fpcms.common.blog_post.AccountBlogPosterDecorator;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.blog_post.BlogPoster;
import com.fpcms.common.blog_post.impl.ChinaUnixBlogPoster;
import com.fpcms.common.blog_post.impl.CnblogBlogPoster;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.random_gen_article.NaipanArticleGeneratorUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPageCrawler;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.service.CmsDomainService;

/**
 * 自动针对外网发布外链BLOG
 * 
 * @author badqiu
 *
 */
@Service
public class AutoPublishOuterBlogJob extends BaseCronJob{
	private List<BlogPoster> posterList = new ArrayList<BlogPoster>();
	private CmsDomainService cmsDomainService;
	private Cache cache = CacheManager.createCache(AutoPublishOuterBlogJob.class,1000);
	
	public void setCmsDomainService(CmsDomainService cmsDomainService) {
		this.cmsDomainService = cmsDomainService;
	}

	public AutoPublishOuterBlogJob() {
		super("1 30 2,5 * * *");
		posterList.add(new AccountBlogPosterDecorator(new CnblogBlogPoster(),"fpqqchao","abc123"));
		posterList.add(new AccountBlogPosterDecorator(new ChinaUnixBlogPoster(),"fpqqchao","abc123"));
	}
	
	@Override
	public void execute() {
		final List<HtmlPage> pageList = cralwerForPageList();
		postAllBlog(pageList);
	}

	void postAllBlog(final List<HtmlPage> pageList) {
		for(BlogPoster poster : posterList) {
			HtmlPage page = RandomUtil.randomRemove(pageList);
			if(page == null) {
				break;
			}
			if(StringUtils.length(page.getContent()) < 300) {
				continue;
			}
			
			poster.postBlog(new Blog(NaipanArticleGeneratorUtil.transformArticle(page.getTitle()),addRandomLink(page)));
		}
	}

	private String addRandomLink(HtmlPage page) {
		Assert.hasText(page.getContent());
		Assert.isTrue(page.getContent().length() > 200);
		StringBuilder content = new StringBuilder(page.getContent());
		content.insert(200, selectRandomSite());
		content.append(selectRandomSite());
		return "<pre>"+NaipanArticleGeneratorUtil.transformArticle(content.toString())+"</pre>";
	}
	
	private String selectRandomSite() {
		CmsDomainQuery query = new CmsDomainQuery();
		query.setPageSize(Integer.MAX_VALUE);
		Page<CmsDomain> page = cmsDomainService.findPage(query);
		CmsDomain domain = RandomUtil.randomSelect(page.getItemList());
		Assert.notNull(domain,"not found any random CmsDomain");
		String link =  domain.getYesterdayOuterLinked();
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
}
