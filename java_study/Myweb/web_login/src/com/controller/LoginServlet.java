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
 * ��¼��Servlet
 */
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1����������
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println(username);
			
			//====һ������֤���У��=====
			//������֤��
			String checkcode1 = request.getParameter("checkcode");
			//��session�л�ȡһ������֤���ֵ
			String checkcode2 = (String) request.getSession().getAttribute("checkcode");
			//Ϊ�˱�֤��֤��ֻʹ��һ�Σ�Ӧ��session�е���֤�����
			request.getSession().removeAttribute("checkcode");
			//У��һ������֤��
			if(!checkcode1.equalsIgnoreCase(checkcode2)){
				request.setAttribute("msg", "��֤���������!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return ;
			}
			
			//2����װ����
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			//3����������
			UserModel  userModel = new UserModel();
			User existUser = userModel.login(user);
			
			//4��ҳ����ת
			if(existUser == null){
				//��¼ʧ��  ��request���б��������Ϣ
				request.setAttribute("msg", "�û������������!");
				//ʹ������ת������ҳ����ת
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				//�����û���Ϣ,���浽�Ự��
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("existUser", existUser);
				//��ס�û���
				String remember = request.getParameter("remember");
				if("true".equals(remember)){
					//�Ѿ���ѡ�˼�ס�û��� 
					Cookie cookie = new Cookie("remember",existUser.getUsername());
					//������Ч·��
					cookie.setPath("/web_login");
					//������Чʱ�� 24Сʱ
					cookie.setMaxAge(60*60*24);
					//��Cookie��д�������
					response.addCookie(cookie);
				}
				//�ض��򵽳ɹ�ҳ��
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
