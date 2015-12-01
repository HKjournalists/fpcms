package com.fpcms.common.blog_post.impl;

import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;

import com.fpcms.common.blog_post.BaseBlogPoster;

public class DlDBlogPoster extends BaseBlogPoster{

	@Override
	protected void setLoginRequestBody(String username, String password,PostMethod post) throws Exception {
		post.setRequestBody("user="+username+"&pass="+password);
	}

	@Override
	protected void setPostNewBlogRequestBody(String title, String content,
			String metaDescription, Map<String, String> ext, PostMethod post)
			throws Exception {
		
	}

}
