package com.fpcms.common.util;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class UnuseKeywordsUtilTest extends Assert {

	@Test
	public void test() {
		Set unuseKeywords = UnuseKeywordsUtil.getUnuseKeywords();
		assertFalse(unuseKeywords.isEmpty());
		System.out.println(unuseKeywords);
	}
	
}
