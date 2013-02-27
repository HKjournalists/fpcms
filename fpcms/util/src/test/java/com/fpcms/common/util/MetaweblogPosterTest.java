package com.fpcms.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.fpcms.common.blog_post.Blog;


public class MetaweblogPosterTest {
	
	URL url = newURL("http://www.oschina.net/action/xmlrpc");
	
	MetaweblogPoster poster = new MetaweblogPoster(url);
	
	String username = "fpqqchao@gmail.com";
	String password = "abc123";
	@Test
	public void test_newPost() throws MalformedURLException {
		Blog blog = new Blog("test_title_from_Metaweblog","test_content");
		blog.setCategory("category");
		poster.newPost(username, password, blog);
	}
	@Test
	public void test_getUsersBlog() throws MalformedURLException {
		Blog blog = new Blog("test_title_from_Metaweblog","test_content");
		blog.setCategory("category");
		Map map = poster.getUsersBlog(username, password);
		System.out.println(map);
	}
	
	private static URL newURL(String url) {
		try {
			return new URL("http://www.oschina.net/action/xmlrpc");
		} catch (MalformedURLException e) {
			throw new RuntimeException("MalformedURLException,url:"+url,e);
		}
	}
}
