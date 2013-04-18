package com.fpcms.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(ArticleCrawlJob.class);
	
	private ArticleCrawlService articleCrawlService;
	public ArticleCrawlJob() {
		super("1 1 5 * * *");
	}

	public void setArticleCrawlService(
			ArticleCrawlService articleCrawlService) {
		this.articleCrawlService = articleCrawlService;
	}
	
	@Override
	public synchronized void executeInternal() {
//		articleCrawlService.crawlAllBuzzKeyword();
		String[] keywords = {"java","phone","iphone","company","cameras","printer","notebook","refrigerator","mobile","car","game","novel","cartoon","movie","music","animation","suv","food","pet","travel","stock","money","fund"};
		for(String keyword : keywords) {
			try {
				articleCrawlService.crawlByKeyword("en_fapiao,"+keyword,"en",keyword, "invoice", "en");
			}catch(Exception e) {
				logger.error("error on crawlByKeyword:"+keyword,e);
			}
		}
		
		articleCrawlService.crawlKeyword("发票");
		articleCrawlService.crawlAllSite();
//		articleCrawlService.mergeSmallArticle();
	}

	@Override
	public String getJobRemark() {
		return "文章采集";
	}
	
}
