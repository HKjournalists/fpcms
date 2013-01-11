package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;

import com.duowan.common.util.Profiler;

public class SearchEngineUtil {

	public static String sosoSearch(String keywords,int pageNumber) {
		Profiler.enter("SearchEngineUtil.sogouSearch");
		try {
			// sd=5&min=730&max=3287 时间段参数
			int pageSize = 10;
			String result = NetUtil.httpGet("http://www.soso.com/q",String.format("num="+pageSize+"&pg="+pageNumber+"&w=%s",keywords));
			if(isInvalidSearchResult(result)) {
				throw new RuntimeException("sosoSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageNumber);
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
				throw new RuntimeException("sogouSearch return empty result,keywords:"+keywords+" pageSize:"+pageSize+" pageNumber:"+pageNumber);
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
		
		String rank = RegexUtil.findByRegexGroup(siteRecord, "class=\"result\" id=\"(11)\"", 1);
		if(rank == null) {
			return 0;
		}
		return Integer.parseInt(rank);
	}

	private static String getBaiduSiteContentByRegex(String keyword, String site) {
		String url = "http://www.baidu.com/s";
		String content = NetUtil.httpGet(url,"wd="+keyword+"&rn=100");
		
		String siteRecord = RegexUtil.findByRegexGroup(content, "(?ims)<table.*?>(.*?"+site+".*?)</table>", 1);
		return siteRecord;
	}
	
	public static int baiduSiteCount(String domain) {
		String url = "http://www.baidu.com/s?ie=utf-8&wd=site:"+domain;
		return baiduSiteCount0(url);
	}

	private static int baiduSiteCount0(String url) {
		String content = NetUtil.httpGet(url);
		String num = RegexUtil.findByRegexGroup(content, "找到相关结果数(\\d+)个", 1);
		if(StringUtils.isNotBlank(num)) {
			return Integer.parseInt(num);
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
