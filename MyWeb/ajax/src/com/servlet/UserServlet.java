package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ���ڰ�������֤�û����Ƿ���á� ��Servlet
 */
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1���õ��������
		String username = request.getParameter("username");
		
		//2���ж��û����Ƿ�ռ��
		String msg = "";
		if("xxx".equals(username)){
			msg = "<font color='red'>�û����ѱ�ʹ��</font>";
		}else{
			msg = "<font color='green'>�û���δ��ʹ��</font>";
		}
		
		//3��ͨ��response��ȡ�������д�������
		response.setCharacterEncoding("UTF-8");  //��ֹ����
		response.getWriter().write(msg);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
