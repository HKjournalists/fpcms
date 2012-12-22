package com.fpcms.common.random_gen_article;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.util.SimpleHttpInvokerRequestExecutor;

public class NaipanArticleGeneratorUtil {
	static Logger logger = LoggerFactory.getLogger(NaipanArticleGeneratorUtil.class);
	static SimpleHttpInvokerRequestExecutor requestExecutor = new SimpleHttpInvokerRequestExecutor();
	static {
		requestExecutor.setRequestMethod("POST");
	}
	
	static int SEGEMENT_SIZE = 170; //奶盘一次只能转换170字节
	public static String transformArticle(String content) {
		try {
			return transformArticle0(content);
		}catch(Exception e) {
			logger.error("transformArticle error,result input content",e);
			return content;
		}
	}

	private static String transformArticle0(String content) {
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
		Pattern p = Pattern.compile("(?s)<textarea name=\"webContent\".*?>(.*)</textarea>");
		Matcher m = p.matcher(input);
		if(m.find()) {
			String result = m.group(1);
			return result;
		}
		return null;
	}
	
	
}
