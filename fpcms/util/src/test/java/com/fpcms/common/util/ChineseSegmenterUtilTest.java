package com.fpcms.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.duowan.common.util.Profiler;
import com.fpcms.common.util.ChineseSegmenterUtil.TokenCount;


public class ChineseSegmenterUtilTest extends Assert {
	@Test
	public void test_segmenteForTokenCount() throws IOException {
		String string = "中国人民银行是一家好国家，中国太棒了,一,一,一";
		Map map = ChineseSegmenterUtil.segmenteForTokenCount(new StringReader(string),false);
		System.out.println(map);
	}
	
	@Test
	public void test_toTokenCountList() throws IOException {
		String string = "中国人民银行是一家好国家，中国太棒了,一,一,一";
		Map map = ChineseSegmenterUtil.segmenteForTokenCount(new StringReader(string),false);
		List<TokenCount> tokenCountList = ChineseSegmenterUtil.toSortedTokenCountList(map);
		System.out.println("tokenCountList:"+tokenCountList);
		assertEquals(tokenCountList.get(0).getToken(),"一");
		assertEquals(tokenCountList.get(1).getToken(),"中国");
	}
	
	
	@Test
	public void test_perf() throws IOException {
		String string = "中国人民银行是一家好国家，中国太棒了,一,一,一";
		int loopCount = 10000;
		Profiler.start("ChineseSegmenterUtil.test_perf",loopCount);
		for(int i = 0; i < loopCount; i++) {
			Map map = ChineseSegmenterUtil.segmenteForTokenCount(new StringReader(string),false);
		}
		Profiler.release();
		System.out.println(Profiler.dump());
	}
}
