package com.servlet.demo;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ServletConfig�����API����ʾ
 * ServletContext��������� ��һ����ȡweb��Ŀ����Ϣ
 */
public class ServletDemo6 extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=====ServletConfig��API��ʾ=====
		//1����ó�ʼ��������API
		//���ServletConfig����
		ServletConfig config = this.getServletConfig();
		String username = config.getInitParameter("username");
		String password = config.getInitParameter("password");
		System.out.println(username+ "  " +password);
		
		//2��������г�ʼ������������
		Enumeration<String> names = config.getInitParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = config.getInitParameter(name);
			System.out.println(name + " " + value);
		}
		
		//3�����Servlet������
		String servletName = config.getServletName();
		System.out.println(servletName);
		
		
		//=====ServletContext��������ʾ====
		//1����ȡ�ļ���MIME����
		//���ServletContext
		ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType("xx.txt");
		System.out.println(mimeType);
		
		//2���������·���Ĺ�����
		String path = context.getContextPath();
		System.out.println(path);
		
		//3�����ȫ�ֵĳ�ʼ������
		String Uname = context.getInitParameter("UserName");
		String Pwd = context.getInitParameter("PassWord");
		System.out.println(Uname + " " + Pwd);
		Enumeration<String> enumNames = context.getInitParameterNames();
		while(enumNames.hasMoreElements()){
			String name = enumNames.nextElement();
			String value = context.getInitParameter(name);
			System.out.println(name+ " " +value);
		}
		
		//ServletContext��Ϊ������ȡ����	��Demo7�г�ʼ��  �ȷ���7�ٷ���6
		String name = (String) this.getServletContext().getAttribute("name");
		System.out.println();
		System.out.println("������"+ name);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
