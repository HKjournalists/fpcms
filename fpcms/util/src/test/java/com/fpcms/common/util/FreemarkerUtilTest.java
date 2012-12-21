package com.fpcms.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class FreemarkerUtilTest extends Assert{
	
	@Test
	public void test() {
		Map map = new HashMap();
		map.put("username", "badqiu");
		map.put("user.home", "/home/badqiu");
		
		Map map2 = new HashMap();
		map2.put("keyword", "好人");
		map.put("env", map2);
		
		Assert.assertEquals(StrSubstitutorUtil.strSubstitutor("${username}", map),"badqiu");
		Assert.assertEquals(StrSubstitutorUtil.strSubstitutor("${user.home}", map),"/home/badqiu");
		Assert.assertEquals(StrSubstitutorUtil.strSubstitutor("${env.keyword}", map),"好人");
		Assert.assertEquals(StrSubstitutorUtil.strSubstitutor("${env.keyword1}", map),"");
		
	}
}
