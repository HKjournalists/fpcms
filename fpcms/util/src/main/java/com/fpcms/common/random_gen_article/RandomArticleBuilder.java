package com.fpcms.common.random_gen_article;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Assert;

import com.duowan.common.util.DateConvertUtils;
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
	
	private String confuseKeyword = " 发票  ";
	private String[] randomConfuseKeywordArray = Constants.FAIPIAO_KEYWORDS;
	
	public RandomArticle buildRandomArticle(String city) {
		Set<String> baiduBuzzs = BaiduTopBuzzUtil.getBaiduBuzzs();
		Assert.isTrue(!baiduBuzzs.isEmpty()," baiduBuzzs must be not empty");
		KeywordUtil.filterSensitiveKeyword(baiduBuzzs);
		
		String randomConfuseKeyword = RandomUtil.randomSelect(randomConfuseKeywordArray);
		String randomBuzz = RandomUtil.randomSelect(baiduBuzzs);
		String finalSearchKeyword = randomBuzz + " " + randomConfuseKeyword  + " " + city + " " + randomMonth();
//		String finalSearchKeyword = randomBuzz + confuseKeyword + randomConfuseKeyword + " "+ randomMonth();
		
		RandomArticle article = buildBySearchKeyword(city,
				randomConfuseKeyword, randomBuzz, finalSearchKeyword);
		
		return article;
	}
	

	String randomMonth() {
		Date startMonth = DateConvertUtils.parse("2006-01", "yyyy-MM");
		Date now = new Date();
		int maxMonth = (int)((now.getTime() - startMonth.getTime())/1000.0/3600/24/30);
		Date result = DateUtils.addMonths(startMonth, RandomUtils.nextInt(maxMonth));
		return DateConvertUtils.format(result, "yyyy-MM");
	}

	RandomArticle buildBySearchKeyword(String city,
			String randomConfuseKeyword, String randomBuzz,
			String finalSearchKeyword) {
//		int randomPageSize = 20 + RandomUtils.nextInt(50);
		int randomPageSize = 20 + 15;
		int randomPageNumber = 1 + RandomUtils.nextInt(5);
		String result = SearchEngineUtil.sogouSearch(finalSearchKeyword, randomPageSize,randomPageNumber);
		
		ArticleContentProcesser articleContentProcesser = new ArticleContentProcesser(randomBuzz,randomBuzz);
		articleContentProcesser.buildArticle(result);
		String transferedArticle = articleContentProcesser.getArticle();
		RandomArticle article = new RandomArticle(randomBuzz,randomConfuseKeyword,finalSearchKeyword,transferedArticle);
		article.setPerfectKeyword(StringUtils.defaultString(articleContentProcesser.getPerfectKeyword(),article.getKeyword()));
		return article;
	}


	
}
