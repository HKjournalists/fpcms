package com.fpcms.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.duowan.common.util.Profiler;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.cache.ValueCallback;

public class SearchEngineUtil {

	public static String sosoSearch(String keywords,int pageNumber) {
		Profiler.enter("SearchEngineUtil.sogouSearch");
		try {
			// sd=5&min=730&max=3287 时间段参数
			int pageSize = 10;
			String result = NetUtil.httpGet("http://www.soso.com/q",String.format("num="+pageSize+"&pg="+pageNumber+"&w=%s",keywords));
			if(isInvalidSearchResult(result)) {
				throw new EmptySearchResultException("sosoSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageNumber);
			}
			return result;
		}finally {
			Profiler.release();
		}
	}

	public static String googleSearch(String keywords, int pageSize,int pageNumber) {
		Profiler.enter("SearchEngineUtil.googleSearch");
		try {
			int start = (pageNumber - 1) * pageSize;
			String result = NetUtil.httpGet("https://www.google.com.hk/search",String.format("hl=zh-CN&start=%s&num=%s&q=%s",start,pageSize,keywords));
			if(isInvalidSearchResult(result)) {
				throw new EmptySearchResultException("googleSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageSize);
			}
			return result;
		}finally {
			Profiler.release();
		}
	}

	public static boolean baiduKeywordsNotExist(String keywords) throws EmptySearchResultException{
		Assert.hasText(keywords,"keywords must be not empty");
		
		List<String> keywordsList = KeywordUtil.toTokenizerList(keywords);
		Collections.sort(keywordsList,new ReverseComparator(new StringLengthComparator()));
		String maxLengthKeyword = keywordsList.get(0);
		try {
			String substring = maxLengthKeyword.substring(0,Math.min(36,maxLengthKeyword.length()));
			baiduSearch("\""+substring+"\"",1,100);
			return false;
		}catch(EmptySearchResultException e) {
			return true;
		}
	}
	
	public static String baiduSearch(String keywords, int pageSize,int pageNumber) throws EmptySearchResultException{
		Profiler.enter("SearchEngineUtil.baiduSearch");
		try {
			int start = (pageNumber - 1) * pageSize;
			String result = NetUtil.httpGet("http://www.baidu.com/s",String.format("pn=%s&rn=%s&wd=%s",start,pageSize,keywords));
			if(isInvalidSearchResult(result)) {
				throw new EmptySearchResultException("baiduSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageSize);
			}
			return result;
		}finally {
			Profiler.release();
		}
	}
	
	public static String sogouSearch(String keywords, int pageSize,int pageNumber) {
		Profiler.enter("SearchEngineUtil.sogouSearch");
		try {
			String result = NetUtil.httpGet("http://www.sogou.com/web",String.format("num="+pageSize+"&page="+pageNumber+"&query=%s",keywords));
			if(isInvalidSearchResult(result)) {
				throw new EmptySearchResultException("sogouSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageNumber);
			}
			return result;
		}finally {
			Profiler.release();
		}
	}
	
	public static int baiduKeywordRank(String keyword,String site) {
		String siteRecord = getBaiduSiteContentByRegex(keyword, site);
		if(siteRecord == null) {
			return 0;
		}
		String rank = RegexUtil.findByRegexGroup(siteRecord, "class=\"result\" id=\"(\\d+)\"", 1);
		if(rank == null) {
			return 0;
		}
		return Integer.parseInt(rank);
	}

	private static Cache cache = CacheManager.createCache(SearchEngineUtil.class, 500);
	public static Map<String,Integer> baiduKeywordsRank(final String keywords,final String site) {
		return cache.get("baiduKeywordsRank:"+keywords+"_"+site, 3600, new ValueCallback<Map<String,Integer>>() {
			@Override
			public Map<String, Integer> create(String key) {
				String[] keywordsArray = org.springframework.util.StringUtils.tokenizeToStringArray(keywords, ",_| ");
				TreeMap<String,Integer> rankMap = new TreeMap<String,Integer>();
				for(String keyword : keywordsArray) {
					int rank = SearchEngineUtil.baiduKeywordRank(keyword, site);
					if(rank > 0) {
						rankMap.put(keyword, rank);
					}
				}
				return MapUtil.sortByValue(rankMap);
			}
		});
	}
	
	private static String getBaiduSiteContentByRegex(String keyword, String site) {
		String url = "http://www.baidu.com/s";
		String content = NetUtil.httpGet(url,"wd="+keyword+"&rn=100");
		int siteIndex = content.indexOf(site);
		if(siteIndex >= 0) {
			String siteContent = content.substring(0,siteIndex);
			int tableIndex = siteContent.lastIndexOf("<table ");
//			int tableIndex = siteContent.lastIndexOf("<table cellpadding=\"0\" cellspacing=\"0\" class=\"result\" ");
			return tableIndex >= 0 ? siteContent.substring(tableIndex) : null;
		}
		return null;
	}
	
	public static int baiduSiteCount(String domain) {
		String url = "http://www.baidu.com/s?ie=utf-8&wd=site:"+domain;
		return baiduSiteCount0(url);
	}

	private static int baiduSiteCount0(String url) {
		String content = NetUtil.httpGet(url);
		String num = RegexUtil.findByRegexGroup(content, "找到相关结果数([\\d,]+)个", 1);
		if(StringUtils.isNotBlank(num)) {
			return Integer.parseInt(num.replace(",", ""));
		}
		return 0;
	}
	
	public static int baiduRecentlySiteCount(String domain) {
		String url = "http://www.baidu.com/s?ie=utf-8&wd=site:"+domain+"&lm=1";
		return baiduSiteCount0(url);
	}

	static String[] invalidSearchs = new String[]{"检查输入是否正确","请检查您输入的关键词是否有错误","请检查输入字词有无错误","请检查输入的关键词是否有误"};
	private static boolean isInvalidSearchResult(String result) {
		if (StringUtils.isBlank(result)) {
			return true;
		}
		for (String invalid : invalidSearchs) {
			if (result.contains(invalid)) {
				return true;
			}
		}
		return false;
	}
	
}
