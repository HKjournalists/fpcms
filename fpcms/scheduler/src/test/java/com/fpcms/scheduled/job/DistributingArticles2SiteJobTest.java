package com.fpcms.scheduled.job;

import org.junit.Assert;
import org.junit.Test;


public class DistributingArticles2SiteJobTest extends Assert{
	
	@Test
	public void test() {

	}
	
	@Test
	public void test_processWithSiteKeyword() {
		String text = DistributingArticles2SiteJob.processWithSiteKeyword("中国人民的好发票", "发票", "唐山代开发票");
		assertEquals("中国人民的好唐山代开发票",text);
	}
}
