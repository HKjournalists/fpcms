package com.fpcms.common.util;

import java.util.Map;

import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

import com.github.rapid.common.beanutils.PropertyUtils;

public class StrSubstitutorUtil {

	
	public static String strSubstitutor(String source,final Map map) {
		StrSubstitutor strSubstitutor = new StrSubstitutor(new StrLookup() {
			public String lookup(Object input,String key) {
				Object obj = PropertyUtils.getProperty(input, key);
				if(obj != null) {
					return String.valueOf(obj);
				}
				
				int indexOf = key.indexOf(".");
				if(indexOf >= 0) {
					String realKey = key.substring(0,indexOf);
					String subKey = key.substring(indexOf+1);
					obj = PropertyUtils.getProperty(input, realKey);
					return lookup(obj,subKey);
				}
				
				if(obj == null) {
					return "";
				}
				return String.valueOf(obj);
			}

			@Override
			public String lookup(String key) {
				return lookup(map,key);
			}
		});
		return strSubstitutor.replace(source);
	}
	
}
