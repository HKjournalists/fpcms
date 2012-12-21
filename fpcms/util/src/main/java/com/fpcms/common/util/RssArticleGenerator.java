package com.fpcms.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.StringUtils;

public class RssArticleGenerator {
	private String rssUrl;
	static String delimiters = " \t\n\r\f,.!?;:'\"()<>-，。！？；：、‘“”（《》";
	
	public String buildArticle() {
		String content = SimpleNetUtil.readUrl("http://www.iteye.com/search?query=发票&sort=&type=all","");
		return NaipanArticleGeneratorUtil.transformArticle(buildArticle(content));
	}

	public String buildArticle(String content) {
		Set<String> tokens = getValidTokens(content);
		insertKeyWords(tokens);
		
		tokens = filterTokens(tokens);
		StringBuilder result = new StringBuilder();
		for(String token : tokens) {
			result.append(token).append(getSymbol(token));
		}
		return result.toString();
	}
	
	String[] filterArray = {"发票","增值税","税控","地税","国税","税务","发票税","纳税","纳税人","税法","税收"};
	String[] replaseToken = {"物品","建筑","广告","教育","商品","房屋","商业"};
	private Set<String> filterTokens(Set<String> tokens) {
		Set<String> result = new HashSet<String>();
		for(String token : tokens) {
			for(String filter : filterArray) {
				String newToken = RandomUtil.randomSelect(replaseToken);
				token = StringUtils.replace(token,filter,newToken);
			}
			result.add(token);
		}
		return result;
	}


	private void insertKeyWords(Set<String> tokens) {
		tokens.add("广东发票");
	}
	
	private void strongTokens(Set<String> tokens) {
		
	}

	String[] a = {"啊","呀","嗯","啦","哪","吧"};
	String[] question = {"吗"};
	String[] symbols = {",",".","!",";"};
	private Object getSymbol(String token) {
		
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

	public Set<String> getValidTokens(String string) {
		Set<String> tokens = new HashSet<String>();
		StringTokenizer tokenizer = new StringTokenizer(string,delimiters);
		while(tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			if(isValidToken(token)) {
				tokens.add(token);
			}
		}
		return tokens;
	}

	private boolean isValidToken(String token) {
		if(token.length() < 8) {
			return false;
		}
		for(int i = 0; i < token.length(); i++) {
			char c = token.charAt(i);
			if((int)c < 1024) {
				return false;
			}
		}
		
		return true;
	}
	
}
