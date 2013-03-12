package com.fpcms.common.util;

import org.apache.commons.lang.StringUtils;

public class TextLangUtil {

	// GENERAL_PUNCTUATION 判断中文的“号

	// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号

	// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	/**
	 * 得到中文字符数量
	 * @param str
	 * @return
	 */
	public static int chineseCount(String str) {
		int count = 0;
		for(int i = 0; i < str.length(); i ++) {
			if(isChinese(str.charAt(i))) {
				count ++;
			}
		}
		return count;
	}
	
	public static int chineseCountPercent(String str) {
		if(StringUtils.isBlank(str)) {
			return 0;
		}
		float chineseCountPercent = TextLangUtil.chineseCount(str) / (float)StringUtils.remove(str," ").length();
		return (int)(chineseCountPercent * 100);
	}

	public static boolean hasChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == true) {
				return true;
			}
		}
		return false;
	}


}