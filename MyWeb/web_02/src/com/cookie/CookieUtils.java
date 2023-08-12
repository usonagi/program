package com.cookie;

import javax.servlet.http.Cookie;

/*
 * Cookie�Ĺ�����
 */
public class CookieUtils {

	public static Cookie findCookie(Cookie[] cookies,String name){
		if(cookies == null ){	//�����û��Я��Cookie
			return null;
		}else{
			//��������Cookie�����ƣ��ж��Ƿ���ָ������һ��
			for(Cookie cookie : cookies) {
				if(name.equals(cookie.getName())){
					return cookie;
				}
			}
			return null;
		}
	}
	
}
