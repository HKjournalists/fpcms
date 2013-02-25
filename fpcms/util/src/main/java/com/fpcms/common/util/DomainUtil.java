package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;

public class DomainUtil {

	public static boolean isMainSite(String site) {
		if(StringUtils.isBlank(site)) {
			return false;
		}
		return site.startsWith("www.") || StringUtils.countMatches(site, ".") == 1;
	}
	
}
