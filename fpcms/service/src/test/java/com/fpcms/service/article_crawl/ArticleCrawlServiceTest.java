package com.fpcms.service.article_crawl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})  
public class ArticleCrawlServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private ArticleCrawlService articleCrawlService;
	

	@Test
	public void test() {
		articleCrawlService.execute();
	}
	
}
