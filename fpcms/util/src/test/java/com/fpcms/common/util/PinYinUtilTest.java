package com.fpcms.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.util.ResourceUtils;
//
//import com.fpcms.common.util.SpringContext;
//import com.fpcms.model.CmsSite;
//import com.fpcms.service.CmsSiteService;

public class PinYinUtilTest {
	
	@Test
	public void test() throws Exception {
		Set<String> citys = getAllCitys();
		
		Map pinyinMap = new HashMap();
		Map cityMap = new HashMap();
		for(String city : citys) {
			String firstSpell = PinyinUtil.cn2FirstSpell(city);
			pinyinMap.put(firstSpell, city);
			cityMap.put(city, firstSpell);
			System.out.println(firstSpell+" ==> " + city);
		}
		Assert.assertTrue(cityMap.toString(),pinyinMap.size()<cityMap.size());
	}
	
//	@Test 
//	public void test_create_site_by_city_list() throws FileNotFoundException, IOException {
//		CmsSiteService service = SpringContext.getBean(CmsSiteService.class);
//		Set<String> citys = getAllCitys();
//		for(String city : citys) {
//			int count = 0;
//			String cityPinyin = PinyinUtil.cn2FirstSpell(city);
//			String domain = ".aaafaipiao.com";
//			for(int i = 0; i < 10; i++) {
//				try {
//					String finalCityPinyin = i == 0 ? cityPinyin : cityPinyin+i;
//					service.create(newCmsSite(city, finalCityPinyin,domain));
//					System.out.println("create city:"+city+" => "+finalCityPinyin);
//					break;
//				}catch(org.springframework.dao.DuplicateKeyException e) {
//				}
//			}
//			
//		}
//	}
//
//	private CmsSite newCmsSite(String city, String cityPinyin,String domain) {
//		CmsSite cmsSite = new CmsSite();
//		cmsSite.setSiteDomain(cityPinyin+domain);
//		cmsSite.setSiteName(city);
//		cmsSite.setSiteKeyword(city+"开发票 "+city+"代开发票");
//		cmsSite.setSiteCity(cityPinyin);
//		cmsSite.setRemarks(null);
//		return cmsSite;
//	}

	private Set<String> getAllCitys() throws FileNotFoundException, IOException {
		File file = ResourceUtils.getFile("classpath:city_gdp_top300.txt");
		FileReader reader = new FileReader(file);
		Set<String> citys = new LinkedHashSet<String>();
		for(String line : IOUtils.readLines(reader)) {
			String[] array = line.split("\\s+");
			citys.add(array[2]);
			citys.add(array[3]);
		}
		reader.close();
		return citys;
	}
}
