package com.fpcms.common.util;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class NaipanArticleGeneratorUtil {
	
	static SimpleHttpInvokerRequestExecutor requestExecutor = new SimpleHttpInvokerRequestExecutor();
	static {
		requestExecutor.setRequestMethod("POST");
	}
	
	public static String transformArticle(String content) {
		try {
			String url = "http://www.naipan.com/weiyuanchuang.action";
			InputStream input = requestExecutor.executeRequest(url, "webContent="+content);
			String response = IOUtils.toString(input,"UTF-8");
			input.close();
			return extractWebContent(response);
		}catch(Exception e) {
			throw new RuntimeException("NaipanArticleGeneratorUtil.transformArticle error,content="+content,e);
		}
	}

	private static String extractWebContent(String input) {
		if(input == null) return null;
		
		Pattern p = Pattern.compile("<textarea name=\"webContent\".*>(.*)</textarea>");
		Matcher m = p.matcher(input);
		if(m.find()) {
			return m.group(1);
		}
		return null;
	}
	
	
}
