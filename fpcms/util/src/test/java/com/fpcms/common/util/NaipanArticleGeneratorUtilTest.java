package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class NaipanArticleGeneratorUtilTest extends Assert{

	@Test
	public void test() {
		String content = NaipanArticleGeneratorUtil.transformArticle("新版奶盘seo伪原创采用java技术开发");
		System.out.println(content);
		assertEquals("新版奶盘搜索引擎优化伪自创选用java技术开发",content);
	}
	
}
