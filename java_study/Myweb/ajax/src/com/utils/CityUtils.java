package com.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/*
 * 获取城市信息的工具类
 */
public class CityUtils {
	private static final Map<String,String> cities = new HashMap<String,String>();
	static{
		cities.put("黑龙江", "哈尔滨,大庆,齐齐哈尔");
		cities.put("吉林", "长春,吉林,四平");
		cities.put("辽宁", "沈阳,大连,葫芦岛");
	}
	
	//获取省份信息
	public static String getProvince(){
		Set<String> set = cities.keySet();
		String province = "";
		for(String p:set){
			province += p+",";
		}
		return province.substring(0,province.length()-1);
	}
	
	//获取城市信息
	public static String getCity(String provinceName){
		return cities.get(provinceName);
	}
	
	/*public static void main(String[] args) {
		System.out.println(getProvince());
		System.out.println(getCity("辽宁"));
	}*/
}
