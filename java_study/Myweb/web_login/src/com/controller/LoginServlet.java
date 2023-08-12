package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.User;
import com.model.UserModel;

/**
 * 登录的Servlet
 */
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1、接收数据
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println(username);
			
			//====一次性验证码的校验=====
			//接收验证码
			String checkcode1 = request.getParameter("checkcode");
			//从session中获取一次性验证码的值
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			//为了保证验证码只使用一次，应将session中的验证码清空
			request.getSession().removeAttribute("checkcode");
			//校验一次性验证码
			if(!checkcode1.equalsIgnoreCase(checkcode2)){
				request.setAttribute("msg", "验证码输入错误!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return ;
			}
			
			//2、封装数据
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			//3、处理数据
			UserModel  userModel = new UserModel();
			User existUser = userModel.login(user);
			
			//4、页面跳转
			if(existUser == null){
				//登录失败  向request域中保存错误信息
				request.setAttribute("msg", "用户名或密码错误!");
				//使用请求转发进行页面跳转
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				//保存用户信息,保存到会话中
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("existUser", existUser);
				//记住用户名
				String remember = request.getParameter("remember");
				if("true".equals(remember)){
					//已经勾选了记住用户名 
					Cookie cookie = new Cookie("remember",existUser.getUsername());
					//设置有效路径
					cookie.setPath("/web_login");
					//设置有效时长 24小时
					cookie.setMaxAge(60*60*24);
					//将Cookie回写到浏览器
					response.addCookie(cookie);
				}
				//重定向到成功页面
				response.sendRedirect("/web_login/success.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
