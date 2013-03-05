package com.fpcms.common.blog_post.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.util.NetUtil;


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
		// encoding='UTF-8' 必须修改了 GB2312才正确
		String result = NetUtil.httpPost("http://51ctoblog.blog.51cto.com/xmlrpc.php", "<?xml version='1.0' encoding='UTF-8'?><methodCall><methodName>metaWeblog.newPost</methodName><params><param><value>1</value></param><param><value>fpqqchao@gmail.com</value></param><param><value>abc123</value></param><param><value><struct><member><name>title</name><value>MetaWeblogBlogPosterTest title from Http Post</value></member><member><name>description</name><value>MetaWeblogBlogPosterTest content,</value></member><member><name>mt_excerpt</name><value></value></member><member><name>mt_keywords</name><value></value></member><member><name>categories</name><value><array><data><value><string>【创作类型:原创】</string></value><value><string>开发技术</string></value></data></array></value></member></struct></value></param><param><value><boolean>1</boolean></value></param></params></methodCall>", "text/xml");
		System.out.println(result);
		
//		poster.setBlogUrl("http://51ctoblog.blog.51cto.com");
//		poster.setUsername("fpqqchao@gmail.com");
//		poster.setPassword("abc123");
//		Blog blog = new Blog("MetaWeblogBlogPosterTest title","MetaWeblogBlogPosterTest content,");
//		blog.setCategories("【创作类型:原创】","开发技术");
//		poster.postBlog(blog);
	}
	
	@Test
	public void test_blogbug() throws IOException {
		poster.setBlogUrl("http://blogbug.blogbus.com");
		poster.setUsername("blogtg123@gmail.com");
		poster.setPassword("abc123");
		poster.postBlog(new Blog("MetaWeblogBlogPosterTest title",StringUtils.repeat("MetaWeblogBlogPosterTest content,",100)));
	}
	
}
