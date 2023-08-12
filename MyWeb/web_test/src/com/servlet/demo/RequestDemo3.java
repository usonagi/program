package com.servlet.demo;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request接收中文数据乱码问题
 */
public class RequestDemo3 extends HttpServlet {
	
	/*
	 * 演示get方式处理中文乱码
	 * 原因:get方式提交的数据在请求行的url后面，在地址栏进行过URL编码
	 * 解决方案:将存入request缓冲区的数据以ISO-8859-1的方式获取，以UTF-8的方式解码
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收数据
		String name = request.getParameter("name");
		/*String encode = URLEncoder.encode(name, "ISO-8859-1");
		String decode = URLDecoder.decode(encode, "UTF-8");
		System.out.println("姓名:"+decode);*/
		
		String value = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("姓名:"+value);
	}

	/*
	 * 演示post处理中文乱码
	 * 原因:post方式提交的数据在请求体中，request对象接收数据后放入request缓冲区，该缓冲区不支持中文。
	 * 解决方案:修改request缓冲区的编码
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置缓冲区的编码
		request.setCharacterEncoding("UTF-8");
		//接收数据
		String name = request.getParameter("name");
		System.out.println("姓名:"+name);
		
	}

}
