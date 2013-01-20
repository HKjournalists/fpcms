package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class GoogleTranslateUtilTest extends Assert {

	@Test
	public void test_fromChinese2English() {
		String str = GoogleTranslateUtil.fromChinese2English("中国");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"China");
		
		str = GoogleTranslateUtil.fromChinese2English("中国人民银行");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"The People's Bank of China");
		
		str = GoogleTranslateUtil.fromChinese2English("中国人民中国人民中国人民中国人民,好发票好发票好发票好发票好发票,929282,浙江浙江浙江浙江浙江");
		assertEquals(str,"Chinese people , Chinese people , Chinese people , Chinese people , good good invoice invoice invoice invoice invoice , 929282 , Zhejiang, Zhejiang and Zhejiang Zhejiang Zhejiang");
	}
	
	@Test
	public void test_fromEnglish2Chinese() {
		String str = GoogleTranslateUtil.fromEnglish2Chinese("China");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"中国");
	}
	
}
