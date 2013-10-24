package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest extends Assert{

	@Test
	public void test() {
		System.out.println(StringUtils.lowerCase("AAA"));
		
		assertEquals("aaa",StringUtils.lowerCase("AAA"));
	}
	
}
