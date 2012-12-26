package com.fpcms.common.cache;

public class CacheManager {
	
	public static Cache createCache(int maxEntries) {
		return new MapBackendCache(maxEntries);
	}
	
}
