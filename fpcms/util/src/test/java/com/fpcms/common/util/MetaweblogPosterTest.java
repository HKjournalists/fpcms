package com.fpcms.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fpcms.common.blog_post.Blog;


public class MetaweblogPosterTest {
	
	URL url = newURL("http://www.oschina.net/action/xmlrpc");
	
	MetaweblogPoster poster = new MetaweblogPoster(url);
	
	String username = "fpqqchao@gmail.com";
	String password = "abc123";
	Blog blog = new Blog("test_title_from_Metaweblog","test_content");

	@Before
	public void setUp() {
		blog.setCategory("");
	}
	
	@Test
	public void test_newPost() throws MalformedURLException {
		poster.newPost(username, password, blog);
		
	}
	
	@Test
	public void test_newPost_163() throws MalformedURLException {
		poster = new MetaweblogPoster(newURL("http://os.blog.163.com/api/xmlrpc/metaweblog/"));
//		poster.newPost("blogtb123@163.com", "abc123", blog);
		poster.newPost("fpqqchao@gmail.com", "asdf@1234", blog);
	}
	@Test
	public void test_getUsersBlog() throws MalformedURLException {
		Blog blog = new Blog("test_title_from_Metaweblog","test_content");
		Map map = poster.getUsersBlog(username, password);
		System.out.println(map);
	}
	
	private static URL newURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException("MalformedURLException,url:"+url,e);
		}
	}
}
