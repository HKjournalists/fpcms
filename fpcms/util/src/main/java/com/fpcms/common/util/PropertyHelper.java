package com.fpcms.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertyHelper {
	
	public static Properties loadFromString(String string) {
		if(StringUtils.isBlank(string)) {
			return new Properties();
		}
		
		Properties p = new Properties();
		try {
			p.load(new StringReader(string));
			return p;
		} catch (IOException e) {
			throw new RuntimeException("cannot load properties from string:"+string);
		}
	}
	
	public static String toString(Properties props) {
		if(props.isEmpty()) {
			return null;
		}
		try {
			StringWriter writer = new StringWriter();
			props.store(writer, null);
			return writer.toString();
		} catch (IOException e) {
			throw new RuntimeException("cannot store properties:"+props);
		}
	}
}
