package com.fpcms.tools;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

public class PinYinUtilTest {
	
	@Test
	public void test() throws Exception {
		File file = ResourceUtils.getFile("classpath:city.txt");
		FileReader reader = new FileReader(file);
		Set<String> citys = new LinkedHashSet<String>();
		for(String line : IOUtils.readLines(reader)) {
			String[] array = line.split("\\s+");
			for(String city : array) {
				citys.add(city);
			}
		}
		
		Map pinyinMap = new HashMap();
		Map cityMap = new HashMap();
		for(String city : citys) {
			String firstSpell = PinyinUtil.cn2FirstSpell(city);
			pinyinMap.put(firstSpell, city);
			cityMap.put(city, firstSpell);
			System.out.println(firstSpell+" ==> " + city);
		}
		Assert.assertEquals(cityMap.toString(),pinyinMap.size() , cityMap.size());
		reader.close();
	}
}
