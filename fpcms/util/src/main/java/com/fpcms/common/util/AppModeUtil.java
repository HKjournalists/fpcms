package com.fpcms.common.util;

public class AppModeUtil {

	public static boolean isDevAppMode() {
		return "dev".equals(System.getProperty("appMode"));
	}
	
	public static boolean isProdAppMode() {
		return "prod".equals(System.getProperty("appMode"));
	}
	
	public static boolean hasDevPassword() {
		return Constants.DEV_PASSWORD_VALUE.equals(System.getProperty(Constants.DEV_PASSWORD_KEY));
	}
	
	public static void setAppMode(String mode) {
		System.setProperty("appMode", mode);
		System.out.println("executed setAppMode:"+mode);
	}
	
}
