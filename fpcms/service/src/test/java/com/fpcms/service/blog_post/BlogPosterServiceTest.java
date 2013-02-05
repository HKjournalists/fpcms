package com.fpcms.service.blog_post;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.duowan.common.util.DateConvertUtils;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.blog_post.BlogPoster;


public class BlogPosterServiceTest {
	public @Rule TestName testName = new TestName();
	private ApplicationContext applicationContext;
	private BlogPoster blogPoster;
	
	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-blog_poster.xml");
		blogPoster = (BlogPoster)applicationContext.getBean(testName.getMethodName());
	}
	
	@Test
	public void cto51BlogPoster() {
		blogPoster.postBlog(newBlog("fpqqchao","abc123"));
	}
	
	@Test
	public void oschinaBlogPoster() {
		//email=fpqqchao%40gmail.com&pwd=6367c48dd193d56ea7b0baad25b19455e529f5ee&save_login=1
		System.out.println(DigestUtils.md5Hex("abc123"));
		blogPoster.postBlog(newBlog("fpqqchao@gmail.com","abc123"));
	}
	
	@Test
	public void hexunBlogPoster() {
		blogPoster.postBlog(newBlog("fpqqchao@gmail.com","asdf@1234"));
	}
	

	private Blog newBlog() {
		return newBlog("fpqqchao","abc123");
	}
	
	private Blog newBlog(String username,String password) {
		Blog blog = new Blog();
		blog.setUsername(username);
		blog.setPassword(password);
		blog.setMetaDescription("metaDescription");
		blog.setTitle("test_title 航空第一: "+DateConvertUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		blog.setContent(StringUtils.repeat("test_content\n\t 航空第一:\n <br /> <h1>H1标题</h1>",50));
		return blog;
	}
	
}
