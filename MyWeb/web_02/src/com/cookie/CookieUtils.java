package com.cookie;

import javax.servlet.http.Cookie;

/*
 * Cookie的工具类
 */
public class CookieUtils {

	public static Cookie findCookie(Cookie[] cookies,String name){
		if(cookies == null ){	//浏览器没有携带Cookie
			return null;
		}else{
			//遍历所有Cookie的名称，判断是否与指定名称一致
			for(Cookie cookie : cookies) {
				if(name.equals(cookie.getName())){
					return cookie;
				}
			}
			return null;
		}
	}
	
}
