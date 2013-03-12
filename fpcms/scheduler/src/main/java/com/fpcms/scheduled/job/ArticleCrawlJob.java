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
	private ArticleCrawlService articleCrawlService;
	public ArticleCrawlJob() {
		super("1 1 1/2 * * *");
	}

	public void setArticleCrawlService(
			ArticleCrawlService articleCrawlService) {
		this.articleCrawlService = articleCrawlService;
	}
	
	@Override
	public synchronized void execute() {
//		articleCrawlService.crawlAllBuzzKeyword();
		articleCrawlService.crawlByKeyword("en_fapiao","en","java", "invoice", "en");
		articleCrawlService.crawlKeyword("发票");
		articleCrawlService.crawlAllSite();
		articleCrawlService.mergeSmallArticle();
	}

	@Override
	public String getJobRemark() {
		return "文章采集";
	}
	
}
