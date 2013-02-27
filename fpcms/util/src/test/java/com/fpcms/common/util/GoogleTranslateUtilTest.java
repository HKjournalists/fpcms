package com.fpcms.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;


public class GoogleTranslateUtilTest extends Assert {

	@Test
	public void test_fromChinese2English_BIG() throws Exception {
		File file = ResourceUtils.getFile("classpath:translate_test.txt");
		String input = FileUtils.readFileToString(file);
		String english = GoogleTranslateUtil.fromChinese2English(input);
		System.out.println("english:"+english);
		
		String chinese = GoogleTranslateUtil.fromEnglish2Chinese(english);
		System.out.println("chinese:"+chinese);
	}
	
	@Test
	public void test_fromChinese2English() {

		String str = GoogleTranslateUtil.fromChinese2English("中国人民银行");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"The People's Bank of China");
		
		str = GoogleTranslateUtil.fromChinese2English("中国人民中国人民中国人民中国人民,好发票好发票好发票好发票好发票,929282,浙江浙江浙江浙江浙江");
		assertEquals(str,"Chinese people , Chinese people , Chinese people , Chinese people , good good invoice invoice invoice invoice invoice , 929282 , Zhejiang, Zhejiang and Zhejiang Zhejiang Zhejiang");
		
		
		str = GoogleTranslateUtil.fromChinese2English("中国");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"China");
	}
	
	@Test
	public void test_fromEnglish2Chinese() {
		String str = GoogleTranslateUtil.fromEnglish2Chinese("China");
		System.out.println("fromChinese2English:"+str);
		assertEquals(str,"中国");
	}
	
	@Test
	public void test_autoTranslate() {
		String str = GoogleTranslateUtil.autoTranslate("China", "zh-CN");
		assertEquals(str,"中国");
		
		str = GoogleTranslateUtil.translate("朝日新聞デジタル：塗装会社で爆発、経営者が死亡　東京・文京区","ja", "zh-CN");
		assertEquals(str,"“朝日新闻”数字爆炸的涂装公司，管理层的死亡，东京都文京区，");
		
	}
	
	@Test
	public void test_autoTranslate_zh_tw() {
		String str = GoogleTranslateUtil.autoTranslate("財政部稅務入口網統一發票管理,", "zh-CN");
		assertEquals(str,"税务财政部发票统一的网络管理入口");
		
		str = GoogleTranslateUtil.translate("財政部稅務入口網統一發票管理","zh-TW", "zh-CN");
		assertEquals(str,"财政部税务入口网统一发票管理");
	}
	
	@Test
	public void test_autoTranslate_2() throws UnsupportedEncodingException {
		String s = "\u53ef\u4ee5\u544a\u8bc9\u6211\u003e";
		byte[] utfString = s.getBytes("UTF-8");
		System.out.println("1111:"+new String(utfString));
		String str = GoogleTranslateUtil.autoTranslate("<br>財政部稅務入口網統一發票管理</br>,", "zh-CN");
		String expected = new String(str.getBytes("UTF-8"));
		assertEquals(expected,"税务财政部发票统一的网络管理入口");
		
	}
	
}
