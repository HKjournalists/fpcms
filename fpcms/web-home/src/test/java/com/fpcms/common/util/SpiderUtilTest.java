package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class SpiderUtilTest extends Assert{
	
	@Test
	public void test() {
		assertEquals("Googlebot",SpiderUtil.getSpiderName("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"));
		assertEquals("Baiduspider",SpiderUtil.getSpiderName("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)"));
		assertEquals("Sosospider",SpiderUtil.getSpiderName("Sosospider+(+http://help.soso.com/webspider.htm)"));
		assertEquals("SogouSpider",SpiderUtil.getSpiderName("Sogou web spider/4.0(+http://www.sogou.com/docs/help/webmasters.htm#07)"));
	}
}
