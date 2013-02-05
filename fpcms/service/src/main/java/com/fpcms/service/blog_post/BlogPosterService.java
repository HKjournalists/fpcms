package com.fpcms.service.blog_post;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fpcms.common.blog_post.BlogPoster;
import com.fpcms.common.util.ApplicationContextUtil;

public class BlogPosterService implements ApplicationContextAware,InitializingBean{

	private List<BlogPoster> blogPosterList;
	private ApplicationContext applicationContext;
	
	public void setBlogPosterList(List<BlogPoster> blogPosterList) {
		this.blogPosterList = blogPosterList;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		blogPosterList = ApplicationContextUtil.getBeans(applicationContext,BlogPoster.class);
	}
	
}
