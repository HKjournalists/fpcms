package com.fpcms.common.util;

import org.apache.shiro.util.StringUtils;

public class HtmlUtil {

	public static String getCharsetFromHtml(String html) {
		String charset = RegexUtil.findByRegexGroup(html, "(?s)<meta.*?charset\\s*=\\s*([\\w-]+)", 1);
		if(StringUtils.hasText(charset)) {
			return charset;
		}
		charset = RegexUtil.findByRegexGroup(html, "(?s)encoding=['\"]([\\w-]+)['\"]", 1);
		if(StringUtils.hasText(charset)) {
			return charset;
		}
		return null;
	}
	
}
