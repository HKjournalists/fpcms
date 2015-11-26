package com.fpcms.model;

import com.github.rapid.common.lang.enums.EnumBase;

public enum BlogRpcApiEnum implements EnumBase{
	WORD_PRESS("WordPress"),
	MOVABLE_TYPE("MovableType"),
	META_WEBLOG("MetaWeblog"),
	BLOGGER("Blogger"),
	ATOM("Atom"),
	;

	private final String code;
	
	BlogRpcApiEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return code;
	}
	

}
