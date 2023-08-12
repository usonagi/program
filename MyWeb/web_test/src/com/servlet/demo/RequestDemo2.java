package com.servlet.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request对象API演示	  接收表单参数
 */
public class RequestDemo2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名:"+username+"    密码:"+password);
		//接收性别和籍贯
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		System.out.println("性别:"+sex+"\n"+"籍贯:"+city);
		//接收爱好
		String[] hobby = request.getParameterValues("hobby");
		System.out.println("爱好:"+Arrays.toString(hobby));
		//接收自我介绍
		String info = request.getParameter("info");
		System.out.println("自我介绍:"+info);
		
		System.out.println();
		
		//使用getParameterMap接收数据
		Map<String, String[]> map = request.getParameterMap();
		for (String key:map.keySet()) {
			String[] value = map.get(key);
			System.out.println(key+"  "+Arrays.toString(value));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
