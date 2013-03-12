package com.fpcms.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class TextLangUtilTest extends Assert{

	@Test
	public void hasChinese() {
		assertFalse(TextLangUtil.hasChinese("abc123"));
		assertFalse(TextLangUtil.hasChinese("1234"));
		assertFalse(TextLangUtil.hasChinese("き"));
		assertTrue(TextLangUtil.hasChinese("中国123,是不是english"));
		
		List<String> list = Arrays.asList("a","a1","bbbb","c1");
		Collections.sort(list,new StringLengthComparator());
		assertEquals("[a, a1, c1, bbbb]",list.toString());
	}
	
	
	@Test
	public void chineseCountPercent() {
		assertEquals(TextLangUtil.chineseCountPercent("我是一个中国人,12"),70);
		assertEquals(TextLangUtil.chineseCountPercent(null),0);
		assertEquals(TextLangUtil.chineseCountPercent(" "),0);
		assertEquals(TextLangUtil.chineseCountPercent(" 中1"),33);
	}
	
}
