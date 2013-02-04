package com.fpcms.common.blog_post.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class CnblogBlogPosterTest {
	CnblogBlogPoster poster = new CnblogBlogPoster();
	@Test
	public void login() throws IOException {
		poster.login("fpqqchao", "abc123");
		poster.postNewBlog("test_title",StringUtils.repeat("<p>test_content</p>",70),false);
	}
	
}
