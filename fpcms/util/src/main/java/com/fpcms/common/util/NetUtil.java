package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtil {
	private static Logger logger = LoggerFactory.getLogger(NetUtil.class);
	public static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
	static HttpClient client = new HttpClient();
	
	static {
		init();
	}

	public static void init() {
		if (manager != null) {
			manager.shutdown();
			manager = null;
		}
		int connetctTimeout = 10000;
		int soTimeout = 25000;
		manager = new MultiThreadedHttpConnectionManager();
		System.setProperty("apache.commons.httpclient.cookiespec","COMPATIBILITY");
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setDefaultMaxConnectionsPerHost(50);
		params.setMaxTotalConnections(1000);
		params.setConnectionTimeout(connetctTimeout);
		params.setSoTimeout(soTimeout);
		manager.setParams(params);
		manager.closeIdleConnections(15000);
		
		
		client.setHttpConnectionManager(manager);
		HttpClientParams cparams = new HttpClientParams();
		cparams.setConnectionManagerTimeout(connetctTimeout);
		cparams.setSoTimeout(soTimeout);
		client.setParams(cparams);
	}


	public static String httpGet(String url) {
		return httpGet(url,null);
	}
	
	public static String httpGet(String url, String parameters) {
		String finalUrl = getUrlWithParameters(url, parameters);
		logger.info("httpGet:"+finalUrl+" parameters:"+parameters);
		GetMethod method = new GetMethod(finalUrl);
		method.setFollowRedirects(true);
		
		setHeaders(method);
		return executeForString(finalUrl, method);
	}

	private static String executeForString(String finalUrl, HttpMethodBase method) {
		try {
			client.executeMethod(method);
			String charset = getCharset(method);
			InputStream is = method.getResponseBodyAsStream();
			return IOUtils.toString(is,charset);
		} catch (Exception e) {
			throw new RuntimeException("读取url=[" + finalUrl + "]数据异常",e); 
		} finally {
			method.releaseConnection();
		}
	}


	private static void setHeaders(HttpMethodBase method) {
		method.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		method.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	}
	
	public static String httpPost(String url, String postBody,String contextType) {
		PostMethod method = newPostMethod(url);
		method.setRequestBody(postBody);
		method.setRequestHeader("Content-Type", "text/xml");
		return executeForString(url,method);
	}
	
	public static String httpPost(String url, Map<String,Object> parameters) {
		PostMethod method = newPostMethod(url);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : parameters.keySet()) {
			String value = String.valueOf(parameters.get(key));
			params.add(new NameValuePair(key, value));
		}
		method.setRequestBody(params.toArray(new NameValuePair[params.size()]));
		return executeForString(url,method);
	}


	private static PostMethod newPostMethod(String url) {
		logger.info("httpPost:"+url);
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
		setHeaders(method);
		return method;
	}

	private static String getCharset(HttpMethodBase method) throws IOException {
		String charset = RegexUtil.findByRegexGroup(method.getResponseBodyAsString(), "(?s)<meta.*?charset\\s*=\\s*([\\w-]+)", 1);
		if(StringUtils.hasText(charset)) {
			return charset;
		}
		return method.getResponseCharSet();
	}


	private static String getUrlWithParameters(String url, String parameters) {
		String finalUrl = null;
		if(url.contains("?")) {
			finalUrl = url + "&" + encode(parameters);
		}else {
			finalUrl = url + "?" + encode(parameters);
		}
		return finalUrl;
	}


	private static String encode(String parameters) {
		if(parameters == null) return null;
		try {
			return URLEncoder.encode(parameters, "UTF-8").replace("%3D", "=").replace("%26", "&");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
