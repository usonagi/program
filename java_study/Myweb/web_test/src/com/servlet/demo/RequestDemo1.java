package com.servlet.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request对象API演示
 */
public class RequestDemo1 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=====获取客户机的信息======
		//获得请求方式
		System.out.println("请求方式: "+request.getMethod());
		//获得客户机的IP地址	使用192.168.1.6:8080/web_test/RequestDemo1访问
		System.out.println("IP地址: "+request.getRemoteAddr());
		//获得请求参数的字符串	使用？在地址栏拼接参数
		System.out.println("请求参数的字符串:"+request.getQueryString());
		//获得请求路径的URI和URL
		System.out.println("请求路径的URL: "+request.getRequestURL());
		System.out.println("请求路径的URI: "+request.getRequestURI());
		
		//=====获得请求头的信息======
		System.out.println("获得客户机浏览器的类型: "+request.getHeader("User-Agent"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
