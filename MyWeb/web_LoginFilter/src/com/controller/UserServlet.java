package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;
import com.model.UserModel;


/**
 * �û���¼��Servlet
 */
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// ��������
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// ��װ����
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			// ��������
			UserModel userModel = new UserModel();
			User existUser = userModel.login(user);
			// ���ݽ����תҳ��
			if(existUser == null){
				//��¼ʧ��
				request.setAttribute("msg", "�û������������");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				//��¼�ɹ�
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
