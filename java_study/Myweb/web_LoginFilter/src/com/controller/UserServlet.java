package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.model.UserModel;


/**
 * 用户登录的Servlet
 */
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// 接收数据
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// 封装数据
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			// 处理数据
			UserModel userModel = new UserModel();
			User existUser = userModel.login(user);
			// 根据结果跳转页面
			if(existUser == null){
				//登录失败
				request.setAttribute("msg", "用户名或密码错误。");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				//登录成功
				request.getSession().setAttribute("existUser", existUser);
				response.sendRedirect(request.getContextPath()+"/jsp/success.jsp");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
