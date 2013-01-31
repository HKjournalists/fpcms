package com.fpcms.common.util;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

/**
 * 加载一些无用的搜索关键字
 * @author badqiu
 *
 */
public class UnuseKeywordsUtil {
	
	private static Set<String> unuseKeywords = null;
	
	public static String getRandomUnuseKeyword() {
		return (String)RandomUtil.randomSelect(getUnuseKeywords());
	}
	
	public static synchronized Set<String> getUnuseKeywords() {
		if(unuseKeywords == null) {
			unuseKeywords = load();
		}
		return unuseKeywords;
	}

	public static Set<String> load() {
		String resourceName = "/unuse_keywords.txt";
		try {
			Set unuseKeywordSet = KeywordUtil.readKeywords(resourceName);
			KeywordUtil.filterSensitiveKeyword(unuseKeywordSet);
			KeywordUtil.filterNonNameKeyword(unuseKeywordSet);
			return unuseKeywordSet;
		}catch(Exception e) {
			throw new RuntimeException("error on getResourceAsStream:"+resourceName,e);
		}
	}
}
