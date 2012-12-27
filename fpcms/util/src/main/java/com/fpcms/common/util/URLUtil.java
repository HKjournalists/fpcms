package com.fpcms.common.util;

import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtil {
	static Logger logger = LoggerFactory.getLogger(URLUtil.class);
	static String LOCALHOST = "localhost";
	public static String getHostSite(String url) {
		if(StringUtils.isBlank(url)) {
			return LOCALHOST;
		}
		try {
			String host = new URL(url).getHost();
			if(host.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
				return LOCALHOST;
			}
			return host;
		}catch(Exception e) {
			logger.error("getHostSite error,url:"+url,e);
			return LOCALHOST;
		}
	}
	
}
