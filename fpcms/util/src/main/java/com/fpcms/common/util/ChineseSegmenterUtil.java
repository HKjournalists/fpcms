package com.fpcms.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 中文分词工具类
 * 分词工具使用: http://code.google.com/p/ik-analyzer/
 * 
 * @author badqiu
 * 
 */
public class ChineseSegmenterUtil {

	/**
	 * 解析得到每个词的次数，返回 Map<词,出现次数>
	 * @param reader
	 * @param useSmart
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Integer> segmenteForTokenCount(Reader reader,boolean useSmart) throws IOException {
		IKSegmenter ik = new IKSegmenter(reader, useSmart);// 当为true时，分词器进行最大词长切分
		Lexeme lexeme = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		while ((lexeme = ik.next()) != null) {
			Integer count = map.get(lexeme.getLexemeText());
			if(count == null) {
				count = 0;
			}
			count++;
			map.put(lexeme.getLexemeText(),count);
		}
		return map;
	}
	
	public static Map<String,Integer> segmenteForTokenCount(String string) {
		return segmenteForTokenCount(string,false);
	}
	/**
	 * 解析得到每个词的次数，返回 Map<词,出现次数>
	 * @param reader
	 * @param useSmart
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Integer> segmenteForTokenCount(String string,boolean useSmart) {
		try {
			return segmenteForTokenCount(new StringReader(string),useSmart);
		} catch (Exception e) {
			throw new RuntimeException("segmente error,string:"+string,e);
		}
	}
	
	/**
	 * 将Map<token,count>转换为List<TokenList>并排序回去，序列是降序
	 * @param map
	 * @return
	 */
	public static List<TokenCount> toSortedTokenCountList(Map<String,Integer> tokenCountMap) {
		List<TokenCount> tokens = new ArrayList<TokenCount>();
		for(Map.Entry<String, Integer> entry : tokenCountMap.entrySet()) {
			tokens.add(new TokenCount(entry.getKey(),entry.getValue()));
		}
		Collections.sort(tokens);
		return tokens;
	}
	
	public static class TokenCount implements Comparable<TokenCount>{
		private String token;
		private int count;
		
		public TokenCount() {
		}
		
		public TokenCount(String token, int count) {
			super();
			this.token = token;
			this.count = count;
		}
		
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		@Override
		public String toString() {
			return "TokenCount [token=" + token + ", count=" + count + "]";
		}

		@Override
		public int compareTo(TokenCount o) {
			if(count == o.count) {
				return 0;
			}
			if(count > o.getCount()) {
				return -1;
			}else {
				return 1;
			}
		}
		
	}
}
