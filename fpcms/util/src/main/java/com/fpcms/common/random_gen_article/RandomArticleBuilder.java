package com.fpcms.common.random_gen_article;

import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;

import com.duowan.common.util.DateConvertUtils;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.SearchEngineUtil;
import com.fpcms.common.util.UnuseKeywordsUtil;

/**
 * 生成一个随机文章
 * @author badqiu
 *
 */
public class RandomArticleBuilder {
	
//	private String[] randomConfuseKeywordArray = Constants.FAIPIAO_KEYWORDS;
	
	public RandomArticle buildRandomArticle(String city) {
		String keyword = getRandomMainKeyword();
		
//		String randomConfuseKeyword = RandomUtil.randomSelect(Constants.FAIPIAO_KEYWORDS);
		String finalSearchKeyword = keyword + " " + randomMonth();
//		String finalSearchKeyword = keyword + " " + randomConfuseKeyword  + " " + city  + " " + randomMonth();
//		String finalSearchKeyword = randomBuzz + confuseKeyword + randomConfuseKeyword + " "+ randomMonth();
		
		RandomArticle article = buildBySearchKeyword(city,
				keyword, finalSearchKeyword);
		
		return article;
	}


	static String getRandomMainKeyword() {
//		if(RandomUtil.randomTrue(50)) {
//			Set<String> baiduBuzzs = BaiduTopBuzzUtil.getBaiduBuzzs();
//			Assert.isTrue(!baiduBuzzs.isEmpty()," baiduBuzzs must be not empty");
//			KeywordUtil.filterSensitiveKeyword(baiduBuzzs);
//			String randomBuzz = RandomUtil.randomSelect(baiduBuzzs);
//			return randomBuzz;
//		}else {
			return UnuseKeywordsUtil.getRandomUnuseKeyword();
//		}
	}
	

	String randomMonth() {
//		if(RandomUtil.randomTrue(30)) {
//			return "";
//		}
		
		Date startMonth = DateConvertUtils.parse("2006-01", "yyyy-MM");
		Date now = new Date();
		int maxMonth = (int)((now.getTime() - startMonth.getTime())/1000.0/3600/24/30);
		Date result = DateUtils.addMonths(startMonth, RandomUtils.nextInt(maxMonth));
		return DateConvertUtils.format(result, "yyyy年");
//		return DateConvertUtils.format(result, "yyyy年M月");
	}

	RandomArticle buildBySearchKeyword(String city,
			String keyword,
			String finalSearchKeyword) {
//		int randomPageSize = 20 + RandomUtils.nextInt(50);
		int randomPageSize = 10 + 10;
		int randomPageNumber = 1;
//		int randomPageNumber = 1 + RandomUtils.nextInt(5);
		String result = SearchEngineUtil.googleSearch(finalSearchKeyword, randomPageSize,randomPageNumber);
		ArticleContentProcesser articleContentProcesser = new ArticleContentProcesser(keyword);
		articleContentProcesser.buildArticle(result);
		String transferedArticle = articleContentProcesser.getArticle();
		RandomArticle article = new RandomArticle(keyword,null,finalSearchKeyword,transferedArticle);
		article.setPerfectKeyword(articleContentProcesser.getPerfectKeyword());
		return article;
	}


	
}
