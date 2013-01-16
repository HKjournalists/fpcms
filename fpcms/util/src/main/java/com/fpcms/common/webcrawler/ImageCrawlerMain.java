package com.fpcms.common.webcrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ImageCrawlerMain {
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 2;

		CrawlConfig config = newCrawlConfig(crawlStorageFolder);
		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		CrawlController controller = new CrawlController(config, pageFetcher,newRobotstxtServer(pageFetcher));

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
//		controller.addSeed("http://www.aaafaipiao.com");
		controller.addSeed("http://www.22mm.cc");
		controller.addSeed("http://www.22mm.cc/mm/qingliang/ggiejhb_ljgijd.html");

		ImageCrawler.configure(new String[]{"http://www.22mm.cc","http://qlimg1.meimei22.com"}, crawlStorageFolder);
		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(ImageCrawler.class, numberOfCrawlers);
	}

	private static CrawlConfig newCrawlConfig(String crawlStorageFolder) {
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(30);
		config.setIncludeBinaryContentInCrawling(true);
		return config;
	}

	private static RobotstxtServer newRobotstxtServer(PageFetcher pageFetcher) {
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		robotstxtConfig.setEnabled(false);
		robotstxtConfig.setUserAgentName("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		return robotstxtServer;
	}
	
}