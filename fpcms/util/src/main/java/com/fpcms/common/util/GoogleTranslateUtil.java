package com.fpcms.common.util;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GoogleTranslateUtil {
	 static ObjectMapper mapper = new ObjectMapper();  
	public static String fromChinese2English(String words) {
		String text = NetUtil.httpGet("http://translate.google.cn/translate_a/t","client=t&text="+words+"&hl=zh-CN&sl=zh-CN&tl=en");
		
		try {
			ArrayList array = mapper.readValue(text, ArrayList.class);
			return array.toString();
		} catch (JsonParseException e) {
			throw new RuntimeException("parse json error,context:"+text,e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("parse json error,context:"+text,e);
		} catch (IOException e) {
			throw new RuntimeException("parse json error,context:"+text,e);
		}
	}
	
	public static String fromEnglish2Chinese(String words) {
		return "";
	}
	
}
