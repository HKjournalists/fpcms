package com.fpcms.common.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class UnuseKeywordsUtilTest extends Assert {

	@Test
	public void test_getUnuseKeywords() throws IOException {
		Set<String> unuseKeywords = UnuseKeywordsUtil.getUnuseKeywords();
		assertFalse(unuseKeywords.isEmpty());
		
		System.out.println("-------- done ----------" + unuseKeywords.size());
//		System.out.println(unuseKeywords.size() + " " + unuseKeywords);
		
//		System.out.println("\n\n\n");
//		int i = 0;
//		PrintWriter fileOut = new PrintWriter(new FileWriter("/tmp/unuseKeywords.txt"));
//		for(String str : unuseKeywords) {
//			print(str,fileOut);
//			i++;
//			if(i % 30 == 0) {
//				System.out.println();
//				fileOut.println();
//			}
//		}
//		fileOut.close();
	}

	private void print(String str,PrintWriter fileOut) {
		System.out.print(str+",");
		fileOut.print(str+",");
	}
	
}
