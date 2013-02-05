package com.fpcms.scheduled.job;

import org.junit.Assert;
import org.junit.Test;


public class DistributingArticles2SiteJobTest extends Assert{
	
	@Test
	public void test() {
		assertTrue(DistributingArticles2SiteJob.isMainSite("aaa.com"));
		assertTrue(DistributingArticles2SiteJob.isMainSite("www.aaa.com"));
		assertFalse(DistributingArticles2SiteJob.isMainSite("sz.aaa.com"));
		assertFalse(DistributingArticles2SiteJob.isMainSite(null));
	}
}
