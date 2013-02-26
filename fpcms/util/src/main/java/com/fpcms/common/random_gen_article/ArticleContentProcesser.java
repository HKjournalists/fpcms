package com.fpcms.common.random_gen_article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.fpcms.common.util.ChineseSegmenterUtil;
import com.fpcms.common.util.ChineseSegmenterUtil.TokenCount;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.GoogleTranslateUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;

/**
 * 对文件进行:过滤,替换,分段落,增加<h1>标题等操作,然后生成随机文章. 
 * 
 * @author badqiu
 *
 */
public class ArticleContentProcesser {
	
	private String perfectKeyword;
	private String keyword;
	private String article;
	
	public ArticleContentProcesser(String keyword) {
		super();
		this.keyword = keyword;
	}

	public String getPerfectKeyword() {
		return perfectKeyword;
	}
	
	public String getArticle() {
		return article;
	}

	private long pCount = 0;
	public void buildArticle(String content) {
		Assert.hasText(content,"content must be not empty");
		Set<String> tokens = getValidTokens(content);
		
//		filterByChineseSegment(tokens);
		
		KeywordUtil.filterSensitiveKeyword(tokens);
		
		String translatedTokensString = GoogleTranslateUtil.fromEnglish2Chinese(GoogleTranslateUtil.fromChinese2English(StringUtils.join(tokens,",")));
//		System.out.println("translatedTokensString:"+translatedTokensString);
		Collection<String> translatedTokens = getValidTokens(translatedTokensString);
		article = NaipanArticleGeneratorUtil.transformArticle(toHtmlFormat(translatedTokens));
		
		perfectKeyword = getPerfectKeyword(article, keyword);
//		if(StringUtils.isBlank(perfectKeyword)) {
//			perfectKeyword = getPerfectKeyword(StringUtils.join(tokens,","), keyword);
//		}
//		article = GoogleTranslateUtil.fromEnglish2Chinese(GoogleTranslateUtil.fromChinese2English(article));
	}

	private String toHtmlFormat(Collection<String> tokens) {
		StringBuilder result = new StringBuilder();
		for(String token : tokens) {
			if(pCount % 30 == 0) {
				result.append("<p>");
			}
			boolean strongToken = isStrongToken(token) ;
			if(strongToken) {
				result.append("<h3>");
			}
			
			result.append(token).append(KeywordUtil.getSymbol(token));
			
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
	
	int strongCount = 0;
	private boolean isStrongToken(String token) {
		if(isStrongToken0(token) && strongCount <= 5) {
			strongCount++;
			return true;
		}
		return false;
	}
	
	private boolean isStrongToken0(String token) {
		Assert.notNull(token,"token must be not null");
		for(String strong : Constants.FAIPIAO_KEYWORDS) {
			if(token.indexOf(strong) >= 0) {
				return RandomUtil.randomTrue(40);
			}
		}
		if(token.contains(keyword)) {
			return true;
		}
		return false;
	}

	
	Set<String> getValidTokens(String string) {
		string = removeSearchEngineEmphasizeHtmlTag(string);
		
		
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
	
	/**
	 * 删除搜索引擎对关键字的<em>关键字</em> 标记
	 * @param string
	 * @return
	 */
	private String removeSearchEngineEmphasizeHtmlTag(String string) {
		//sogou
		string = StringUtils.remove(string,"<em><!--red_beg-->");
		string = StringUtils.remove(string,"<!--red_end--></em>");
		
		//google and baidu
		string = StringUtils.remove(string,"<em>");
		string = StringUtils.remove(string,"</em>");
		return string;
	}
	
	static String[] ignoreWords = {"搜狗","开","相关搜索","搜索","网页快照","类似结果"}; 
	static boolean isValidToken(String token) {
		if(token.length() <= 6) {
			return false;
		}
		
		if(token.matches(".*\\d{11}.*")) {
			return false;
		}
		if(token.matches("\\d+")) {
			return false;
		}
		if(token.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
			return false;
		}
		if(token.matches("\\d{4}年\\d{1,2}月")) {
			return false;
		}
		for(String ignoreWord : ignoreWords) {
			if(token.contains(ignoreWord)) {
				return false;
			}
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
	
	private String getPerfectKeyword(String transferedArticle, String keyword) {
		String perfectKeyword = KeywordUtil.getPerfectKeyword(transferedArticle,keyword);
		return perfectKeyword;
		
//		String result = null;
//		if(StringUtils.isBlank(result)) {
//			for(String faipiao : Constants.FAIPIAO_KEYWORDS) {
//				result = KeywordUtil.getPerfectKeyword(transferedArticle,faipiao);
//				if(StringUtils.isNotBlank(result)) {
//					if(result.matches(".*\\d{4}.*")) {
//						continue;
//					}
//					return result;
//				}
//			}
//		}
//		return result;
	}
	
}
