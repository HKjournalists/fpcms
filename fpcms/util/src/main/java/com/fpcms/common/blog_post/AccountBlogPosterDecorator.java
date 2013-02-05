package com.fpcms.common.blog_post;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class AccountBlogPosterDecorator implements BlogPoster,InitializingBean{
	private BlogPoster delegate;
	private String username;
	private String password;

	public AccountBlogPosterDecorator() {
	}
	
	public AccountBlogPosterDecorator(BlogPoster delegate) {
		super();
		setDelegate(delegate);
	}
	
	public AccountBlogPosterDecorator(BlogPoster delegate, String username,
			String password) {
		super();
		setDelegate(delegate);
		this.username = username;
		this.password = password;
	}

	public BlogPoster getDelegate() {
		return delegate;
	}

	public void setDelegate(BlogPoster delegate) {
		Assert.notNull(delegate,"delegate BlogPoster must be not null");
		this.delegate = delegate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void postBlog(Blog blog) {
		blog.setUsername(username);
		blog.setPassword(password);
		delegate.postBlog(blog);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasText(username,"username must be not empty");
		Assert.hasText(password,"password must be not empty");
	}
	
}
