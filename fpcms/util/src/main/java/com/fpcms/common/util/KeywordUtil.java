package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeywordUtil {
	private static Logger logger = LoggerFactory.getLogger(KeywordUtil.class);
	
	/** 常用标点符号 */
	public static String COMMON_SYMBOLES = ",.?;!，。？；！";
	
	public static String DELIMITERS = " \t\n\r\f,.!?;:'/\"\\()+=-_<>，。！？；：、＝＋－——／·＃—￥％—…—＊（）‘“”～｀《》@#$%^&*~`|\\【】";
	/**
	 * 敏感词
	 */
	private static Set<String> sensitiveKeywordSet = readKeywords("/keyword/sensitive_keyword.txt");
	/**
	 * 非名词
	 */
	private static Set<String> nonNameKeywordSet = readKeywords("/keyword/nonName.txt");
	{
		logger.info("sensitive_keyword:"+sensitiveKeywordSet);
	}
	
	/**
	 * 得到常用标点符号的个数
	 * @param str
	 * @return
	 */
	public static int getCommonSymbolsCount(String str) {
		if(StringUtils.isBlank(str)) {
			return 0;
		}
		int result = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(COMMON_SYMBOLES.indexOf(c) >= 0) {
				result++;
			}
		}
		return result;
	}
	
	public static String getPerfectKeyword(String content,String keyword) {
		if(StringUtils.isBlank(content)) {
			return null;
		}
		if(StringUtils.isBlank(keyword)) {
			return null;
		}
		
		ArrayList<String> tokens = toTokenizerList(content);
		Collections.sort(tokens, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		for(String token : tokens) {
//			if(token.matches(".*\\d{4}.*")) {
//				continue;
//			}
			if(token.contains(keyword)) {
				return token;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> toTokenizerList(String content) {
		StringTokenizer tokenizer = new StringTokenizer(content,KeywordUtil.DELIMITERS);
		ArrayList list = Collections.list(tokenizer);
		return (ArrayList<String>)list;
	}
	
	/**
	 * 过滤敏感词
	 * @param list
	 */
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
	
	/**
	 * 过滤掉非名词
	 * @param list
	 */
	public static void filterNonNameKeyword(Collection<String> list) {
		List<String> removeItems = new ArrayList<String>();
		for(String str : list) {
			if(!isNameKeyword(str)) {
				removeItems.add(str);
			}
		}
		for(String str : removeItems) {
			list.remove(str);
		}
	}
	
	/**
	 * 判断一个词是否是名词
	 * @param keyword
	 * @return
	 */
	public static boolean isNameKeyword(String keyword) {
		for(String str : nonNameKeywordSet) {
			if(keyword.contains(str)) {
				return false;
			}
		}
		return true;
	}
	
	public static Set<String> readKeywords(String classpathResource)  {
		InputStream input = KeywordUtil.class.getResourceAsStream(classpathResource);
		try {
			List<String> lines = IOUtils.readLines(input);
			Set set = new HashSet();
			for(String keywords : lines) {
				String[] array = org.springframework.util.StringUtils.tokenizeToStringArray(keywords,DELIMITERS);
				set.addAll(Arrays.asList(array));
			}
			return set;
		}catch(IOException e) {
			throw new RuntimeException("load error,"+classpathResource,e);
		}finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	public static int getMaxRank(String keywords,String site) {
		String[] keywordsArray = org.springframework.util.StringUtils.tokenizeToStringArray(keywords, ",_| ");
		int min = Integer.MAX_VALUE;
		for(String keyword : keywordsArray) {
			int rank = SearchEngineUtil.baiduKeywordRank(keyword, site);
			if(rank > 0 && rank < min) {
				min = rank;
			}
			if(rank > 0) {
				logger.info("rank_baidu:"+rank+" site:"+site);
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	static String[] a = {"啊","呀","嗯","啦","哪","吧"};
	static String[] question = {"吗"};
	static String[] symbols = {",",".","!",";"};
	public static Object getSymbol(String token) {
		for(int i = 0; i < DELIMITERS.length(); i++) {
			char c = DELIMITERS.charAt(i);
			if(token.endsWith(""+c)) {
				return "";
			}
		}
		
		for(String item : a) {
			if(token.endsWith(item)) {
				return "!";
			}
		}
		for(String item : question) {
			if(token.endsWith(item)) {
				return "?";
			}
		}
		
		return RandomUtil.randomSelect(symbols);
	}
}
