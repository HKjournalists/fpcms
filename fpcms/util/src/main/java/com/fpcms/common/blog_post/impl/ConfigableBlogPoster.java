package com.fpcms.common.blog_post.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.blog_post.BaseBlogPoster;
import com.fpcms.common.util.FreemarkerUtil;

/**
 * 可以配置的博客发送器,
 * 注意，Freemarker的变量引用可以为 @{},避免与spring ${}冲突
 * 
 * @author badqiu
 *
 */
public class ConfigableBlogPoster extends BaseBlogPoster{
	private static Logger logger = LoggerFactory.getLogger(ConfigableBlogPoster.class);
	
	private String loginRequestBodyTemplate;
	private String postNewBlogRequestBodyTemplate;
	
	private String verifyLoginResultRegex;
	private String verifyPostNewBlogResultRegex;
	
	public void setLoginRequestBodyTemplate(String loginRequestBodyTemplate) {
		this.loginRequestBodyTemplate = trimAndReplaced(loginRequestBodyTemplate);
	}

	public void setPostNewBlogRequestBodyTemplate(String postNewBlogRequestBodyTemplate) {
		this.postNewBlogRequestBodyTemplate = trimAndReplaced(postNewBlogRequestBodyTemplate);
	}
	
	public void setVerifyLoginResultRegex(String verifyLoginResultRegex) {
		this.verifyLoginResultRegex = trimAndReplaced(verifyLoginResultRegex);
	}

	public void setVerifyPostNewBlogResultRegex(String verifyPostNewBlogResultRegex) {
		this.verifyPostNewBlogResultRegex = trimAndReplaced(verifyPostNewBlogResultRegex);
	}

	private static String trimAndReplaced(String verifyPostNewBlogResultRegex) {
		return StringUtils.replace(StringUtils.trim(verifyPostNewBlogResultRegex),"@{","${");
	}

	@Override
	protected void setLoginRequestBody(String username, String password,
			PostMethod post) throws Exception {
		Map variables = newVariables();
		variables.put("username", urlEncode(username));
		variables.put("password", urlEncode(password));
		
		String body = FreemarkerUtil.processByFreemarker(loginRequestBodyTemplate, "UTF-8", variables);
		post.setRequestBody(body);
		logger.info("login parameters:"+body);
	}

	@Override
	protected void setPostNewBlogRequestBody(String title, String content,
			String metaDescription, Map<String, String> ext, PostMethod post)
			throws Exception {
		Map variables = newVariables();
		variables.putAll(ext);
		variables.put("title", urlEncode(title));
		variables.put("content", urlEncode(content));
		variables.put("metaDescription", urlEncode(metaDescription));
		String body = FreemarkerUtil.processByFreemarker(postNewBlogRequestBodyTemplate, "UTF-8", variables);
		post.setRequestBody(body);
		logger.info("postNewBlogRequestBody:"+body);
	}

	private Map newVariables() {
		Map variables = new HashMap();
		variables.put("StringUtils", new StringUtils());
		variables.put("DigestUtils", new DigestUtils());
		return variables;
	}
	
	@Override
	protected boolean verifyLoginResult(String responseString) {
		return verifyByContainsOrRegex(responseString,verifyLoginResultRegex);
	}

	@Override
	protected boolean verifyPostNewBlogResult(String responseString) {
		return verifyByContainsOrRegex(responseString,verifyPostNewBlogResultRegex);
	}
	
	private static boolean verifyByContainsOrRegex(String responseString,String regexOrContains) {
		if(StringUtils.isBlank(regexOrContains)) {
			return true;
		}
		if(responseString.contains(regexOrContains)) {
			return true;
		}
		if(responseString.matches(regexOrContains)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		logger.info("loginRequestBodyTemplate:"+loginRequestBodyTemplate);
		logger.info("postNewBlogRequestBodyTemplate:"+postNewBlogRequestBodyTemplate);
		logger.info("verifyLoginResultRegex:"+verifyLoginResultRegex);
		logger.info("verifyPostNewBlogResultRegex:"+verifyPostNewBlogResultRegex);
	}
	
}
