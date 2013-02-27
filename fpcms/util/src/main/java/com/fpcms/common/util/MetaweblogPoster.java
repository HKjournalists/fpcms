package com.fpcms.common.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.blog_post.Blog;

public class MetaweblogPoster {
	private static Logger logger = LoggerFactory.getLogger(MetaweblogPoster.class);
	
	private URL blogUrl;
	private XmlRpcClient blogClient;
	private String blogId = "default";

	public MetaweblogPoster(URL blogUrl) {
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

	public Map getUsersBlog(String username,String password)  {
		try {
			String appKey = "11111111111xxxxxxx";
			Object[] array = (Object[])this.blogClient.execute("blogger.getUsersBlogs", new Object[]{appKey,username,password});
			return (Map)array[0];
		}catch(XmlRpcException e) {
			throw new RuntimeException("blogger.getUsersBlogs error,username:"+username+" url:"+blogUrl,e);
		}
	}
	
	/*
	 * @username 发博文时需要的用户名
	 * @password 发博文时需要的密码
	 * @blogUrl 发博文时对应的metaweblog url
	 * 
	 * @blog 要发送的博文对象,它存储了博文的标题,分类,标签,内容等信息
	 */
	public String newPost(String username, String password, Blog blog) {
		try {
			
			// Set up parameters required by newPost method
			Map<String, Object> post = new HashMap<String, Object>();
			post.put("title", blog.getTitle());// 标题
			post.put("categories", new String[]{blog.getCategory()});// 分类
			post.put("description", blog.getContent());// 内容
			Object[] params = new Object[] { blogId, username, password, post,Boolean.TRUE };

			// 发布新博文
			String result = (String) this.blogClient.execute("metaWeblog.newPost", params);
			logger.info("Created with blogid " + result+" on url:"+blogUrl);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("fail newPost:"+blog.getTitle()+" username:"+username+" url:"+blogUrl,e);
		}
	}

	/*
	 * 此方法用于获取博客网站支持的日志分类,根据xml-rpc定义,获取回来的对象实际上是一个Object数组
	 * 每个数组里面包含的是一个HashMap,这个HashMap中存储的是blogcategory的信息
	 */
	public Object[] getCategories(String username, String password)
			throws MalformedURLException, XmlRpcException {
		Object[] params = new Object[] { blogId, username, password };
		Object[] resultObj = (Object[]) this.blogClient.execute("metaWeblog.getCategories", params);
		return resultObj;
	}

	/*
	 * 打印博客支持的分类信息
	 */
	public void printBlogCategory(Object[] blogCategory) {
		if (blogCategory != null) {
			System.out.println("开始打印博客支持的分类");
			for (Object blogCategoryItem : blogCategory) {
				printBlogCategoryItem((HashMap<String, String>) blogCategoryItem);
				System.out
						.println("-------------------------------------------");
			}
		}
	}

	/*
	 * 打印每个blogCategoryItem信息
	 * 每个blogCategoryItem由categoryid,title,htmlUrl,description,rssUrl构成
	 * 至少cnblogs的博客文章分类是如此
	 */
	private void printBlogCategoryItem(HashMap<String, String> blogItem) {
		if (blogItem != null & blogItem.isEmpty() == false) {
			for (String key : blogItem.keySet()) {
				System.out.println(key + ":" + blogItem.get(key));
			}
		} else {
			System.out.println("blogItem is empty!");
		}

	}

	/*
	 * 获取博客支持的所有博文分类名,返回的是一个String数组
	 */
	public String[] getBlogCategoryTitle(Object[] blogCategory) {
		if (blogCategory != null) {
			ArrayList<String> categoryTitleList = new ArrayList<String>(20);
			for (Object categoryItem : blogCategory) {
				categoryTitleList.add((String) ((HashMap) categoryItem)
						.get("title"));
			}
			return (String[]) categoryTitleList.toArray();
		}
		return null;
	}
}