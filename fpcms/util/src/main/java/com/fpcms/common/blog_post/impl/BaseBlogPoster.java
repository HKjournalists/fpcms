package com.fpcms.common.blog_post.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.fpcms.common.blog_post.BlogPoster;

public abstract class BaseBlogPoster implements BlogPoster,InitializingBean{

	static Logger logger = LoggerFactory.getLogger(BaseBlogPoster.class);

	private BlogPosterHelper helper = new BlogPosterHelper();
	private String loginUrl = null;
	private String postNewBlogUrl = null;
	
	private HttpClient client = new HttpClient();
	{
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getPostNewBlogUrl() {
		return postNewBlogUrl;
	}

	public void setPostNewBlogUrl(String postNewBlogUrl) {
		this.postNewBlogUrl = postNewBlogUrl;
	}

	@Override
	public void postBlog(Blog blog) {
		try {
			login(blog.getUsername(),blog.getPassword());
			postNewBlog(blog.getTitle(),blog.getContent(),blog.getMetaDescription(),blog.getExt());
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void login(String username,String password) throws IOException {
		PostMethod post = helper.newPostMethod(loginUrl);
		try {
			logger.info("login with username:"+username);
			setLoginRequestBody(username, password, post);
			client.executeMethod(post);
			Cookie[] cookies = client.getState().getCookies();
			InputStream stream = post.getResponseBodyAsStream();
			String responseString = IOUtils.toString(stream,"UTF-8");
			verifyHttpStatusCode(post.getStatusCode(),"login error,username:" + username);
			Assert.isTrue(verifyLoginResult(responseString),"login error,username:"+username+" password:"+password);
		}finally {
			post.releaseConnection();
		}
	}

	public static void verifyHttpStatusCode(int statusCode,String attachErrorMessage) {
		if(statusCode >= 200 && statusCode < 400) {
			return;
		}
		throw new IllegalStateException("error http statusCode:" + statusCode + "; " +attachErrorMessage);
	}

	protected void postNewBlog(String title,String content,String metaDescription,Map<String,String> ext) throws IOException, HttpException {
		PostMethod post = helper.newPostMethod(postNewBlogUrl);
		logger.info("postNewBlog:"+title);
		try {
			setPostNewBlogRequestBody(title, content,metaDescription, ext, post);
			client.executeMethod(post);
			InputStream stream = post.getResponseBodyAsStream();
			String responseString = IOUtils.toString(stream,"UTF-8");
			stream.close();
			verifyHttpStatusCode(post.getStatusCode(),"post new blog error,blog title:"+title);
			Assert.isTrue(verifyPostNewBlogResult(responseString),"post blog error,title:"+title);
		}finally {
			post.releaseConnection();
		}
	}

	protected boolean verifyLoginResult(String responseString) {
		return true;
	}

	protected abstract void setLoginRequestBody(String username, String password,PostMethod post);
	
	protected boolean verifyPostNewBlogResult(String responseString) {
		return true;
	}

	protected abstract void setPostNewBlogRequestBody(String title, String content,String metaDescription, Map<String, String> ext, PostMethod post);

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(loginUrl,"loginUrl must be not empty");
		Assert.hasText(postNewBlogUrl,"postNewBlogUrl must be not empty");		
	}
	
}
