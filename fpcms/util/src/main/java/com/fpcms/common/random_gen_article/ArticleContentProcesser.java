package com.fpcms.common.random_gen_article;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.StringUtils;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;

public class ArticleContentProcesser {
	static String delimiters = " \t\n\r\f,.!?;:'\"()<>-，。！？；：、‘“”（《》";
	private boolean filterToken = false;
	private String searchKeyword;
	
	public ArticleContentProcesser(String searchKeyword) {
		super();
		this.searchKeyword = searchKeyword;
	}

	private long pCount = 0;
	public String buildArticle(String content) {
		Set<String> tokens = getValidTokens(content);
		insertKeyWords(tokens);
		
		if(filterToken) {
			tokens = filterTokens(tokens);
		}
		
//		return toString(tokens);
		return NaipanArticleGeneratorUtil.transformArticle(toString(tokens));
	}

	private String toString(Set<String> tokens) {
		StringBuilder result = new StringBuilder();
		for(String token : tokens) {
			if(pCount % 30 == 0) {
				result.append("<p>");
			}
			boolean strongToken = isStrongToken(token) ;
			if(strongToken) {
				result.append("<h3>");
			}
			
			result.append(token).append(getSymbol(token));
			
			if(strongToken) {
				result.append("</h3>");
			}
			if(pCount % 30 == 29) {
				result.append("</p>");
			}
			pCount++;
		}
		return result.toString();
	}
	
	private boolean isStrongToken(String token) {
		for(String strong : Constants.FAIPIAO_KEYWORDS) {
			if(token.indexOf(strong) >= 0) {
				return true && RandomUtils.nextInt(2) % 2 == 0;
			}
		}
		if(token.contains(searchKeyword)) {
			return true;
		}
		return false;
	}

	String[] replaseToken = {"物品","建筑","广告","教育","商品","房屋","商业"};
	private Set<String> filterTokens(Set<String> tokens) {
		Set<String> result = new HashSet<String>();
		for(String token : tokens) {
			for(String filter : Constants.FAIPIAO_KEYWORDS) {
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
