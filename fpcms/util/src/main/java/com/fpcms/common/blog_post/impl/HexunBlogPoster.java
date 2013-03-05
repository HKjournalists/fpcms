package com.fpcms.common.blog_post.impl;

import java.util.HashMap;
import java.util.Map;

public class HexunBlogPoster extends ConfigableBlogPoster{

	public HexunBlogPoster() {
		setLoginUrl("https://reg.hexun.com/login.aspx");
		setLoginRequestBodyTemplate("username=${username}&password=${password}&LoginStateAuto=1");
		setVerifyLoginResultRegex("Object moved to");
		
		setPostNewBlogUrl("http://post.blog.hexun.com/PostArticleHandler.ashx");
		setPostNewBlogRequestBodyTemplate("blogname=b2982fls83&action=0&oldclass=0&weibo=1&newcategorytextbox=%E8%AF%B7%E8%BE%93%E5%85%A5%E6%96%B0%E5%88%86%E7%B1%BB%E5%90%8D%E7%A7%B0&chkselected=&categorylist=0&ContentSpaw=test123&TitleTextbox=test&articleid=0&draftid=0&SourceTextbox=&SourceUrlTextbox=&TagTextbox=test%20test1&TrackbackTextbox=&BriefTextbox=&VerificationInput=4zaw&postid=101545823&HideCheckbox=1&AcceptCommentCheckbox=1&posttype=%E5%8E%9F%E5%88%9B&postclass=-1&StickOutCheckbox=0&StickOutOrderNumberTextbox=0&StickOutExpiredTimeTextbox=&xfh=undefined");
		setVerifyPostNewBlogResultRegex("11");
		
		Map<String,String> postNewBlogHeaders = new HashMap<String,String>();
		postNewBlogHeaders.put("Referer", "http://post.blog.hexun.com/b2982fls83/postarticle.aspx");
		setPostNewBlogHeaders(postNewBlogHeaders);
	}
	
}
