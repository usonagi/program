package com.servlet.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet ģ��
 * Response��Ӧ���ĵĴ���
 */
public class ServletDemo2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//useByte(response);
		useChar(response);
	}

	/*ʹ���ַ����������һ����������� responseĬ�ϵĻ��������벻֧������
	 *�������������response����ַ����Ļ������ı����������Ĭ�ϴ򿪲��õ��ַ���һ�¼���
	 */
	private void useChar(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//���������Ĭ�ϴ򿪲��õ��ַ���
		//response.setHeader("Content-Type", "text/html);charset=UTF-8");
		//����response����ַ����Ļ������ı���
		//response.setCharacterEncoding("UTF-8");
		
		//ʹ�����·����滻������  �򻯴���
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().println("��ʾʹ���ַ����������...");
	}
	
	/*ʹ���ֽ���������Ĳ�һ��������  
	    ���������������ת�����ֽ�������ַ��������������Ĭ�ϴ򿪲��õ��ַ���һ�¼���
	*/
	private void useByte(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		//ʹ���ֽ����ķ�ʽ�������
		ServletOutputStream outputStream = response.getOutputStream();
		//���������Ĭ�ϴ�ʱ���ַ���
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//��������ת���ֽ�������ַ�������
		outputStream.write("��ʾʹ���ֽ����������...".getBytes("UTF-8"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
