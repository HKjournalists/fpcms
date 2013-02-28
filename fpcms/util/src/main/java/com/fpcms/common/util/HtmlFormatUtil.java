package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;

public class HtmlFormatUtil {

	
	public static String htmlBeauty(String html) {
		if(StringUtils.isBlank(html)) {
			return html;
		}
		
		StringBuilder sb = new StringBuilder(html);
		int start = 0;
		sb.insert(0, "<p>");
		while(start < sb.length()) {
			start += 120;
			int index = indexOf(sb,start,"。",".","？","?","！","!");
			if(index == -1) {
				break;
			}
			sb.insert(index + 1, "</p>\n<p>");
		}
		sb.append("</p>");
		return sb.toString();
	}
	
	public static int indexOf(StringBuilder str,int fromIndex,String... chars) {
		for(String ch : chars) {
			int index = str.indexOf(ch,fromIndex);
			if(index >= 0) {
				return index;
			}
		}
		return -1;
	}
	
}
