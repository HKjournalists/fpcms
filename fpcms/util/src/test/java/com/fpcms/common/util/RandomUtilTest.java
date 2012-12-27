package com.fpcms.common.util;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Assert;
import org.junit.Test;


public class RandomUtilTest extends Assert{

	@Test
	public void test_randomTrue() {
		int count = 0;
		for(int i = 0; i < 10000; i++) {
			if(RandomUtil.randomTrue(30)) {
				count ++;
			}
		}
		System.out.println(count);
		assertTrue(count < 10000 * 0.3 + 200);
	}
	
	@Test
	public void test_randomInt() {
		int count = 0;
		for(int i = 0; i < 100000; i++) {
			if(RandomUtils.nextInt(3) < 0) {
				count ++;
			}
		}
		System.out.println(count);
		assertTrue(count == 0);
	}
}
