package com.fpcms.common.random_gen_article;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.duowan.common.util.Profiler;
import com.fpcms.common.random_gen_article.BaiduTopBuzzUtil;


public class BaiduTopBuzzUtilTest extends Assert{
	@Test
	public void test() {
		Set set = BaiduTopBuzzUtil.getBaiduBuzzs();
		assertFalse(set.isEmpty());
		assertTrue("set.size() > 40 false,size:"+set.size()+" set:"+set,set.size() > 40);
		
		for(int i =0; i< 10; i++) {
			Profiler.start();
			BaiduTopBuzzUtil.getBaiduBuzzs();
			Profiler.release();
		}
		long endCostTime = Profiler.getStep().getDuration();
		assertTrue("cost:"+endCostTime,endCostTime < 100);
		System.out.println("baiduBuzzs, site:"+set.size()+" set:" + set);
	}
}
