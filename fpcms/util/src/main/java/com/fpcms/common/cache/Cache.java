package com.fpcms.common.cache;

import java.util.Map;

/**
 * A cache interface
 * 
 * expiration的时间单位为秒
 * @see CacheHolder.cache
 */
public interface Cache {

    /** 
     * 设置cache值
     * */
    public boolean set(String key, Object value, int expiration);

    /**
     * 根据key得到一个元素
     * @param key
     * @return
     */
    public Object get(String key);
    /**
     * 批量查找元素
     * @param key
     * @return
     */
    public Map<String, Object> get(String[] keys);
    /**
     * 递增元素的值
     * @param key
     * @param by
     * @return
     */
    public long incr(String key, int by);
    /**
     * 递减的元素的值
     * @param key
     * @param by
     * @return
     */
    public long decr(String key, int by);

    public void clear();
    /**
     * 从缓存中删除一个元素
     * @param key
     */
    public boolean delete(String key);

    public <T> T get(String key,int expiration,ValueCallback<T> callback);
    
    public void stop();
}
