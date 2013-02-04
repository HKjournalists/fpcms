package com.fpcms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duowan.common.web.scope.Flash;
import com.fpcms.service.article_crawl.ArticleCrawlService;
@Controller
@RequestMapping("/admin/articlecrawl")
public class ArticleCrawlController {
	
	private ArticleCrawlService articleCrawlService;

	public void setArticleCrawlService(ArticleCrawlService articleCrawlService) {
		this.articleCrawlService = articleCrawlService;
	}
	
	@RequestMapping
	public String gen() {
		articleCrawlService.execute();
		Flash.current().success("采集文章成功");
		return "/commons/messages";
	}
}
