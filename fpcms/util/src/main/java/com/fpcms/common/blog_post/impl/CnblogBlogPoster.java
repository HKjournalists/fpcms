package com.fpcms.common.blog_post.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fpcms.common.blog_post.BlogPoster;

public class CnblogBlogPoster implements BlogPoster {
	static Logger logger = LoggerFactory.getLogger(CnblogBlogPoster.class);

	@Override
	public void postBlog(String username, String password, String title,
			String content) {

	}

	public void postNewBlog(String title,String content,boolean candidate) throws IOException, HttpException {
		PostMethod post = new PostMethod("http://www.cnblogs.com/fpqqchao/admin/EditPosts.aspx?opt=1");
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		System.out.println("------------------ postNewBlog() ----------------------");
		
		NameValuePair[] data = new NameValuePair[] {
				new NameValuePair("Editor$Edit$APOptions$APSiteHome$chkDisplayHomePage", "on"),
				new NameValuePair("Editor$Edit$Advanced$chkComments", "on"),
				new NameValuePair("Editor$Edit$Advanced$chkMainSyndication", "on"),
				new NameValuePair("Editor$Edit$Advanced$ckbPublished", "on"),
				new NameValuePair("Editor$Edit$Advanced$tbEnryPassword", ""),
				new NameValuePair("Editor$Edit$Advanced$txbEntryName", ""),
				new NameValuePair("Editor$Edit$Advanced$txbExcerpt", "test_digest"),
				new NameValuePair("Editor$Edit$Advanced$txbTag", ""),
				new NameValuePair("Editor$Edit$APOptions$APSiteHome$cbHomeCandidate", candidate ? "on" : ""), //候选区
				
				new NameValuePair("Editor$Edit$EditorBody", content), //必须大于200
				new NameValuePair("Editor$Edit$lkbPost", "发布"),
				new NameValuePair("Editor$Edit$txbTitle", title),
				
				new NameValuePair("__VIEWSTATE", "/wEPDwULLTE3NjgxMzM0MDIPZBYCZg9kFgYCBg8WAh4EVGV4dAVKPGxpbmsgcmVsPSJzdHlsZXNoZWV0IiB0eXBlPSJ0ZXh0L2NzcyIgaHJlZj0iL2Nzcy9hZG1pbi5jc3M/aWQ9MjAxMjEyMjgiLz5kAggPFgIfAAWdATxzY3JpcHQgdHlwZT0idGV4dC9qYXZhc2NyaXB0IiBzcmM9Ii9zY3JpcHQvanF1ZXJ5LmNuYmxvZ3MudGhpY2tib3guanMiPjwvc2NyaXB0Pgo8c2NyaXB0IHR5cGU9InRleHQvamF2YXNjcmlwdCIgc3JjPSIvc2NyaXB0L2FkbWluLmpzP2lkPTIwMTIxMjI4Ij48L3NjcmlwdD5kAgoPZBYCAgEPZBYaZg8PFgIeC05hdmlnYXRlVXJsBSBodHRwOi8vd3d3LmNuYmxvZ3MuY29tL2ZwcXFjaGFvL2RkAgEPDxYGHgZUYXJnZXRlHwEFIGh0dHA6Ly93d3cuY25ibG9ncy5jb20vZnBxcWNoYW8vHwAFCGZwcXFjaGFvZGQCAg8PFgQfAQUXaHR0cDovL3d3dy5jbmJsb2dzLmNvbS8eCEltYWdlVXJsBS5odHRwOi8vc3RhdGljLmNuYmxvZ3MuY29tL2ltYWdlcy9hZG1pbmxvZ28uZ2lmZGQCBA8WAh4HVmlzaWJsZWhkAgUPFgIfBGhkAgYPFgIfBGhkAgcPFgIfBGdkAhAPZBYCAgEPZBYMAgEPDxYGHglDb2xsYXBzZWRnHgtDb2xsYXBzaWJsZWcfBGhkZAICD2QWBGYPZBYCZg8PFgIfAAUM5re75Yqg6ZqP56yUZGQCAQ9kFg4CAQ8PFgQfAWUfBGhkZAIDDw9kFgIeCW9ua2V5ZG93bgUVdGl0bGVfa2V5ZG93bihldmVudCk7ZAIHD2QWAgIBD2QWCAIBD2QWAgIBD2QWAgIBDxAPFgYeDkRhdGFWYWx1ZUZpZWxkBQpDYXRlZ29yeUlEHg1EYXRhVGV4dEZpZWxkBQVUaXRsZR4LXyFEYXRhQm91bmRnZBAVABUAFCsDAGRkAgMPZBYCAgEPZBYGAgMPZBYCAgEPFgIeCGRpc2FibGVkZGQCBQ8WBB4HY2hlY2tlZGQfBGhkAgYPFgIfAAVhPHNwYW4gc3R5bGU9J2NvbG9yOmdyYXknPuWPkeW4g+WIsOWN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DxYCHwAFDOWtmOS4uuiNieeov2RkAhMPZBYCAgEPZBYCAgIPDxYCHwAFEDIwMTMvMi81IDA6MDg6NDZkZAIEDxYCHwBlZAIFDxYCHwAFBjEwODY5N2QCBg8WAh8ABQM4MDhkAggPFgIfAAUBMGQCEQ8PFgQfAAUIZnBxcWNoYW8fAQUjaHR0cDovL2hvbWUuY25ibG9ncy5jb20vdS9mcHFxY2hhby9kZAISDxYCHwAFXTxhIGhyZWY9Imh0dHA6Ly9zcGFjZS5jbmJsb2dzLmNvbS9tc2cvcmVjZW50IiB0YXJnZXQ9Il9ibGFuayIgaWQ9Imxua19zaXRlX21zZyI+55+t5raI5oGvPC9hPmQCEw8WAh8ABZ0BPGEgaHJlZj0iaHR0cDovL3Bhc3Nwb3J0LmNuYmxvZ3MuY29tL2xvZ291dC5hc3B4P1JldHVyblVSTD1odHRwOi8vd3d3LmNuYmxvZ3MuY29tL2ZwcXFjaGFvLyIgb25jbGljaz0icmV0dXJuIGNvbmZpcm0oJ+ehruiupOimgemAgOWHuueZu+W9leWQlz8nKSI+5rOo6ZSAPC9hPmQCFA8WAh8ABQQyMDEzZAIVDw8WBB8BBRdodHRwOi8vd3d3LmNuYmxvZ3MuY29tLx8ABQnljZrlrqLlm61kZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WCgU0RWRpdG9yJEVkaXQkQVBPcHRpb25zJEFkdmFuY2VkcGFuZWwxJGNrbENhdGVnb3JpZXMkMAUzRWRpdG9yJEVkaXQkQVBPcHRpb25zJEFQU2l0ZUhvbWUkY2hrRGlzcGxheUhvbWVQYWdlBTBFZGl0b3IkRWRpdCRBUE9wdGlvbnMkQVBTaXRlSG9tZSRjYkhvbWVDYW5kaWRhdGUFIUVkaXRvciRFZGl0JEFkdmFuY2VkJGNrYlB1Ymxpc2hlZAUgRWRpdG9yJEVkaXQkQWR2YW5jZWQkY2hrQ29tbWVudHMFMEVkaXRvciRFZGl0JEFkdmFuY2VkJGNoa0Rpc2FibGVBbm9ueW1vdXNDb21tZW50cwUnRWRpdG9yJEVkaXQkQWR2YW5jZWQkY2hrTWFpblN5bmRpY2F0aW9uBSVFZGl0b3IkRWRpdCRBZHZhbmNlZCRjaGtGdWxsVGV4dEluUnNzBR5FZGl0b3IkRWRpdCRBZHZhbmNlZCRjaGtQaW5uZWQFLUVkaXRvciRFZGl0JEFkdmFuY2VkJGNoa0lzT25seUZvclJlZ2lzdGVyVXNlcg=="),
				};
			
			
		post.setRequestBody(data);
		client.executeMethod(post);
		InputStream stream = post.getResponseBodyAsStream();
		String responseString = IOUtils.toString(stream,"UTF-8");
		stream.close();
		post.releaseConnection();
		
		Assert.isTrue(responseString.contains("博客后台管理 - 博客园"),"post blog error,title:"+title);
	}

