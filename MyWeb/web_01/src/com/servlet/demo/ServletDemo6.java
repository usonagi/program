package com.servlet.demo;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ServletConfig对象的API的演示
 * ServletContext对象的作用 其一：获取web项目的信息
 */
public class ServletDemo6 extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=====ServletConfig的API演示=====
		//1、获得初始化参数的API
		//获得ServletConfig对象
		ServletConfig config = this.getServletConfig();
		String username = config.getInitParameter("username");
		String password = config.getInitParameter("password");
		System.out.println(username+ "  " +password);
		
		//2、获得所有初始化参数的名称
		Enumeration<String> names = config.getInitParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(name + " " + value);
		}
		
		//3、获得Servlet的名称
		String servletName = config.getServletName();
		System.out.println(servletName);
		
		
		//=====ServletContext的作用演示====
		//1、获取文件的MIME类型
		//获得ServletContext
		ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType("xx.txt");
		System.out.println(mimeType);
		
		//2、获得请求路径的工程名
		String path = context.getContextPath();
		System.out.println(path);
		
		//3、获得全局的初始化参数
		String Uname = context.getInitParameter("UserName");
		String Pwd = context.getInitParameter("PassWord");
		System.out.println(Uname + " " + Pwd);
		Enumeration<String> enumNames = context.getInitParameterNames();
		while(enumNames.hasMoreElements()){
			String name = enumNames.nextElement();
			String value = context.getInitParameter(name);
			System.out.println(name+ " " +value);
		}
		
		//ServletContext作为域对象存取数据	在Demo7中初始化  先访问7再访问6
		String name = (String) this.getServletContext().getAttribute("name");
		System.out.println();
		System.out.println("姓名："+ name);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
