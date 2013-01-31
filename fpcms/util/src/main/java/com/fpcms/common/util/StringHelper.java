package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringHelper {

	public static List<String> removeEmptyLines(List<String> lines) {
		if(lines == null) return null;
		
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < lines.size(); i++) {
			if(StringUtils.isNotBlank(lines.get(i))) {
				result.add(lines.get(i));
			}
		}
		return result;
	}
	
	public static String getMetaDescription(String content) { //FIXME 生成的东西太多英文字符
		if(StringUtils.isBlank(content)) {
			return content;
		}
		String replaced = content.replaceAll("</\\w+>", "").replaceAll("&\\w+;", "").replaceAll("<.*?>", "").replaceAll("\\s", "");
		return replaced.substring(0,Math.min(replaced.length(),200));
//		String[] array = org.springframeworenizeToStringArray(content, KeywordUtil.DELIMITERS);
//		return StringUtils.join(array,",").substring(0,200);
	}
	
}
