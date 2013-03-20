package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ClassUtils;

public class ClasspathUtil {
	
	public static String getStringResource(String name,String encoding) {
		InputStream input = ClassUtils.getDefaultClassLoader().getResourceAsStream(name);
		try {
			return IOUtils.toString(input,encoding);
		}catch(IOException e) {
			throw new RuntimeException("error get classpath resource by name:"+name,e);
		}finally {
			IOUtils.closeQuietly(input);
		}
	}
}
