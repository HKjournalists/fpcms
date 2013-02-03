package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtil {

	public static <T> List<T> getBeans(ApplicationContext applicationContext,Class<T> requiredType) {
		List<T> beans = new ArrayList<T>();
		String[] beanNames = applicationContext.getBeanNamesForType(requiredType);
		for(String beanName : beanNames) {
			T bean = (T)applicationContext.getBean(beanName,requiredType);
			beans.add(bean);
		}
		return beans;
	}
	
}
