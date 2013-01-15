package com.fpcms.common.webcrawler;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar <lastname at gmail dot com>
 */

/*
 * This class shows how you can crawl images on the web and store them in a
 * folder. This is just for demonstration purposes and doesn't scale for large
 * number of images. For crawling millions of images you would need to store
 * downloaded images in a hierarchy of folders
 */
public class ImageCrawler extends WebCrawler {

	private static final Pattern filters = Pattern
			.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	private static final Pattern imgPatterns = Pattern
			.compile(".*(\\.(gif|jpe?g|png?))$");

	private static File storageFolder;
	private static String[] crawlDomains;
	private static Logger logger = LoggerFactory.getLogger(ImageCrawler.class);
	private static PrintWriter storedImagesUrl = new PrintWriter(getF‎ileWriter(),true);

	private static FileWriter getF‎ileWriter()  {
		try {
			return new FileWriter("/tmp/storedImagesUrl.txt",true);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void configure(String[] crawlDomains, String storageFolderName) {
		System.out.println("ImageCrawler.configure,crawlDomains:"+Arrays.toString(crawlDomains));
		ImageCrawler.crawlDomains = crawlDomains;

		storageFolder = new File(storageFolderName);
		if (!storageFolder.exists()) {
			storageFolder.mkdirs();
		}
	}

	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		if (filters.matcher(href).matches()) {
			return false;
		}

		if (imgPatterns.matcher(href).matches()) {
			return true;
		}
		for (String domain : crawlDomains) {
			if (href.startsWith(domain)) {
//				logger.debug("shouldVisit() url:{}",url);
				return true;
			}
		}
		return false;
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
//		logger.debug("visit(Page) url:{}",url);
		// We are only interested in processing images
		if (!(page.getParseData() instanceof BinaryParseData)) {
			return;
		}

		if (!imgPatterns.matcher(url).matches()) {
			return;
		}

		// Not interested in very small images
		if (page.getContentData().length < 45 * 1024) {
			return;
		}
	
		// get a unique name for storing this image
		String extension = url.substring(url.lastIndexOf("."));
		try {
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(page.getContentData()));
			int width = image.getWidth();
			int height = image.getHeight();
			if(width > 500 || height > 500) {
				String hashedName = new URL(url).getFile() + extension;
				// store image
				// IO.writeBytesToFile(page.getContentData(),
				// storageFolder.getAbsolutePath() + "/" + hashedName);
				System.out.println("Stored: " + url+" width:"+width+" height:"+height);
				storedImagesUrl.println(url);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}