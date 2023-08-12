package com.servlet.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Response对象API的演示
 */
public class ServletDemo8 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应的状态码
		//response.setStatus(404);
		
		//完成重定向	可以使用response.sendRedirect()替换下面两行
		/*response.setStatus(302);
		//设置响应头
		response.setHeader("Location", "/web_test/ServletDemo2");*/
		
		//定时刷新
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("5秒以后跳转页面！");
		response.setHeader("Refresh", "5;url=/web_test/ServletDemo2");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
