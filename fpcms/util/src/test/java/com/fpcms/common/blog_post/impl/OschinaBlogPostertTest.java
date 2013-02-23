package com.fpcms.common.blog_post.impl;

import org.junit.Test;

import com.fpcms.common.blog_post.Blog;

public class OschinaBlogPostertTest {

	OschinaBlogPoster poster = new OschinaBlogPoster();
	@Test
	public void test() {
		Blog blog = CnblogBlogPosterTest.newBlog();
		blog.setUsername("fpqqchao@gmail.com");
		blog.setPassword("6367c48dd193d56ea7b0baad25b19455e529f5ee");
		poster.postBlog(blog);
	}
	
}
