package com.fpcms.common.blog_post.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.github.rapid.common.util.DateConvertUtil;
import com.fpcms.common.blog_post.Blog;


public class CnblogBlogPosterTest {
	CnblogBlogPoster poster = new CnblogBlogPoster();
	@Test
	public void login() throws IOException {
		Blog blog = newBlog();
		blog.setUsername("fpqqchao");
		blog.setPassword("abc123");
		poster.postBlog(blog);
	}
	
	public static Blog newBlog() {
		Blog blog = new Blog();
		blog.setTitle("test_title 测试标题:"+DateConvertUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		blog.setContent(StringUtils.repeat("中国人民\t<p>test_content</p> \n",50));
		return blog;
	}
	
}
