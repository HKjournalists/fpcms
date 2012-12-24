package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeywordUtil {
	private static Logger logger = LoggerFactory.getLogger(KeywordUtil.class);
	private static HashSet<String> sensitiveKeywordSet = readLines("/keyword/sensitive_keyword.txt");
	{
		logger.info("sensitive_keyword:"+sensitiveKeywordSet);
	}
	
	public static String DELIMITERS = " \t\n\r\f,.!?;:'\"()+=-_<>，。！？；：、＝＋－——／·＃—￥％—…—＊（）‘“”～｀《》@#$%^&*~`|\\";
	public static String getPerfectKeyword(String content,String keyword) {
		if(StringUtils.isBlank(content)) {
			return null;
		}
		if(StringUtils.isBlank(keyword)) {
			return null;
		}
		
		ArrayList<String> tokens = list(content);
		Collections.sort(tokens, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		for(String token : tokens) {
			if(token.contains(keyword)) {
				return token;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<String> list(String content) {
		StringTokenizer tokenizer = new StringTokenizer(content,KeywordUtil.DELIMITERS);
		ArrayList list = Collections.list(tokenizer);
		return (ArrayList<String>)list;
	}
	
	
	public static void filterSensitiveKeyword(Collection<String> list) {
		List<String> removeItems = new ArrayList<String>();
		for(String str : list) {
			if(isSensitiveKeyword(str)) {
				removeItems.add(str);
			}
		}
		for(String str : removeItems) {
			list.remove(str);
		}
	}
	
	/**
	 * 是否敏感关键字
	 * @param keyword
	 * @return
	 */
	public static boolean isSensitiveKeyword(String keyword) {
		if(StringUtils.isBlank(keyword)) {
			return false;
		}
		
		for(String str : sensitiveKeywordSet) {
			if(keyword.contains(str)) {
				return true;
			}
		}
		return false;
	}

	private static HashSet<String> readLines(String classpathResource)  {
		InputStream input = KeywordUtil.class.getResourceAsStream(classpathResource);
		try {
			List<String> keywords = IOUtils.readLines(input);
			return new HashSet(keywords);
		}catch(IOException e) {
			throw new RuntimeException("load error,"+classpathResource,e);
		}finally {
			IOUtils.closeQuietly(input);
		}
	}
}
