package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.duowan.common.util.ArrayUtils;
import com.duowan.common.web.util.HttpUtils;

public class GoogleTranslateUtil {
	static Logger logger = LoggerFactory.getLogger(GoogleTranslateUtil.class);
	
	public static String fromChinese2English(String words) {
		logger.info("fromChinese2English,input:"+words);
		
		//完整参数 "client=t&hl=zh-CN&ie=UTF-8&multires=1&oe=UTF-8&sc=1&ssel=0&sl=zh-CN&tl=en"
		String output = translate(words,"zh-CN","en");
		logger.info("fromChinese2English,output:"+output);
		return output;
	}
	
	/**
	 * 
	 * @param words	需要翻译的文本
	 * @param sourceLang	源语言
	 * @param targetLang	目标语言
	 * @return
	 */
	public static String translate(String words,String sourceLang,String targetLang) {
		if(!StringUtils.hasText(words)) {
			return null;
		}
		
		Map params = new HashMap();
		params.put("text", words);
		params.put("sl", sourceLang);
//		params.put("hl", sourceLang); //该参数暂时无用
		params.put("tl", targetLang);
		params.putAll(HttpUtils.parseQueryString("client=t"));
		String text = NetUtil.httpPost("http://translate.google.cn/translate_a/t",params);
		
		if(words.length() < 20) {
			String seperator = "\",\"";
			int indexOfEnglish = text.indexOf(seperator);
			int indexOfChinese = text.indexOf(seperator, indexOfEnglish+1);
			String inputChinese = text.substring(indexOfEnglish+seperator.length(),indexOfChinese);
			String targetEnglish = text.substring(4,indexOfEnglish);
			return targetEnglish;
		}
		
		String replacedText = StringUtils.replace(text, "\\\"", "");
		Pattern pattern = Pattern.compile("(?msi)\".*?\"");
		Matcher m = pattern.matcher(replacedText);
		ArrayList result = new ArrayList();
		while(m.find()) {
			String str = replacedText.substring(m.start(),m.end());
			result.add(str);
		}
		List<Map<String,String>> mapRows = new ArrayList<Map<String,String>>();
		List<List<String>> rows = CollectionHelper.toMultiRows(result, 4);
		for(int i = 0; i < rows.size(); i++) {
			List<String> cols = rows.get(i);
			Map<String,String> map = ArrayUtils.toMap(cols.toArray(), "target","source","unuse","pinyin");
			String target = map.get("target").trim();
//			if(target.equalsIgnoreCase("\"zh-CN\"") || target.equalsIgnoreCase("\"en\"")) {
//				break;
//			}
			if(target.equals("\""+sourceLang+"\"")) {
				break;
			}
			mapRows.add(map);
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mapRows.size(); i++) {
			Map<String,String> row = mapRows.get(i);
			String target = row.get("target");
			target = target.substring(1,target.length() - 1);
			
			sb.append(target);
			if(i != mapRows.size() - 1) {
				sb.append(KeywordUtil.getSymbol(target));
			}
		}
		return sb.toString();
		
	}
	
	public static String fromEnglish2Chinese(String words) {
		return translate(words,"en","zh-CN");
	}
	
}
