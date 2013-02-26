package com.fpcms.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A tool convert Traditional Chinese character to Simplified or vice versa. The conversion is base on Unicode,
 * it means that if the character source is other encoding (e.g. gbk or gb2313) have to convert to Unicode 
 * before performing conversion 
 * 
 * @author Joe Choi
 */

public class JChineseConvertor {
	/**
	 * Get a JChineseConvertor instance. 
	 * <br><br>
	 * Actually, JChineseConvertor just load the Conversion Table from disk once
	 * while the first JChineseConvertor be instanced, and sequential call of the method will get a same instance
	 * 
	 * @exception IOException if the conversion table cannot be loaded
	 */
	public static JChineseConvertor getInstance() {
		if(convertor == null) convertor = new JChineseConvertor();
		return(convertor);
	}
	
	/**
	 * Convert all the characters of given String from Traditional Chinese to Simplified 
	 */
	public String t2s(final String s) {
		char[] cs = new char[s.length()];
		for(int i=0; i<s.length(); i++) cs[i] = t2s(s.charAt(i));
		return(new String(cs));
	}
	
	/**
	 * Convert all the characters of given String from Simplified Chinese to Traditional
	 */
	public String s2t(final String s) {
		char[] cs = new char[s.length()];
		for(int i=0 ; i<s.length(); i++) cs[i] = s2t(s.charAt(i));
		return(new String(cs));
	}
	
	/**
	 * Convert a character from Traditional Chinese to Simplified.
	 * <br>
	 * if the given character cannot be converted, simple return the given character
	 */	
	public Character t2s(final char c) {
		if(ts.get(c) == null ) return(c);
		return(ts.get(c));
	}
	
	/**
	 * Convert a character from Simplified Chinese to Traditional
	 * <br>
	 * if the given character cannot be converted, simple return the given character
	 */
	public Character s2t(final char c) {
		if(st.get(c) == null) return(c);
		return(st.get(c));
	}

	private List<Character> loadTable()  {
		String file = "/big5_to_gbk_mapping.txt";
		try {
			List<Character> cs = loadChar(file, "UTF-8");		
			if((cs.size() % 2) != 0) throw new RuntimeException("The conversion table may be damaged or not exists");
			else return(cs);
		}catch(IOException e) {
			throw new RuntimeException("IOException on load file:"+file,e);
		}
	}
		
	private JChineseConvertor() {
		List<Character> cs = loadTable();
		ts = new HashMap<Character, Character>();
		st = new HashMap<Character, Character>();
		
		for(int i=0; i<cs.size(); i=i+2) {
			ts.put(cs.get(i), cs.get(i+1));
			st.put(cs.get(i+1), cs.get(i));
		}
	}

	private List<Character> loadChar(String file, String charset) throws IOException {
		List<Character> content = new ArrayList<Character>();
		BufferedReader in = new BufferedReader(
			new InputStreamReader(this.getClass().getResourceAsStream(file), charset)
		);
		int c;
		while((c = in.read()) != -1) content.add((char) c);
		in.close();
		return(content);
	}
	
	private Map<Character, Character> ts;
	private Map<Character, Character> st;
	private static JChineseConvertor convertor;
}