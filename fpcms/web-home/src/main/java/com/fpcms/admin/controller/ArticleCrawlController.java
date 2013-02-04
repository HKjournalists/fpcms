package com.fpcms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duowan.common.web.scope.Flash;
import com.fpcms.scheduled.job.DistributingArticles2SiteJob;
import com.fpcms.service.article_crawl.ArticleCrawlService;
@Controller
@RequestMapping("/admin/articlecrawl")
public class ArticleCrawlController {
	
	private ArticleCrawlService articleCrawlService;
	private DistributingArticles2SiteJob distributingArticles2SiteJob;
	
	public void setDistributingArticles2SiteJob(
			DistributingArticles2SiteJob distributingArticles2SiteJob) {
		this.distributingArticles2SiteJob = distributingArticles2SiteJob;
	}

	public void setArticleCrawlService(ArticleCrawlService articleCrawlService) {
		this.articleCrawlService = articleCrawlService;
	}
	
	@RequestMapping
	public String gen() {
		articleCrawlService.crawlAllSite();
		Flash.current().success("采集文章成功");
		return "/commons/messages";
	}
	
	@RequestMapping
	public String distributingArticles2Site() {
		distributingArticles2SiteJob.execute();
		Flash.current().success("为网站分发文章成功");
		return "/commons/messages";
	}
}
