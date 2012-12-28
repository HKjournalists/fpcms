package com.fpcms.common.random_gen_article;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.Assert;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.SearchEngineUtil;

/**
 * 生成一个随机文章
 * @author badqiu
 *
 */
public class RandomArticleBuilder {
	
	private String confuseKeyword = " 发票 ";
	private String[] randomConfuseKeywordArray = Constants.FAIPIAO_KEYWORDS;
	
	public RandomArticle buildRandomArticle(String insertKeyword) {
		Set<String> baiduBuzzs = BaiduTopBuzzUtil.getBaiduBuzzs();
		Assert.isTrue(!baiduBuzzs.isEmpty()," baiduBuzzs must be not empty");
		KeywordUtil.filterSensitiveKeyword(baiduBuzzs);
		
		String randomConfuseKeyword = RandomUtil.randomSelect(randomConfuseKeywordArray);
		String randomBuzz = RandomUtil.randomSelect(baiduBuzzs);
		String finalSearchKeyword = "\""+randomBuzz+"\"" +confuseKeyword + randomConfuseKeyword;
		
		RandomArticle article = buildBySearchKeyword(insertKeyword,
				randomConfuseKeyword, randomBuzz, finalSearchKeyword);
		
		return article;
	}

	RandomArticle buildBySearchKeyword(String insertKeyword,
			String randomConfuseKeyword, String randomBuzz,
			String finalSearchKeyword) {
		int randomPageSize = 20 + RandomUtils.nextInt(50);
		int randomPageNumber = 1 + RandomUtils.nextInt(5);
		String result = SearchEngineUtil.sogouSearch(finalSearchKeyword, randomPageSize,randomPageNumber);
		
		String transferedArticle = new ArticleContentProcesser(randomBuzz,insertKeyword).buildArticle(result);
		RandomArticle article = new RandomArticle(randomBuzz,randomConfuseKeyword,finalSearchKeyword,transferedArticle);
		
		String perfectKeyword = KeywordUtil.getPerfectKeyword(result,article.getKeyword());
		article.setPerfectKeyword(StringUtils.defaultString(perfectKeyword,article.getKeyword()));
		return article;
	}
	
}
