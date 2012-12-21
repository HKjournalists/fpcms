package com.fpcms.common.util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class SimpleNetUtil {
	static SimpleHttpInvokerRequestExecutor executor = new SimpleHttpInvokerRequestExecutor();
	
	public static String readUrl(String url,Map parameters) {
		String parametersString = buildParameters(parameters).toString();
		return readUrl(url, parametersString);
	}

	public static String readUrl(String url,String parametersString) {
		try {
			return IOUtils.toString(executor.executeRequest(url, parametersString),"UTf-8");
		}catch(Exception e) {
			throw new RuntimeException("readUrl error,url:"+url+" parameters:"+parametersString,e);
		}
	}

	private static StringBuilder buildParameters(Map parameters) {
		StringBuilder parameter = new StringBuilder();
		Set<Map.Entry> entrySet = parameters.entrySet();
		for(Map.Entry entry : entrySet) {
			parameter.append(entry.getKey()+"="+entry.getValue()+"&");
		}
		return parameter;
	}
}
