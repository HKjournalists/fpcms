package com.fpcms.common.blog_post.impl;

import org.junit.Test;

import com.fpcms.common.blog_post.Blog;

public class ChinaUnixBlogPosterTest {

	ChinaUnixBlogPoster poster = new ChinaUnixBlogPoster();
	@Test
	public void test() {
		Blog blog = CnblogBlogPosterTest.newBlog();
		blog.setUsername("blogtg123");
		blog.setPassword("abc123");
		poster.postBlog(blog);
	}
}
