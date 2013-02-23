package com.fpcms.common.blog_post.impl;

/**
 * OSChina的博客发布器，注意：OSChina的密码是hash 32位密码，需要通过http查看原始密码
 * 
 * @author badqiu
 *
 */
public class OschinaBlogPoster extends ConfigableBlogPoster{

	public OschinaBlogPoster() {
		setLoginUrl("https://www.oschina.net/action/user/hash_login");
		setPostNewBlogUrl("http://my.oschina.net/action/blog/save?as_top=0"); //FIXME 不同的用户,会有不同的URL
		setLoginRequestBodyTemplate("email=@{username}&pwd=${password}&save_login=1");
		setPostNewBlogRequestBodyTemplate("user_code=K0v0y4bY73AvkfZTdNSIRvLm2bnQ0lLZywYBCmoA&draft=0&title=@{title}&catalog=300881&content=@{content}&content_type=0&tags=&music_url=&pubDate=&type=1&origin_url=&privacy=0&deny_comment=0");
	}

}
