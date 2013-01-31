package com.fpcms.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class SpiderUtil {

	public static String getSpiderName(String userAgent) {
		if(StringUtils.isBlank(userAgent)) {
			return null;
		}
		if(userAgent.contains("Sogou web spider")) {
			return "SogouSpider";
		}
		String bot = RegexUtil.findByRegexGroup(userAgent, "(?i).*?(\\w+bot\\w*).*?", 1);
		if(bot == null) {
			bot = RegexUtil.findByRegexGroup(userAgent, "(?i).*?(\\w+spider\\w*).*?", 1);
		}
		if(bot == null) {
			return userAgent;
		}
		return bot;
	}

	public static boolean isSpider(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return StringUtils.isNotBlank(userAgent) && (userAgent.toLowerCase().contains("spider") || userAgent.toLowerCase().contains("bot"));
	}
	
}
