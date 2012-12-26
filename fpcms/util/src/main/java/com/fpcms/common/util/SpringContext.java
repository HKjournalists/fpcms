package com.fpcms.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	static String[] locations = { "classpath*:spring/*.xml" };

	static ApplicationContext context;

	public synchronized static ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(locations);
		}
		return context;
	}

	public static Object getBean(String name) throws BeansException {
		return getContext().getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return getContext().getBean(requiredType);
	}

}
