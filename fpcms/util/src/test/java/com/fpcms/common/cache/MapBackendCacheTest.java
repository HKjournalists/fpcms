package com.fpcms.common.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MapBackendCacheTest extends Assert{

	MapBackendCache cache = new MapBackendCache(10);
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void test_size() throws InterruptedException {
		
		
		for(int i = 1; i < 100; i++) {
			cache.add(""+i, i, i);
			System.out.println("add "+i);
		}
		assertEquals(cache.map.size(),10);
	}
	
	@Test
	public void test_expir() throws InterruptedException {
		for(int i = 1; i < 10; i++) {
			cache.add(""+i, i, i);
			System.out.println("add "+i);
		}
		Thread.sleep(1000 + 100);
		assertEquals(null,cache.get("1"));
		assertEquals(2,cache.get("2"));
		
		Thread.sleep(1000 + 100);
		assertEquals(null,cache.get("2"));
	}
}
