package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用于案例《验证用户名是否可用》 的Servlet
 */
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、得到请求参数
		String username = request.getParameter("username");
		
		//2、判断用户名是否被占用
		String msg = "";
		if("xxx".equals(username)){
			msg = "<font color='red'>用户名已被使用</font>";
		}else{
			msg = "<font color='green'>用户名未被使用</font>";
		}
		
		//3、通过response获取输出流回写到浏览器
		response.setCharacterEncoding("UTF-8");  //防止乱码
		response.getWriter().write(msg);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
