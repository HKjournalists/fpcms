package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;

import com.duowan.common.util.Profiler;

public class SearchEngineUtil {

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

	static String[] invalidSearchs = new String[]{"检查输入是否正确","请检查您输入的关键词是否有错误","请检查输入字词有无错误"};
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
