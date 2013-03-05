package com.fpcms.common.blog_post;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public abstract class BaseBlogPoster implements BlogPoster,InitializingBean{

	static Logger logger = LoggerFactory.getLogger(BaseBlogPoster.class);

	private BlogPosterHelper helper = new BlogPosterHelper();
	private String loginUrl = null;
	private String postNewBlogUrl = null;
	private String postNewBlogContentType = null;
	
	private Map<String,String> postNewBlogHeaders = new HashMap<String,String>();
	
	private HttpClient client = new HttpClient();
	{
//		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = StringUtils.trim(loginUrl);
	}

	public String getPostNewBlogUrl() {
		return postNewBlogUrl;
	}

	public void setPostNewBlogUrl(String postNewBlogUrl) {
		this.postNewBlogUrl = StringUtils.trim(postNewBlogUrl);
	}
	
	public void setPostNewBlogHeaders(Map<String, String> postNewBlogHeaders) {
		this.postNewBlogHeaders = postNewBlogHeaders;
	}

	@Override
	public void postBlog(Blog blog) {
		try {
			Cookie[] cookies = login(blog.getUsername(),blog.getPassword());
			postNewBlog(blog.getTitle(),blog.getContent(),blog.getMetaDescription(),blog.getExt(),cookies);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Cookie[] login(String username,String password) throws Exception,IOException {
		Assert.hasText(getLoginUrl(),"loginUrl must be not empty");
		PostMethod post = helper.newPostMethod(getLoginUrl());
		try {
			logger.info("start login with username:"+username+" loginUrl:"+getLoginUrl());
			setLoginRequestBody(username, password, post);
			client.executeMethod(post);
			
			printResponseHeaders(post);
			Cookie[] cookies = client.getState().getCookies();
			printCookies(cookies);
			
			InputStream stream = post.getResponseBodyAsStream();
			String responseString = IOUtils.toString(stream,"UTF-8");
			verifyHttpStatusCode(post.getStatusCode(),"login error,username:" + username+" response:"+responseString);
			Assert.isTrue(verifyLoginResult(responseString),"login error,username:"+username + " response:"+responseString);
			logger.info("login_success with username:"+username+" loginUrl:"+getLoginUrl());
			return fromSetCookieHeader(post.getResponseHeaders("Set-Cookie"));
		}finally {
			post.releaseConnection();
		}
	}

	private Cookie[] fromSetCookieHeader(Header[] responseHeaders) {
		return null;
	}

	private void printResponseHeaders(PostMethod post) {
		Header[] headers = post.getResponseHeaders();
		for(Header h : headers) {
			System.out.println(h.getName()+"="+h.getValue());
		}
	}

	private void printCookies(Cookie[] cookies) {
		System.out.println("--------- print cookies ------");
		for(Cookie c : cookies) {
			System.out.println(c.getName()+"="+c.getValue());
		}
	}
	
	
	private static String toRequestHeaderCookieString(Cookie[] cookies) {
		 String tmpcookies = "";  
		for(Cookie c : cookies) {
			tmpcookies = tmpcookies + c.toString() + ";";  
		}
		return tmpcookies;
	}
	
	public static void verifyHttpStatusCode(int statusCode,String attachErrorMessage) {
		if(statusCode >= 200 && statusCode < 400) {
			return;
		}
		throw new IllegalStateException("error http statusCode:" + statusCode + "; " +attachErrorMessage);
	}

	protected void postNewBlog(String title,String content,String metaDescription,Map<String,String> ext, Cookie[] cookies) throws Exception,IOException, HttpException {
		Assert.hasText(getPostNewBlogUrl(),"postNewBlogUrl must be not empty");
		PostMethod post = helper.newPostMethod(getPostNewBlogUrl());
		if(StringUtils.isNotBlank(getPostNewBlogContentType())) {
			post.setRequestHeader("Content-Type",getPostNewBlogContentType());
		}
		for(String key : postNewBlogHeaders.keySet()) {
			post.setRequestHeader(key, postNewBlogHeaders.get(key));
		}
		post.setRequestHeader("Cookie",toRequestHeaderCookieString(cookies));
		
		try {
			logger.info("start postNewBlog:"+title+" url:"+getPostNewBlogUrl());
			setPostNewBlogRequestBody(title, content,metaDescription, ext, post);
			client.executeMethod(post);
			InputStream stream = post.getResponseBodyAsStream();
			String responseString = IOUtils.toString(stream,"UTF-8");
			stream.close();
			verifyHttpStatusCode(post.getStatusCode(),"post new blog error,blog title:"+title);
			Assert.isTrue(verifyPostNewBlogResult(responseString),"post blog error,title:"+title+" response:"+responseString);
			logger.info("postNewBlog_success:"+title+" url:"+getPostNewBlogUrl());
		}finally {
			post.releaseConnection();
		}
	}

	public String getPostNewBlogContentType() {
		return postNewBlogContentType;
	}

	public void setPostNewBlogContentType(String postNewBlogContentType) {
		this.postNewBlogContentType = postNewBlogContentType;
	}

	protected boolean verifyLoginResult(String responseString) {
		return true;
	}

	protected abstract void setLoginRequestBody(String username, String password,PostMethod post) throws Exception;
	
	protected boolean verifyPostNewBlogResult(String responseString) {
		return true;
	}

	protected abstract void setPostNewBlogRequestBody(String title, String content,String metaDescription, Map<String, String> ext, PostMethod post)  throws Exception;

	public static String urlEncode(String content)
		throws UnsupportedEncodingException {
		if(StringUtils.isBlank(content)) {
			return null;
		}
		return URLEncoder.encode(content,"UTF-8");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(getLoginUrl(),"loginUrl must be not empty");
		Assert.hasText(getPostNewBlogUrl(),"postNewBlogUrl must be not empty");		
		
		logger.info("loginUrl:"+loginUrl);
		logger.info("postNewBlogUrl:"+postNewBlogUrl);
	}
	
}
