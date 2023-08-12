package com.servlet.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request����API��ʾ	  ���ձ�����
 */
public class RequestDemo2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("�û���:"+username+"    ����:"+password);
		//�����Ա�ͼ���
		String sex = request.getParameter("sex");
		String city = request.getParameter("city");
		System.out.println("�Ա�:"+sex+"\n"+"����:"+city);
		//���հ���
		String[] hobby = request.getParameterValues("hobby");
		System.out.println("����:"+Arrays.toString(hobby));
		//�������ҽ���
		String info = request.getParameter("info");
		System.out.println("���ҽ���:"+info);
		
		System.out.println();
		
		//ʹ��getParameterMap��������
		Map<String, String[]> map = request.getParameterMap();
		for (String key:map.keySet()) {
			String[] value = map.get(key);
			System.out.println(key+"  "+Arrays.toString(value));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
