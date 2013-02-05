package com.fpcms.common.blog_post.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class CnblogBlogPosterTest {
	CnblogBlogPoster poster = new CnblogBlogPoster();
	@Test
	public void login() throws IOException {
		Blog blog = new Blog();
		blog.setUsername("fpqqchao");
		blog.setPassword("abc123");
		blog.setTitle("test_title");
		blog.setContent(StringUtils.repeat("<p>test_content</p>",70));
		poster.postBlog(blog);
	}
	
}
