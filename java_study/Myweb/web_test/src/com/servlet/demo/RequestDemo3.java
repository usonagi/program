package com.servlet.demo;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request��������������������
 */
public class RequestDemo3 extends HttpServlet {
	
	/*
	 * ��ʾget��ʽ������������
	 * ԭ��:get��ʽ�ύ�������������е�url���棬�ڵ�ַ�����й�URL����
	 * �������:������request��������������ISO-8859-1�ķ�ʽ��ȡ����UTF-8�ķ�ʽ����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������
		String name = request.getParameter("name");
		/*String encode = URLEncoder.encode(name, "ISO-8859-1");
		String decode = URLDecoder.decode(encode, "UTF-8");
		System.out.println("����:"+decode);*/
		
		String value = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("����:"+value);
	}

	/*
	 * ��ʾpost������������
	 * ԭ��:post��ʽ�ύ���������������У�request����������ݺ����request���������û�������֧�����ġ�
	 * �������:�޸�request�������ı���
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���û������ı���
		request.setCharacterEncoding("UTF-8");
		//��������
		String name = request.getParameter("name");
		System.out.println("����:"+name);
		
	}

}
