package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.duowan.common.util.page.Page;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.blog_post.BlogPoster;
import com.fpcms.common.blog_post.impl.MetaWeblogBlogPoster;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.service.CmsDomainService;
import com.fpcms.service.CmsKeyValueService;
import com.fpcms.service.CmsSiteService;

public class AutoPublishOuterBlogJobTest extends Mockito{
	AutoPublishOuterBlogJob job = new AutoPublishOuterBlogJob();
	CmsDomainService cmsDomainService = mock(CmsDomainService.class);
	CmsKeyValueService cmsKeyValueService = mock(CmsKeyValueService.class);
	CmsSiteService cmsSiteService = mock(CmsSiteService.class);
	Page<CmsDomain> page = new Page<CmsDomain>();
	
	@Before
	public void setUp() {
		job.setCmsDomainService(cmsDomainService);
		job.setCmsKeyValueService(cmsKeyValueService);
		job.setCmsSiteService(cmsSiteService);
		
		when(cmsDomainService.findPage((CmsDomainQuery)any())).thenReturn(page);
		page.setItemList(Arrays.asList(new CmsDomain("aaafaipiao.com"),new CmsDomain("starkp.com")));
	}
	
	@Test
	public void test() {
		
		job.execute();
	}
	
	@Test
	public void test_postBlog() {
		when(cmsDomainService.randomSelectDomain()).thenReturn(new CmsDomain("www.aaafaipiao.com"));
		List<HtmlPage> list = new ArrayList<HtmlPage>();
		for(int i = 0; i < 20; i++) {
			HtmlPage e = new HtmlPage("test_title_AutoPublishOuterBlogJobTest",StringUtils.repeat("11111", 200));
			Anchor anchor = new Anchor();
			anchor.setHref("http://www.163.com");
			e.setAnchor(anchor);
			list.add(e);
		}
		job.postAllBlog(list);
	}
	
	@Test
	public void test_postBlog_blog_com() {
		MetaWeblogBlogPoster blogPoster = new MetaWeblogBlogPoster("http://blogtg123.blog.com/","blogtg123@gmail.com","abc123");
		Blog blog = newBlog();
		blogPoster.postBlog(blog);
	}

	private Blog newBlog() {
		Blog blog = new Blog("test_title","test_content");
		blog.setCategories("1");
		return blog;
	}
	
	@Test
	public void test_post_meta() {
		List<BlogPoster> posterList = new ArrayList<BlogPoster>();
//		posterList.add(new MetaWeblogBlogPoster("http://sh292did.blog.163.com/","fpqqchao@gmail.com","asdf@1234"));
//		posterList.add(new MetaWeblogBlogPoster("http://blog.sina.com.cn/u/3099457992","fpqqchao@gmail.com","asdf@1234"));
//		posterList.add(new MetaWeblogBlogPoster("http://blogtg123.blog.com/","blogtg123@gmail.com","abc123"));
		posterList.add(new MetaWeblogBlogPoster("http://bbstg123.blogbus.com/","bbstg123","abc123"));
		
		for(BlogPoster poster : posterList) {
			poster.postBlog(newBlog());
		}
	}
}
