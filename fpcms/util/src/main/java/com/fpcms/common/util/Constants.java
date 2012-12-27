package com.fpcms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Constants {

	public static String ADMIN_LOGIN_USER = "ADMIN_LOGIN_USER";
	
	public static String CHANNED_CODE_NEWS = "news";
	
	public static String[] FAIPIAO_KEYWORDS = {"发票","增值税","税控","地税","国税","税务","发票税","纳税","纳税人","税法","税收"};
	
	public static String[] ATTACH_KEYWORD = {"最权威","最好"};
	
	public static String[] BAIDU_BUZZ_URLS = {
		"http://top.baidu.com/buzz.php?p=weekhotspot",
		"http://top.baidu.com/buzz.php?p=top10",
		"http://top.baidu.com/buzz?b=342",
		"http://top.baidu.com/buzz?b=344",
		"http://top.baidu.com/buzz.php?b=291",
		"http://top.sogou.com/hotword0.html",
		};

	public static String PROPERTY_DEFAULT_GROUP = "default";

	/**
	 * XML tree的root id
	 */
	public static long TREE_ROOT_ID = 1;
	
	/**
	 * 网络蜘蛛日志类
	 */
	public static Logger LOGGER_SPIDER = LoggerFactory.getLogger("spider");
	
	/**
	 * 性能类
	 */
	public static Logger LOGGER_DUMP_PROFILER = LoggerFactory.getLogger("dump-profiler");
	
	/**
	 * 开发密码，用于在开发环境下采用的密码
	 */
	public static String DEV_PASSWORD_KEY = "4a3f3Ff2as4dio27LFsffguiDKJAuiD43ui2vmoOnq4O2N0hHu9J6K5kg6e45ui5K5qwe9823Ulkuiad";
	public static String DEV_PASSWORD_VALUE = "4a3f3Ff2as4dio27LFsffguiDKJAuiD43ui2vmoOnq4O2N0hHu9J6K5kg6e45ui5K5qwe9823Ulkuiad";
}
