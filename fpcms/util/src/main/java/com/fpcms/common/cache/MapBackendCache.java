package com.fpcms.common.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fpcms.common.cache.CacheUtil.ValueCallback;
import com.fpcms.common.util.AppModeUtil;

public class MapBackendCache implements Cache {
	LRUMap map = null;
	
	MapBackendCache(int maxEntries) {
		super();
		this.map = new LRUMap(1000,maxEntries);
	}

	public class Value {
		long gmtCreate = System.currentTimeMillis();
		long expiration;
		Object value;
		public Value(Object value, int expiration) {
			this.value = value;
			this.expiration = expiration * 1000;
		}
		public boolean isExpiration() {
			return (gmtCreate + expiration) <= System.currentTimeMillis();
		}
	}
	
	public void clear() {
		map.clear();
	}

	public long decr(String key, int by) {
		Long v = (Long)get(key);
		if(v == null) {
			v = -(long)by;
		}else {
			v = v - by;
		}
		map.put(key, v);
		return v;
	}

	public boolean delete(String key) {
		return map.remove(key) != null;
	}

	public Object get(String key) {
		if(AppModeUtil.isDevAppMode()) {
			return null;
		}
		Value value = (Value)map.get(key);
		if(value == null) return null;
		if(value.isExpiration()) {
			delete(key);
			return null;
		}else {
			return value.value;
		}
	}

	public Map<String, Object> get(String[] keys) {
		Map<String,Object> result = new HashMap();
		for(String key : keys) {
			Object value = get(key);
			result.put(key, value);
		}
		return result;
	}

	public long incr(String key, int by) {
		Long v = (Long)get(key);
		if(v == null) {
			v = (long)by;
		}else {
			v = v + by;
		}
		map.put(key, v);
		return v;
	}

	public boolean set(String key, Object value, int expiration) {
		if(AppModeUtil.isDevAppMode()) {
			return true;
		}
		map.put(key, new Value(value,expiration));
		return true;
	}
	
	public <T> T get(String key,int expiration,ValueCallback<T> callback) {
		Object value = get(key);
		if(value == null) {
			value = callback.create(key);
			set(key, value, expiration);
		}
		return (T)value;
	}

	public void stop() {
		map.clear();
	}
	
	public class LRUMap<K,V> extends LinkedHashMap<K,V>
	{
	    protected final int _maxEntries;
	    
	    public LRUMap(int initialEntries, int maxEntries)
	    {
	        super(initialEntries, 0.8f, true);
	        _maxEntries = maxEntries;
	    }

	    @Override
	    protected boolean removeEldestEntry(Map.Entry<K,V> eldest)
	    {
	        return size() > _maxEntries;
	    }

	}

}
