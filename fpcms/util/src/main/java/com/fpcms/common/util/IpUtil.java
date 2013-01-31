package com.fpcms.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtil {
	static Logger logger = LoggerFactory.getLogger(IpUtil.class);
	public static String getIp(String host)  {
		if(StringUtils.isBlank(host)) {
			return null;
		}
		try {
			InetAddress address = Inet4Address.getByName(host);
			return address.getHostAddress();
		}catch(UnknownHostException e) {
			logger.error("UnknownHostException,host:"+host,e);
			return null;
		}
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
