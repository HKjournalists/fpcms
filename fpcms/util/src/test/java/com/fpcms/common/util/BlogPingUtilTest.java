package com.fpcms.common.util;

import static org.junit.Assert.*;

import org.junit.Test;


public class BlogPingUtilTest {
	
	@Test
	public void test_baiduPing() {
		boolean result = BlogPingUtil.baiduPing("百度空间", "http://hi.baidu.com/baidu/", "http://www.cnblogs.com/fpqqchao/archive/2013/02/04/2892304.html", "http://hi.baidu.com/baidu/rss");
		assertTrue(result);
	}
	
}
