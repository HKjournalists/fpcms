package com.fpcms.service.article_crawl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.duowan.common.util.page.Paginator;
import com.fpcms.model.CmsContent;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsKeyValueService;
import com.fpcms.service.article_crawl.ArticleCrawlService.GoogleTranslateTransformer;

public class ArticleCrawlServiceTest extends Mockito{
	private ArticleCrawlService articleCrawlService = new ArticleCrawlService(); ;
	private CmsContentService cmsContentService = mock(CmsContentService.class);
	private CmsKeyValueService cmsKeyValueService = mock(CmsKeyValueService.class);
	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-crawler.xml");
		articleCrawlService.setApplicationContext(applicationContext);
		articleCrawlService.setCmsContentService(cmsContentService);
		articleCrawlService.setCmsContentService(cmsContentService);
		articleCrawlService.setCmsKeyValueService(cmsKeyValueService);
		
		articleCrawlService.afterPropertiesSet();
	}
	
	@Test
	public void test_hasFilterKeyword() {
		assertTrue(ArticleCrawlService.hasFilterKeyword("开贵州发票"));
		assertTrue(ArticleCrawlService.hasFilterKeyword("代开贵州发票"));
	}
	
	@Test
	public void test_return_countBySourceUrl_1() {
		when(cmsContentService.countBySourceUrl((Date)any(), (Date)any(), (String)any())).thenReturn(1);
		articleCrawlService.crawlAllSite();
		
		verify(cmsContentService,atLeastOnce()).countBySourceUrl((Date)any(), (Date)any(), (String)any());
		verify(cmsContentService,never()).create((CmsContent)any());
	}
	
	@Test
	public void test_return_countBySourceUrl_0() {
		when(cmsContentService.countBySourceUrl((Date)any(), (Date)any(), (String)any())).thenReturn(0);
		articleCrawlService.crawlAllSite();
		
		verify(cmsContentService,atLeastOnce()).countBySourceUrl((Date)any(), (Date)any(), (String)any());
		verify(cmsContentService,atLeastOnce()).create((CmsContent)any());
	}

	@Test
	public void getShoudVisitAnchorList() {
		List<String> list = articleCrawlService.getInvalidUrlList();
		removeIgnoreSite(list);
		
		assertTrue(list.toString(),list.isEmpty());
	}
	
	@Test
	public void mergeSmallArticle() {
		List<CmsContent> list = Arrays.asList(newCmsContent(300),newCmsContent(1400),newCmsContent(800),newCmsContent(1000),newCmsContent(300));
		Page<CmsContent> page = new Page<CmsContent>(list,new Paginator(1, 100, 1000));
		when(cmsContentService.findPage((PageQuery)any(), (String)any(), (String)any(), (DateRange)any())).thenReturn(page);
		articleCrawlService.mergeSmallArticle();
	}
	
	@Test
	public void crawlFapiaoKeyword() {
		List<CmsContent> list = articleCrawlService.crawlKeyword("发票");
		assertAndPrint(list);
		
	}

	@Test
	public void crawl_by_java_replace_invoice() {
		String[] keywords = {"java","phone","iphone","cameras","printer","notebook","refrigerator","mobile","car","game","novel","cartoon","movie","music","animation","suv","food","pet","travel","stock","money","fund"};
		for(String keyword : keywords) {
			List<CmsContent> list = articleCrawlService.crawlByKeyword("en_fapiao,"+keyword,"en",keyword, "invoice", "en");
			assertAndPrint(list);
		}
	}

	@Test
	public void replaceWithCaseInsentisive() {
		assertEquals("Jitterz invoice咖啡厅入室盗窃的风行照相机",articleCrawlService.replaceWithCaseInsentisive("Jitterz Java咖啡厅入室盗窃的风行照相机", "java", "invoice"));
	}
	
	private void assertAndPrint(List<CmsContent> list) {
		assertFalse(list.isEmpty());
		for(CmsContent c : list) {
			System.out.println("----------------"+c.getTitle()+"---------------------");
			System.out.println(c.getContent());
		}
	}

	private CmsContent newCmsContent(int i) {
		String title = ""+RandomUtils.nextInt();
		String content = RandomStringUtils.randomAlphanumeric(i);
		CmsContent c = new CmsContent();
		c.setTitle(title);
		c.setContent(content);
		c.setId((long)i);
		return c;
	}

	private void removeIgnoreSite(List<String> list) {
		for(ListIterator<String> it = list.listIterator(); it.hasNext();) {
			String url = it.next();
			if(url.contains("rfi.fr")) {
				it.remove();
			}
		}
	}
	
	@Test
	public void test_GoogleTranslateTransformer() {
		GoogleTranslateTransformer t = new GoogleTranslateTransformer();
		String str = t.transform("en", "Samsung Galaxy S4 Twice As Fast As iPhone 5, Even Galaxy S3 Proves Faster Than Apple’s Latest [Report]");
		assertEquals("三星Galaxy S4快两倍， iPhone 5 ，即使是银河S3阅兵超苹果[举报]",str);
	}
	
	
	
}
