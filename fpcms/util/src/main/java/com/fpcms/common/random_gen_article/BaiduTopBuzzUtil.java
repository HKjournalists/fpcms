package com.fpcms.common.random_gen_article;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duowan.common.util.Profiler;
import com.fpcms.common.cache.CacheUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.NetUtil;
import com.fpcms.common.util.RegexUtil;
/**
 * 查询百度的相关热门关键字
 * 
 * @author badqiu
 *
 */
public class BaiduTopBuzzUtil {
	static Logger logger = LoggerFactory.getLogger(BaiduTopBuzzUtil.class);

	static String CACHE_KEYWORD_BUZZS = "KEYWORD_BUZZS";
	public static Set<String> getBaiduBuzzs() {
		Set set = (Set)CacheUtil.get(CACHE_KEYWORD_BUZZS);
		if(set == null) {
			set =  getBaiduBuzzs0();
			CacheUtil.set(CACHE_KEYWORD_BUZZS, set, 3600 * 6);
		}
		return set;
	}

	private static Set<String> getBaiduBuzzs0() {
		Set<String> result = new HashSet<String>();
		for(String url : Constants.BAIDU_BUZZ_URLS) {
			try {
				Set<String> keywords = findBaiduBuzzs(url);
				result.addAll(keywords);
			}catch(Exception e) {
				logger.error("read url for buzz error:"+url,e);
			}
		}
		return result;
	}
	
	public static Set<String> findBaiduBuzzs(String url) {
		Profiler.enter("findBaiduBuzzs");
		try {
			String topKeyword =  NetUtil.httpGet(url);
			String[] keywordRegex = {
					"(?s)<a class=\"list-title\" target=\"_blank\" href=\"./detail.{1,80}>(\\W+)</a>", // baidu
					"(?s)<a title=..{1,70}. href=..{1,120}. target=._blank.>(\\W+)</a></li>" // sougou
			};
			for(String pattern : keywordRegex) {
				Set<String> keyword = RegexUtil.findAllByRegexGroup(topKeyword, pattern, 1);
				if(!keyword.isEmpty()) {
					logger.info("getBaiduKeywords,url=" + url + " result:"+keyword);
					return keyword;
				}
			}
			return new HashSet<String>();
		}finally {
			Profiler.release();
		}
	}
	
}
