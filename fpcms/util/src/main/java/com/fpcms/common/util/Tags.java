package com.fpcms.common.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class Tags {

	public static String toString(Set<String> tags) {
		if(tags == null || tags.isEmpty()) {
			return "";
		}
		
		return StringUtils.join(tags,",");
	}
	
	public static Set<String> fromString(String str) {
		if(StringUtils.isBlank(str)) {
			return new HashSet<String>();
		}
		String[] tags = StringUtils.split(str,",");
		return new HashSet<String>(Arrays.asList(tags));
	}
	
	public static boolean containOne(String expectedTags,String actualTags) {
		Set<String> exptecedTagSet = fromString(expectedTags);
		Set<String> actualTagSet = fromString(actualTags);
		for(String actualTag : actualTagSet) {
			if(exptecedTagSet.contains(actualTag)) {
				return true;
			}
		}
		return false;
	}
}