	HttpClient client = new HttpClient();

	public void login(String username,String password) throws IOException {
		PostMethod post = newPostMethod("http://passport.cnblogs.com/login.aspx");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		
//		NameValuePair[] data = {
//				new NameValuePair("__EVENTTARGET", ""),
//				new NameValuePair("__EVENTARGUMENT", ""),
//				new NameValuePair("__EVENTVALIDATION", "/wEWBQLWwpqPDQLyj/OQAgK3jsrkBALR55GJDgKC3IeGDE1m7t2mGlasoP1Hd9hLaFoI2G05"),
//				new NameValuePair("__VIEWSTATE", "/wEPDwULLTE1MzYzODg2NzZkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQtjaGtSZW1lbWJlcm1QYDyKKI9af4b67Mzq2xFaL9Bt"),
//				new NameValuePair("txtReturnUrl", "http://home.cnblogs.com/"),
//				new NameValuePair("chkRemember", "on"),
//				new NameValuePair("btnLogin", "登 录"),
//				new NameValuePair("tbUserName", username),
//				new NameValuePair("tbPassword", password)
//			};
//		for(NameValuePair pair : data) {
//			pair.setValue(URLEncoder.encode(pair.getValue(),"UTF-8"));
//		}
//		post.setRequestBody(data);
		
		post.setRequestBody("__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwULLTE1MzYzODg2NzZkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQtjaGtSZW1lbWJlcm1QYDyKKI9af4b67Mzq2xFaL9Bt&__EVENTVALIDATION=%2FwEWBQLWwpqPDQLyj%2FOQAgK3jsrkBALR55GJDgKC3IeGDE1m7t2mGlasoP1Hd9hLaFoI2G05&tbUserName="+username+"&tbPassword="+password+"&btnLogin=%E7%99%BB++%E5%BD%95&txtReturnUrl=http%3A%2F%2Fhome.cnblogs.com%2F");
		client.executeMethod(post);
		Cookie[] cookies = client.getState().getCookies();
		InputStream stream = post.getResponseBodyAsStream();
		String responseString = IOUtils.toString(stream,"UTF-8");
		post.releaseConnection();
		Assert.isTrue(responseString.contains("<h2>Object moved to <a href=\"http://home.cnblogs.com/\">here</a>.</h2"),"login error,username:"+username+" password:"+password);
	}

	private PostMethod newPostMethod(String url) {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
		post.setRequestHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		post.setRequestHeader("Accept-Encoding","gzip, deflate");
		post.setRequestHeader("Accept-Language","zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		post.setRequestHeader("Referer",url);
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		return post;
	}

	private static void logStream(InputStream input) throws IOException {
		IOUtils.copy(input, System.out);
		input.close();
	}

}
