package com.fpcms.common.cache;

import java.util.Map;

public class CacheUtil {
	private static Cache cache = new MapBackendCache(100000);
	public static Cache getCache() {
		return cache;
	}
	public static void add(String key, Object value, int expiration) {
		getCache().add(key, value, expiration);
	}
	public static boolean safeAdd(String key, Object value, int expiration) {
		return getCache().safeAdd(key, value, expiration);
	}
	public static void set(String key, Object value, int expiration) {
		getCache().set(key, value, expiration);
	}
	public static boolean safeSet(String key, Object value, int expiration) {
		return getCache().safeSet(key, value, expiration);
	}
	public static void replace(String key, Object value, int expiration) {
		getCache().replace(key, value, expiration);
	}
	public static boolean safeReplace(String key, Object value, int expiration) {
		return getCache().safeReplace(key, value, expiration);
	}
	public static Object get(String key) {
		return getCache().get(key);
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
	public static boolean safeDelete(String key) {
		return getCache().safeDelete(key);
	}
	public static void stop() {
		getCache().stop();
	}
	
}
