package com.fpcms.common.blog_post.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.fpcms.common.blog_post.Blog;

public class XmlRpcBlogPoster {
	
	public void newPost(Blog blog) throws MalformedURLException, XmlRpcException {
		XmlRpcClient xmlrpc = new XmlRpcClient();
		XmlRpcClientConfigImpl pConfig = new XmlRpcClientConfigImpl();
		pConfig.setServerURL(new URL(""));
		xmlrpc.setConfig(pConfig );
		
		Vector params = new Vector();
		params.addElement("Tom");
		Object result = xmlrpc.execute("blogger.getUsersBlogs",params);
		
	}
}
