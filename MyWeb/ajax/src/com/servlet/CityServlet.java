package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CityUtils;

/**
 * ������ȡ������Ϣ�������Servlet
 */
public class CityServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���POST��������
		request.setCharacterEncoding("UTF-8");
		
		//�õ�ʡ������
		String pname = request.getParameter("provinceName");
		
		//����ʡ�����Ƶõ���Ӧ����
		String cities = CityUtils.getCity(pname);
		
		//д�ص������
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(cities);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
