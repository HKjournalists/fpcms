package com.fpcms.common.random_gen_article;

import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.SearchEngineUtil;

public class RandomArticleBuilder {

	public RandomArticle buildRandomArticle() {
		Set<String> baiduBuzzs = BaiduUtil.getBaiduBuzzs();
		String randomFaipiao = RandomUtil.randomSelect(Constants.FAIPIAO_KEYWORDS);
		String randomBuzz = RandomUtil.randomSelect(baiduBuzzs);
		String findSearchKeyword = randomBuzz +" 发票  "+randomFaipiao;
		
		int randomPageSize = 20 + RandomUtils.nextInt(50);
		String result = SearchEngineUtil.sogouSearch(findSearchKeyword, randomPageSize,1);
		
		String transferedArticle = new ArticleContentProcesser(randomBuzz).buildArticle(result);
		RandomArticle article = new RandomArticle(randomBuzz,randomFaipiao,findSearchKeyword,transferedArticle);
		return article;
	}
	
}
