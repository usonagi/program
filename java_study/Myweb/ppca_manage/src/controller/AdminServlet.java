package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import domain.Admin;
import model.AdminModel;

/**
 * 处理Admin登录、退出功能的servlet
 */
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("login".equals(method)){
			login(request,response);
		}
		
		if("shutDown".equals(method)){
			shutDown(request,response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//登录操作
	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			//1、接收数据
			request.setCharacterEncoding("UTF-8");
			String ANO = request.getParameter("ANO");
			String APWD = request.getParameter("APWD");
			
			System.out.println("当前登录的帐号、密码: "+ANO+" "+APWD);
			
			//非空校验  可以自定义js函数实现
			/*if(StringUtils.isBlank(ANO)){
				request.setAttribute("NameErrorMsg", "用户名不能为空!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}
			if(StringUtils.isBlank(APWD)){
				request.setAttribute("PwdErrorMsg", "密码不能为空!");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}*/
			
			//====一次性验证码的校验=====
			//接收验证码
			String checkcode1 = request.getParameter("checkcode");
			//从session中获取一次性验证码的值
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			//为了保证验证码只使用一次，应将session中的验证码清空
			request.getSession().removeAttribute("checkcode");
			//校验一次性验证码
			if(!checkcode1.equalsIgnoreCase(checkcode2)){
				request.setAttribute("ErrorMsg", "验证码输入错误!");
				//请求转发
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return ;
			}
			
			//System.out.println("路径名:"+request.getContextPath());
			
			//2、封装数据
			Admin admin = new Admin();
			admin.setAno(ANO);
			admin.setApwd(APWD);
			//3、处理数据
			AdminModel  adminModel = new AdminModel();
			Admin existAdmin = adminModel.login(admin);
			
			//4、页面跳转
			if(existAdmin == null){
				//登录失败  向request域中保存错误信息
				request.setAttribute("ErrorMsg", "用户名或密码错误!");
				//使用请求转发进行页面跳转
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
			}else{
				//保存用户信息,保存到会话中
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("existAdmin", existAdmin);
				//记住用户名
				String remember = request.getParameter("remember");
				if("true".equals(remember)){
					//已经勾选了记住用户名 
					Cookie cookie = new Cookie("remember",existAdmin.getAno());
					//设置有效路径
					cookie.setPath("/ppca_manage");
					//设置有效时长 24小时
					cookie.setMaxAge(60*60*24);
					//将Cookie回写到浏览器
					response.addCookie(cookie);
				}
				//重定向到成功页面
				response.sendRedirect("/ppca_manage/pages/home.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	//退出系统
	private void shutDown(HttpServletRequest request, HttpServletResponse response) {
		//销毁session
		request.getSession().invalidate();
		
	}
}
