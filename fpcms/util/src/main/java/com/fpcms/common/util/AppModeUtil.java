package com.fpcms.common.util;

public class AppModeUtil {

	public static boolean isDevAppMode() {
		return "dev".equals(System.getProperty("appMode"));
	}
	
	public static void setAppMode(String mode) {
		System.setProperty("appMode", mode);
	}
	
}
