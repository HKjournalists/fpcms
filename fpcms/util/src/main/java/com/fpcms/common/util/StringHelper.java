package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringHelper {

	public static List<String> removeEmptyLines(List<String> lines) {
		if(lines == null) return null;
		
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < lines.size(); i++) {
			if(StringUtils.isNotBlank(lines.get(i))) {
				result.add(lines.get(i));
			}
		}
		return result;
	}
	
}
