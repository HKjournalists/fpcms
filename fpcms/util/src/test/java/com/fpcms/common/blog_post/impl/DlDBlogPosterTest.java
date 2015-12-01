package com.fpcms.common.blog_post.impl;

import org.junit.Test;

import com.fpcms.common.blog_post.Blog;

public class DlDBlogPosterTest {

	DlDBlogPoster poster = new DlDBlogPoster();
	@Test
	public void test() throws Exception {
		poster.setLoginUrl("https://weblogin.utoronto.ca/");
		poster.setPostNewBlogUrl("http://www.163.com");
		poster.afterPropertiesSet();
		Blog blog = new Blog();
		blog.setUsername("zengkail");
		blog.setPassword("Kelly317");
		poster.postBlog(blog);
		
	}

}
