package com.cookie;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录用户上次访问时间
 */
public class VisitServlet extends HttpServlet {
	
	/*
	 * 问题：若用户访问之后关闭浏览器重新访问则会被当成第一次访问
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 用户第一次访问：显示欢迎信息，记录当前访问时间，存到cookie，回写到浏览器
		 * 不是第一次访问：从cookie中得到上次时间，记录当前访问时间，存入cookie，回写到浏览器
		 */
		//从cookie数组中获取指定名称的cookie，由此判断是否是第一次访问
		//获得从浏览器带来的所有cookie
		Cookie[] cookies = request.getCookies();
		//从数组中找到指定名称的Cookie
		Cookie cookie = CookieUtils.findCookie(cookies, "lastVisit");
		//判断是否是第一次访问
		if(cookie == null){
			//第一次访问，显示欢迎信息
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<h1>您好，欢迎使用本网站！<h1>");
		}else{
			//不是第一次访问,获得上次访问时间
			response.setContentType("text/html;charset=UTF-8");
			String lastTime = cookie.getValue(); 
			response.getWriter().println("<h1>您好，您的上次访问时间为: "+lastTime+"<h1>");
		}
		//记录当前时间,存入到cookie，回写到浏览器
		Date date = new Date();
		//存入Cookie
		Cookie ck = new Cookie("lastVisit",date.toLocaleString());
		//设置Cookie的有效路径
		ck.setPath("/web_02");
		//设置Cookie的有效时长为1小时
		ck.setMaxAge(60 * 60);
		//回写到浏览器
		response.addCookie(ck);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
