package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.duowan.common.util.DateConvertUtils;

public class StringHelper {
	
	/**
	 * 得到昨天文章的外链
	 * @param site
	 * @return
	 */
	public static String getYesterdayOuterLinked(String site) {
		return getOuterLinked(site,DateUtils.addDays(new Date(),-1));
	}
	
	public static String getOuterLinked(String site,Date date) {
		return "http://"+site+"/linked/"+DateConvertUtils.format(date, "yyyyMMdd")+".do";
	}
	
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
	
	public static void removeRegexMatch(List<String> list, String regex) {
		for(ListIterator<String> it = list.listIterator();it.hasNext();) {
			String keyword = it.next();
			if(keyword.matches(regex)) {
				it.remove();
			}
		}
	}
	
}
