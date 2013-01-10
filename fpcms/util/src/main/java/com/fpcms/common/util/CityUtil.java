package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.duowan.common.util.ArrayUtils;


public class CityUtil {
	static List<City> cityList = null;
	
	public static synchronized List<City> getCityList() throws IOException{
		if(cityList == null) {
			List<Map> rows = readCityLines();
			regProvincePinyin(rows);
			regCityPinyin(rows);
			cityList = toCityList(rows);
		}
		return cityList;
	}
	
	/**
	 * 返回LinkedHashMap<province,provincePinyin>
	 */
	public static synchronized LinkedHashMap<String,String> getProvincePinyinMap() throws IOException{
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		for(City city : getCityList()) {
			map.put(city.getProvince(), city.getProvincePinyin());
		}
		return map;
	}
	
	/**
	 * 返回LinkedHashMap<city,cityPinyin>
	 */
	public static synchronized LinkedHashMap<String,String> getCityPinyinMap() throws IOException{
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		for(City city : getCityList()) {
			map.put(city.getCity(), city.getCityPinyin());
		}
		return map;
	}
	
	private static List<City> toCityList(List<Map> rows) {
		List<City> cityList = new ArrayList<City>();
		for(Map<String,String> map : rows) {
			int rank = Integer.parseInt(map.get("rank"));
			float gdp = Float.parseFloat(map.get("gdp"));
			String city = map.get("city");
			String province = map.get("province");
			String cityPinyin = map.get("cityPinyin");
			String provincePinyin = map.get("provincePinyin");
			City cityObj = new City(rank,(int)gdp,province,city,provincePinyin,cityPinyin);
			cityList.add(cityObj);
		}
		return cityList;
	}

	private static void regCityPinyin(List<Map> rows) {
		for(Map map : rows) {
			String city = (String)map.get("city");
			String cityPinyin = getPinyinCode(city);
			map.put("cityPinyin", cityPinyin);
		}
	}

	private static void regProvincePinyin(List<Map> rows) {
		for(Map map : rows) {
			String province = (String)map.get("province");
			String provincePinyin = getPinyinCode(province);
			map.put("provincePinyin", provincePinyin);
		}
	}

	private static List<Map> readCityLines()
			throws IOException {
		InputStream input = CityUtil.class.getResourceAsStream("/city_gdp_top300.txt");
		try {
			List<String> lines = IOUtils.readLines(input, "UTF-8");
			List<Map> result = new ArrayList();
			for(String line : lines) {
				if(StringUtils.isBlank(line)) continue;
				
				String[] array = line.split("[\\s　]+");
				Map<String,String> map = ArrayUtils.toMap(array, "rank","gdp","province","city");
				map.put("province", map.get("province").replaceAll("\\d", ""));
				map.put("city", map.get("city").replaceAll("\\d", ""));
				result.add(map);
			}
			return result;
		}finally {
			IOUtils.closeQuietly(input);
		}
	}
	
	static Map<String,String> codePinyinRegMap = new HashMap();
	private static String getPinyinCode(String city) {
		String pinyinCode = codePinyinRegMap.get(city);
		if(pinyinCode != null) {
			return pinyinCode;
		}
		
		pinyinCode = getPinyinCode0(city);
		codePinyinRegMap.put(city, pinyinCode);
		return pinyinCode;
	}

	static Map<String,Integer> pinyinRegCountMap = new HashMap();
	private static String getPinyinCode0(String city) {
		String pinyin = PinyinUtil.cn2FirstSpell(city);
		Integer regCount = pinyinRegCountMap.get(pinyin);
		if(regCount == null) {
			regCount = 0;
			pinyinRegCountMap.put(pinyin, regCount);
		}else {
			regCount++;
		}
		pinyinRegCountMap.put(pinyin, regCount);
		if(regCount == 0) {
			return pinyin;
		}else {
			return pinyin+regCount;
		}
	}
	
	public static class City{
		int rank;
		int gdp;
		String province;
		String city;
		String cityPinyin;
		String provincePinyin;
		public City(){
		}
		
		public City(int rank, int gdp, String province, String city,
				String provincePinyin,String cityPinyin) {
			super();
			this.rank = rank;
			this.gdp = gdp;
			this.province = province;
			this.city = city;
			this.cityPinyin = cityPinyin;
			this.provincePinyin = provincePinyin;
		}

		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public int getGdp() {
			return gdp;
		}
		public void setGdp(int gdp) {
			this.gdp = gdp;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCityPinyin() {
			return cityPinyin;
		}
		public void setCityPinyin(String cityPinyin) {
			this.cityPinyin = cityPinyin;
		}
		public String getProvincePinyin() {
			return provincePinyin;
		}
		public void setProvincePinyin(String provincePinyin) {
			this.provincePinyin = provincePinyin;
		}
		@Override
		public String toString() {
			return "City [rank=" + rank + ", gdp=" + gdp + ", city=" + city
					+ ", cityPinyin=" + cityPinyin + ", province=" + province
					+ ", provincePinyin=" + provincePinyin + "]";
		}
		
	}
	
}
