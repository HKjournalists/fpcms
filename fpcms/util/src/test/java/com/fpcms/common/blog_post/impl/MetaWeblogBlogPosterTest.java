package com.fpcms.common.blog_post.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.fpcms.common.blog_post.Blog;


public class MetaWeblogBlogPosterTest {
	MetaWeblogBlogPoster poster = new MetaWeblogBlogPoster();
	@Test
	public void test_163() throws IOException {
		poster.setBlogUrl("http://sh292did.blog.163.com/");
		poster.setUsername("fpqqchao@gmail.com");
		poster.setPassword("asdf@1234");
		poster.postBlog(new Blog("MetaWeblogBlogPosterTest title","MetaWeblogBlogPosterTest content"));
	}
	
	@Test
	public void test_sina() throws IOException {
		poster.setBlogUrl("http://blog.sina.com.cn/u/3099457992");
		poster.setUsername("fpqqchao@gmail.com");
		poster.setPassword("asdf@1234");
		poster.postBlog(new Blog("MetaWeblogBlogPosterTest title","MetaWeblogBlogPosterTest content"));
	}
	
	@Test
	public void test_51cto() throws IOException {
		poster.setBlogUrl("http://51ctoblog.blog.51cto.com");
		poster.setUsername("fpqqchao@gmail.com");
		poster.setPassword("abc123");
		poster.postBlog(new Blog("MetaWeblogBlogPosterTest title",StringUtils.repeat("MetaWeblogBlogPosterTest content,",100)));
	}
	
	@Test
	public void test_blogbug() throws IOException {
		poster.setBlogUrl("http://blogbug.blogbus.com");
		poster.setUsername("blogtg123@gmail.com");
		poster.setPassword("abc123");
		poster.postBlog(new Blog("MetaWeblogBlogPosterTest title",StringUtils.repeat("MetaWeblogBlogPosterTest content,",100)));
	}
	
}
