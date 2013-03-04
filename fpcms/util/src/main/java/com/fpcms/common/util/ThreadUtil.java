package com.fpcms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadUtil {
	private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			logger.error("InterruptedException",e);
			return;
		}
	}
}
