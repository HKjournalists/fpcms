package com.fpcms.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过MetaWeblog进行博客管理,
 * 参考文档: http://xmlrpc.scripting.com/metaWeblogApi.html
 *  
 * @author badqiu
 *
 */
public class MetaWeblog {
	private static Logger logger = LoggerFactory.getLogger(MetaweblogPoster.class);
	
	private URL blogUrl;
	private XmlRpcClient blogClient;
	
	public MetaWeblog(String blogUrl) throws MalformedURLException {
		this(new URL(blogUrl));
	}
	
	public MetaWeblog(URL blogUrl) {
		this.blogUrl = blogUrl;
		this.initBlogClient();
	}

	public URL getBlogUrl() {
		return blogUrl;
	}

	/*
	 * 初始化博客发送客户端
	 */
	private void initBlogClient() {
		if (this.blogUrl != null) {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(this.blogUrl);
			this.blogClient = new XmlRpcClient();
			blogClient.setConfig(config);
		}
	}
	
	/**
	 * metaWeblog.newPost
	 * @return string : postId
	 */
	public void newPost(String blogid, String username, String password, Map<String,Object> struct, String publish){
	}
	/**
	 * metaWeblog.editPost
	 * @param postid
	 * @param username
	 * @param password
	 * @param struct
	 * @param publish 
	 * @return true
	 */
	public boolean editPost(String postid, String username,String  password,Map<String,Object> struct, String publish) {
		return false;
	}
	/**
	 * metaWeblog.getPost
	 * @param postid
	 * @param username
	 * @param password
	 * @return struct
	 */
	public Map getPost(String postid, String username, String password) {
		return null;
	}
	
	/**
	 * 
	 * @param blogid
	 * @param username
	 * @param password
	 */
	public List<Map<String,Object>> getCategories(String blogid, String username, String password) {
		return null;
	}
	/**
	 * metaWeblog.getRecentPosts
	 * @param blogid
	 * @param username
	 * @param password
	 * @param numberOfPosts
	 */
	public List<Map<String,Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) {
		return null;
	}
	
	
}
