package com.fpcms.common.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Http状态检查工具类
 * 
 * @author badqiu
 *
 */
public class HttpStatusCheckUtil {
	static Logger log = LoggerFactory.getLogger(HttpStatusCheckUtil.class);
	
	public static String getHttpStatus(String site) {
		Assert.hasText(site,"site must be not emtpy");
		try {
			int httpResponseCode = HttpStatusCheckUtil.getHttpResponseCode("http://"+site);
			log.info("http_status, site:"+site+" responseCode:"+httpResponseCode);
			return String.valueOf(httpResponseCode);
		}catch(Exception e) {
			log.error("update http status error on site:"+site,e);
			return e.getMessage();
		}
	}
	
	public static boolean isHttpSuccess(String status) {
		if(StringUtils.isBlank(status)) {
			return false;
		}
		if(status.matches("\\d+")) {
			int s = Integer.parseInt(status);
			if(s >= 200 && s <= 399) {
				return true;
			}
		}
		return false;
	}
	
	public static int getHttpResponseCode(String url) throws IOException {
		return getHttpResponseCode(url,false);
	}
	
	public static int getHttpResponseCode(String url,boolean followRedirects) throws IOException {
		HttpURLConnection conn = null;
		try {
			conn = openConnection(url);
			conn.setReadTimeout(1000 * 10);
			conn.setConnectTimeout(1000 * 10);
			conn.setInstanceFollowRedirects(followRedirects);
			conn.connect();
			
			return conn.getResponseCode();
		}finally {
			disconnect(conn);
		}
	}

	private static void disconnect(HttpURLConnection conn) {
		if(conn != null)
			conn.disconnect();
	}
	
	private static HttpURLConnection openConnection(String url) throws IOException {
		URLConnection con = new URL(url).openConnection();
		if (!(con instanceof HttpURLConnection)) {
			throw new IOException("Service URL [" + url + "] is not an HTTP URL");
		}
		return (HttpURLConnection) con;
	}
}
