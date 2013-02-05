package com.fpcms.common.blog_post.impl;

import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.fpcms.common.blog_post.BaseBlogPoster;

public class ChinaUnixBlogPoster extends BaseBlogPoster{

	public ChinaUnixBlogPoster() {
		setLoginUrl("http://blog.chinaunix.net/site/login.html");
		setPostNewBlogUrl("http://blog.chinaunix.net/blog/post.html");
	}

	@Override
	protected void setLoginRequestBody(String username, String password,
			PostMethod post) {
		post.setRequestBody("loginUsername="+username+"&loginPassword="+password+"&login=%E7%99%BB+%E5%BD%95&formhash=397952cf");
	}

	@Override
	protected void setPostNewBlogRequestBody(String title, String content,
			String metaDescription, Map<String, String> ext, PostMethod post) throws Exception {
		post.setRequestBody("articleType=1&subject="+urlEncode(title)+"&message="+urlEncode(content)+"&info="+urlEncode(metaDescription)+"&classid=0&sysclass=96&formhash=965d23f9&blog_edit=submit");
	}

	@Override
	protected boolean verifyLoginResult(String responseString) {
		return responseString.contains("登录成功，正在返回登录前页面");
	}
	
	@Override
	protected boolean verifyPostNewBlogResult(String responseString) {
		return StringUtils.isBlank(responseString);
	}
}
