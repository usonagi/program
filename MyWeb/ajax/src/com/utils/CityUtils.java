package com.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/*
 * ��ȡ������Ϣ�Ĺ�����
 */
public class CityUtils {
	private static final Map<String,String> cities = new HashMap<String,String>();
	static{
		cities.put("������", "������,����,�������");
		cities.put("����", "����,����,��ƽ");
		cities.put("����", "����,����,��«��");
	}
	
	//��ȡʡ����Ϣ
	public static String getProvince(){
		Set<String> set = cities.keySet();
		String province = "";
		for(String p:set){
			province += p+",";
		}
		return province.substring(0,province.length()-1);
	}
	
	//��ȡ������Ϣ
	public static String getCity(String provinceName){
		return cities.get(provinceName);
	}
	
	/*public static void main(String[] args) {
		System.out.println(getProvince());
		System.out.println(getCity("����"));
	}*/
}
