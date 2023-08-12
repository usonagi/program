package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jquery��ajax��������
 * ����demo3.html ��֤�û����Ƿ���õ� Servlet
 */
public class LoadServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//1���õ��û���
		String username = request.getParameter("username");
		
		//2���ж��û����Ƿ����ʹ��
		if("xxx".equals(username)){
			//�û���������
			response.getWriter().write("<font color='red'>�û�����ռ��</font>");
		}else{
			//�û�������
			response.getWriter().write("<font color='green'>�û�������ʹ��</font>");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
