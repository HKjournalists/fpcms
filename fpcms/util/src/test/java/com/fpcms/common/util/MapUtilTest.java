package com.fpcms.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class MapUtilTest extends Assert{

	@Test
	public void test() {
		Map map = new HashMap();
		Map defaultMap = new HashMap();
		
		map.put("username", "badqiu");
		map.put("pwd", "123456");
		
		defaultMap.put("username", "jane");
		defaultMap.put("blog", "www.blog.com/blog");
		
		MapUtil.mergeWithDefaultMap(map, defaultMap);
		
		assertEquals(map.get("username"),"badqiu");
		assertEquals(map.toString(),"{pwd=123456, username=badqiu, blog=www.blog.com/blog}");
	}
}
