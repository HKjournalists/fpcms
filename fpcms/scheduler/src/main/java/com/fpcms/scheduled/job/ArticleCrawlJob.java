package com.fpcms.scheduled.job;

import org.springframework.stereotype.Service;

import com.fpcms.service.article_crawl.ArticleCrawlService;

/**
 * 定时采集文章的Job
 * 
 * @author badqiu
 *
 */
@Service
public class ArticleCrawlJob extends BaseCronJob{
	private static ArticleCrawlService articleCrawlService;
	public ArticleCrawlJob() {
		super("1 1 10 * * *");
	}

	@Override
	protected void execute() {
		articleCrawlService.crawlAllSite();
	}

}
