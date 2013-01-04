package com.fpcms.common.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	private static Map<String,Cache> caches = new HashMap<String,Cache>();
	
	public static Cache createCache(Class cacheName,int maxEntries) {
		return createCache(cacheName.getSimpleName(),maxEntries);
	}

	public static Cache createCache(String cacheName,int maxEntries) {
		Cache cache = caches.get(cacheName);
		if(cache == null) {
			cache = new MapBackendCache(maxEntries);
			caches.put(cacheName,cache);
		}
		return cache;
	}
	
}
