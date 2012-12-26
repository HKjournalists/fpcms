package com.fpcms.common.cache;

import java.util.Map;

public class CacheUtil {
	private static Cache cache = new MapBackendCache(100000);

	public static Cache getCache() {
		return cache;
	}

	public static void set(String key, Object value, int expiration) {
		getCache().set(key, value, expiration);
	}

	public static Object get(String key) {
		return getCache().get(key);
	}

	public static <T> Object get(String key,int expiration,ValueCallback<T> callback) {
		return get(key,expiration,callback);
	}
	
	public static Map<String, Object> get(String[] keys) {
		return getCache().get(keys);
	}

	public static long incr(String key, int by) {
		return getCache().incr(key, by);
	}

	public static long decr(String key, int by) {
		return getCache().decr(key, by);
	}

	public static void clear() {
		getCache().clear();
	}

	public static void delete(String key) {
		getCache().delete(key);
	}

	public static void stop() {
		getCache().stop();
	}

	public static interface ValueCallback <T>{
		public T create(String key);
	}
}
