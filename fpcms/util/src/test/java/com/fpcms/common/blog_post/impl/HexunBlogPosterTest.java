package com.fpcms.common.blog_post.impl;

import java.io.IOException;

import org.junit.Test;

import com.fpcms.common.blog_post.Blog;


public class HexunBlogPosterTest {
	HexunBlogPoster poster = new HexunBlogPoster();
	@Test
	public void login() throws IOException {
		Blog blog = CnblogBlogPosterTest.newBlog();
		blog.setUsername("blogtg123@gmail.com");
		blog.setPassword("abc123");
		poster.postBlog(blog);
	}
	
	
}
