package com.fpcms.common.random_gen_article;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.NetUtil;
import com.fpcms.common.util.RegexUtil;

public class BaiduUtil {
	static Logger logger = LoggerFactory.getLogger(BaiduUtil.class);

	public static Set<String> getBaiduBuzzs() {
		Set<String> result = new HashSet<String>();
		for(String url : Constants.BAIDU_BUZZ_URLS) {
			Set<String> keywords = findBaiduBuzzs(url);
			result.addAll(keywords);
		}
		return result;
	}
	
	public static Set<String> findBaiduBuzzs(String url) {
		String topKeyword =  NetUtil.httpGet(url);
		Set<String> keyword = RegexUtil.findAllByRegexGroup(topKeyword, "(?s)<td class=.key.><a.href=..*?. target=._blank.>(\\W+)</a></td>", 1);
		logger.info("getBaiduKeywords,url=" + url + " result:"+keyword);
		return keyword;
	}
	
}
