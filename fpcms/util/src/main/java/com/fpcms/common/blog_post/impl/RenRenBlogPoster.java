package com.fpcms.common.blog_post.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.blog_post.BlogPoster;

public class RenRenBlogPoster implements BlogPoster {
	static Logger logger = LoggerFactory.getLogger(RenRenBlogPoster.class);

	@Override
	public void postBlog(Blog blog) {
	}

	public void execute() {
		try {
			postNewBlog();
		} catch (HttpException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}

	}

	private void postNewBlog() throws IOException, HttpException {
		PostMethod post = new PostMethod("http://blog.xiaonei.com/NewEntry.do");
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		NameValuePair[] data = new NameValuePair[] {
				new NameValuePair("title", "这是怎么一回子事呢？？"),
				new NameValuePair("body", "调试了半天程序，终于成功了。就把这些内容写到博客里了。"),
				new NameValuePair("categoryId", "0"),
				new NameValuePair("blogControl", "99"),
				new NameValuePair("passwordProtedted", "0") };
		post.setRequestBody(data);
		client.executeMethod(post);
		InputStream stream = post.getResponseBodyAsStream();
		logStream(stream);
		stream.close();
		post.releaseConnection();
	}

	HttpClient client = new HttpClient();

	public void login(String username,String password) throws IOException {
		PostMethod post = new PostMethod("http://login.xiaonei.com/Login.do");
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		NameValuePair[] data = {
				new NameValuePair("email", username),
				new NameValuePair("password", password) };
		post.setRequestBody(data);
		client.executeMethod(post);
		Cookie[] cookies = client.getState().getCookies();
		InputStream stream = post.getResponseBodyAsStream();
		logStream(stream);
		post.releaseConnection();
	}

	private static void logStream(InputStream input) throws IOException {
		IOUtils.copy(input, System.out);
		input.close();
	}

}
