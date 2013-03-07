package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class TagsTest extends Assert{
	
	@Test
	public void test_containOne() {
		assertTrue(Tags.containOne("abc,123,diy", "abc"));
		assertTrue(Tags.containOne("abc,123,diy", "123"));
		assertTrue(Tags.containOne("abc", "abc,123"));
		
		assertFalse(Tags.containOne("", "abc"));
		assertFalse(Tags.containOne("abc,123,diy", "abcd"));
	}
	
}
