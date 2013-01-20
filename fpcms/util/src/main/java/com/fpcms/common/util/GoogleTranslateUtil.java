package com.fpcms.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duowan.common.web.util.HttpUtils;

public class GoogleTranslateUtil {
	static Logger logger = LoggerFactory.getLogger(GoogleTranslateUtil.class);
	
	public static String fromChinese2English(String words) {
		logger.info("fromChinese2English,input:"+words);
		String output = translate(words,"client=t&hl=zh-CN&ie=UTF-8&multires=1&oe=UTF-8&sc=1&ssel=0sl=zh-CN&tl=en");
		logger.info("fromChinese2English,output:"+output);
		return output;
	}
	
	private static String translate(String words,String translateArgs) {
		Map params = new HashMap();
		params.put("text", words);
		params.putAll(HttpUtils.parseQueryString(translateArgs));
		String text = NetUtil.httpPost("http://translate.google.cn/translate_a/t",params);
		
		System.out.println(text);
		String seperator = "\",\"";
		int indexOfEnglish = text.indexOf(seperator);
		int indexOfChinese = text.indexOf(seperator, indexOfEnglish+1);
		String inputChinese = text.substring(indexOfEnglish+seperator.length(),indexOfChinese);
		String targetEnglish = text.substring(4,indexOfEnglish);
		return targetEnglish;
	}
	
	public static String fromEnglish2Chinese(String words) {
		return translate(words,"client=t&text="+words+"&hl=en&sl=en&tl=zh-CN");
	}
	
}
