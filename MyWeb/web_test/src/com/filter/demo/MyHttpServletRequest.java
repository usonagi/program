package com.filter.demo;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/*
 * ��ǿ����
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		
		this.request = request;
		
	}
	
	//��ǿ�˷���
	@Override
	public String getParameter(String name) {
		String method = request.getMethod();
		if("GET".equals(method)){
			String value = super.getParameter(name);
			try {
				value = new String(value.getBytes("ISO-8859"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return value;
		}else if("POST".equals(method)){
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return super.getParameter(name);
	}
	
}
