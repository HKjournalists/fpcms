package com.fpcms.common.blog_post.impl;

import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.blog_post.BaseBlogPoster;

public class RenRenBlogPoster extends BaseBlogPoster {
	static Logger logger = LoggerFactory.getLogger(RenRenBlogPoster.class);

	public RenRenBlogPoster() {
		setLoginUrl("http://login.xiaonei.com/Login.do");
		setPostNewBlogUrl("http://blog.xiaonei.com/NewEntry.do");
	}
	
	@Override
	protected void setLoginRequestBody(String username, String password,
			PostMethod post) {
		NameValuePair[] data = {
				new NameValuePair("email", username),
				new NameValuePair("password", password) };
		post.setRequestBody(data);
	}

	@Override
	protected void setPostNewBlogRequestBody(String title, String content,
			String metaDescription, Map<String, String> ext, PostMethod post) {
		NameValuePair[] data = new NameValuePair[] {
				new NameValuePair("title", title),
				new NameValuePair("body", content),
				new NameValuePair("categoryId", "0"),
				new NameValuePair("blogControl", "99"),
				new NameValuePair("passwordProtedted", "0") };
		post.setRequestBody(data);
	}

	@Override
	protected boolean verifyLoginResult(String responseString) {
		return super.verifyLoginResult(responseString);
	}
	
	@Override
	protected boolean verifyPostNewBlogResult(String responseString) {
		return super.verifyPostNewBlogResult(responseString);
	}
	
}
