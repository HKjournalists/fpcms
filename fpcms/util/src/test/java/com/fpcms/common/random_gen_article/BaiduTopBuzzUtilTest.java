package com.fpcms.common.random_gen_article;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.random_gen_article.BaiduTopBuzzUtil;


public class BaiduTopBuzzUtilTest extends Assert{
	@Test
	public void test() {
		Set set = BaiduTopBuzzUtil.getBaiduBuzzs();
		assertFalse(set.isEmpty());
		assertTrue("set.size() > 40 false,size:"+set.size()+" set:"+set,set.size() > 40);
	}
}
