package com.fpcms.common.cache;

public interface ValueCallback <T>{
	public T create(String key);
}