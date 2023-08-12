package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jquery的ajax开发案例
 * 用于demo3.html 验证用户名是否可用的 Servlet
 */
public class LoadServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//1、得到用户名
		String username = request.getParameter("username");
		
		//2、判断用户名是否可以使用
		if("xxx".equals(username)){
			//用户名不可用
			response.getWriter().write("<font color='red'>用户名被占用</font>");
		}else{
			//用户名可用
			response.getWriter().write("<font color='green'>用户名可以使用</font>");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
