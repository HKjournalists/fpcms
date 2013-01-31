package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class IpUtilTest extends Assert{
	
	@Test
	public void testGetIp() {
		String ip = IpUtil.getIp("news.163.com");
		System.out.println(ip);
		assertNotNull(ip);
		ip = IpUtil.getIp("wwww.aaafddsuiaipiao2323.com");
		System.out.println(ip);
		assertNull(ip);
	}
	
	@Test
	public void testGetIp_unknow_host() {
	}
}
