package com.fpcms.common.util;
/**
 * 搜索引擎搜索结果为空时抛出该异常
 */
public class EmptySearchResultException extends RuntimeException {

	private static final long serialVersionUID = 6140667547480005946L;

	public EmptySearchResultException() {
		super();
	}

	public EmptySearchResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptySearchResultException(String message) {
		super(message);
	}

	public EmptySearchResultException(Throwable cause) {
		super(cause);
	}
	
}
