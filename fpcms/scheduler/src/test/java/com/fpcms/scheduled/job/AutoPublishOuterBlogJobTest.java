package com.fpcms.scheduled.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.duowan.common.util.page.Page;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.service.CmsDomainService;

public class AutoPublishOuterBlogJobTest extends Mockito{
	AutoPublishOuterBlogJob job = new AutoPublishOuterBlogJob();
	CmsDomainService cmsDomainService = mock(CmsDomainService.class);
	Page<CmsDomain> page = new Page<CmsDomain>();
	
	@Before
	public void setUp() {
		job.setCmsDomainService(cmsDomainService);
		when(cmsDomainService.findPage((CmsDomainQuery)any())).thenReturn(page);
		
		page.setItemList(Arrays.asList(new CmsDomain("aaafaipiao.com"),new CmsDomain("starkp.com")));
	}
	
	@Test
	public void test() {
		
		job.execute();
	}
	
	@Test
	public void test_postBlog() {
		List<HtmlPage> list = new ArrayList<HtmlPage>();
		list.add(new HtmlPage("test_title_AutoPublishOuterBlogJobTest",StringUtils.repeat("11111", 200)));
		list.add(new HtmlPage("test_title_AutoPublishOuterBlogJobTest",StringUtils.repeat("11111", 200)));
		job.postAllBlog(list);
	}
}
