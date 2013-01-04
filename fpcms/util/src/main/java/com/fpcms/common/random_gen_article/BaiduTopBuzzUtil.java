package com.fpcms.common.random_gen_article;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duowan.common.util.Profiler;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.cache.ValueCallback;
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
	static Cache cache = CacheManager.createCache(Constants.BAIDU_BUZZ_URLS.length+1);
	public static Set<String> getBaiduBuzzs() {
		return getBaiduBuzzs0();
	}

	private static Set<String> getBaiduBuzzs0() {
		Set<String> result = new HashSet<String>();
		for(String url : Constants.BAIDU_BUZZ_URLS) {
			try {
				Set<String> keywords = cache.get(url, 3600 * 6,new ValueCallback<Set<String>>() {
					public Set<String> create(String key) {
						return findBaiduBuzzs(key);
					}
				});
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
					"(?s)<a title=..{1,70}. href=..{1,120}. target=._blank.>(\\W+)</a></li>", // sougou
					"(?s)<a href=\".*?\" title=\"\\W+\" onclick=\".*?\" target=\"_blank\">(\\W+)</a>"
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
