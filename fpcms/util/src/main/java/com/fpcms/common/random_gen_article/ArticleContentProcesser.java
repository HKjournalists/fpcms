package com.fpcms.common.random_gen_article;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.Assert;

import com.fpcms.common.util.ChineseSegmenterUtil;
import com.fpcms.common.util.ChineseSegmenterUtil.TokenCount;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;

/**
 * 对文件进行:过滤,替换,分段落,增加<h1>标题等操作,然后生成随机文章. 
 * 
 * @author badqiu
 *
 */
public class ArticleContentProcesser {
	/**
	 * 需要加强的keyword
	 */
	private String strongKeyword;
	/**
	 * 需要插入在文档中的keyword
	 */
	private String insertKeyword;
	
	public ArticleContentProcesser(String strongKeyword,String insertKeyword) {
		super();
		this.strongKeyword = strongKeyword;
		this.insertKeyword = insertKeyword;
	}

	private long pCount = 0;
	public String buildArticle(String content) {
		Set<String> tokens = getValidTokens(content);
		insertKeyWords(tokens);
		
		filterByChineseSegment(tokens);
		
		KeywordUtil.filterSensitiveKeyword(tokens);
		
//		return toString(tokens);
		return NaipanArticleGeneratorUtil.transformArticle(toString(tokens));
	}

	private void filterByChineseSegment(Set<String> tokens) {
		Map<String,Integer> tokenCountMap = ChineseSegmenterUtil.segmenteForTokenCount(StringUtils.join(tokens,","));
		List<TokenCount> tokenCountList =ChineseSegmenterUtil.toSortedTokenCountList(tokenCountMap);
		for(int i =0; i < tokenCountList.size(); i++){
			TokenCount tokenCount = tokenCountList.get(i);
			if(tokenCount.getCount() >= 3) {
				System.out.print(tokenCount+"  ");
			}
		}
		System.out.println();
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
				result.append("</p>\n");
			}
			pCount++;
		}
		return result.toString();
	}
	
	private boolean isStrongToken(String token) {
		Assert.notNull(token,"token must be not null");
		for(String strong : Constants.FAIPIAO_KEYWORDS) {
			if(token.indexOf(strong) >= 0) {
				return true && RandomUtils.nextInt(2) % 2 == 0;
			}
		}
		if(token.contains(strongKeyword)) {
			return true;
		}
		return false;
	}

	String[] replaseToken = {"物品","建筑","广告","教育","商品","房屋","商业"};



	private void insertKeyWords(Set<String> tokens) {
		if(StringUtils.isNotBlank(insertKeyword)) {
			tokens.add(insertKeyword);
			tokens.add(insertKeyword+"2");
		}
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
		StringTokenizer tokenizer = new StringTokenizer(string,KeywordUtil.DELIMITERS);
		while(tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			if(isValidToken(token)) {
				tokens.add(token);
			}
		}
		return tokens;
	}

	boolean isValidToken(String token) {
		if(token.length() < 8) {
			return false;
		}
		
		if(token.matches(".*\\d{11}.*")) {
			return false;
		}
		if(token.matches("\\d+")) {
			return false;
		}
		if(token.contains("搜狗")) {
			return false;
		}
		
		for(int i = 0; i < token.length(); i++) {
			char c = token.charAt(i);
			if(Character.isDigit(c)) {
				continue;
			}
			if((int)c < 1024) {
				return false;
			}
		}
		
		return true;
	}
	
}
