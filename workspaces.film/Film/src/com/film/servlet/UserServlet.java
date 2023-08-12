package com.film.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.film.dao.UserDAO;
import com.google.gson.Gson;

//访问映射地址
@WebServlet("/user.action")
public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将请求对象和相应对象的编码集设置为utf_8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//从前端传入一个op属性 根据该值判断操作
		String op = request.getParameter("op");
		//字符串比较
		if("register".equals(op)){
			//注册
			doRegister(request,response);
		}else if("login".equals(op)){
			//登录
			doLogin(request,response);
		}else if("checkEmail".equals(op)){
			doCheckEmail(request,response);
		}
	}
	
	//检查邮箱
	protected void doCheckEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取 密码
		String email = request.getParameter("email");
		Map<String,Object> user;
		try {
			user = UserDAO.findByEmail(email);
			int result = 0;
			if(null != user){
				result = 1;
			}
			Gson gson = new Gson();
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("code", result);
			String info = gson.toJson(jsonMap);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//注册
	protected void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从页面获取用户名、密码、邮箱
		String email = request.getParameter("email");
		String upwd = request.getParameter("upwd");
		String uname = request.getParameter("uname");
		//调用UserDAO中的方法
		try {
			int result = UserDAO.register(uname, email, upwd);
			//返回json格式字符串给外界
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("code", result);
			//创建gson对象
			Gson gson = new Gson();
			String info = gson.toJson(jsonMap);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//登录
	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String upwd = request.getParameter("upwd");
		//调用登录方法
		Map<String, Object> user;
		try {
			user = UserDAO.login(email, upwd);
			int result = 0;
			if(user != null){
				//登录成功
				result = 1;
				//将登录的用户数据存储到HttpSession
				request.getSession().setAttribute("user", user);
				//多个页面可以共享此对象的数据
			}
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("code", result);
			//创建gson对象
			Gson gson = new Gson();
			String info = gson.toJson(jsonMap);
			response.getWriter().print(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response); //所有的请求最终处理都在doGet中
	}

}
