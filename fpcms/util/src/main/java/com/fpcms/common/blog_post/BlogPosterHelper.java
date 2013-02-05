package com.fpcms.common.blog_post;

import org.apache.commons.httpclient.methods.PostMethod;

public class BlogPosterHelper {

	public PostMethod newPostMethod(String url) {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		post.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		post.setRequestHeader("Accept-Encoding","gzip, deflate");
		post.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
//		post.setRequestHeader("Referer",url);
		post.setRequestHeader("Referer","http://post.blog.hexun.com/f22353hg/postarticle.aspx?xfh=");
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		post.getParams().setContentCharset("UTF-8"); //这个可以控制 NameValuePair的编码，具体请查看  PostMethod.generateRequestEntity();
		return post;
	}
	
}
