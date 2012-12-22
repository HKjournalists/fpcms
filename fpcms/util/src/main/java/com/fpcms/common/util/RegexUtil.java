package com.fpcms.common.util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 姝ｅ垯琛ㄨ揪寮忓伐鍏风被
 * @author badqiu
 *
 */
public class RegexUtil {
	
	private static WeakHashMap<String, Pattern> regexCache = new WeakHashMap<String, Pattern>();
	
	/**
	 * 閫氳繃姝ｅ垯鎵惧埌鐩稿尮閰嶇殑瀛楃涓�
	 * 
	 * @param input 杈撳叆瀛楃涓�
	 * @param regex 姝ｅ垯琛ㄨ揪寮�
	 * @param regexGroup 姝ｅ垯琛ㄨ揪寮忕殑group
	 * @return 杩斿洖鍖归厤姝ｅ垯琛ㄨ揪寮忕殑group瀛楃涓�
	 */
	public static String findByRegexGroup(String input,String regex,int regexGroup) {
		if(input == null) return null;
		if(regex == null || regex.isEmpty()) throw new IllegalArgumentException("'regex' must be not null");
		Pattern p = getPatternFromCache(regex);
		
		Matcher m = p.matcher(input);
		if(m.find()) {
			return m.group(regexGroup);
		}
		return null;
	}

	/**
	 * 閫氳繃姝ｅ垯鎵惧埌鐩稿尮閰嶇殑瀛楃涓�
	 * 
	 * @param input 杈撳叆瀛楃涓�
	 * @param regex 姝ｅ垯琛ㄨ揪寮�
	 * @param regexGroup 姝ｅ垯琛ㄨ揪寮忕殑group
	 * @return 杩斿洖鍖归厤姝ｅ垯琛ㄨ揪寮忕殑group瀛楃涓�
	 */
	public static Set<String> findAllByRegexGroup(String input,String regex,int regexGroup) {
		if(input == null) return Collections.EMPTY_SET;
		if(regex == null || regex.isEmpty()) throw new IllegalArgumentException("'regex' must be not null");
		Pattern p = getPatternFromCache(regex);
		
		Matcher m = p.matcher(input);
		Set<String> result = new LinkedHashSet();
		while(m.find()) {
			result.add(m.group(regexGroup));
		}
		return result;
	}
	
	/**
	 * 姝ｅ垯姝ｇ‘寮廲ache
	 * @param regex
	 * @return
	 */
	public static Pattern getPatternFromCache(String regex) {
		Pattern p = regexCache.get(regex);
		if(p == null) {
			p = Pattern.compile(regex);
			synchronized (regexCache) {
				regexCache.put(regex, p);
			}
		}
		return p;
	}
	
}