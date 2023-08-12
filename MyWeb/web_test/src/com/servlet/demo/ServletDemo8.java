package com.servlet.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Response����API����ʾ
 */
public class ServletDemo8 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ��״̬��
		//response.setStatus(404);
		
		//����ض���	����ʹ��response.sendRedirect()�滻��������
		/*response.setStatus(302);
		//������Ӧͷ
		response.setHeader("Location", "/web_test/ServletDemo2");*/
		
		//��ʱˢ��
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("5���Ժ���תҳ�棡");
		response.setHeader("Refresh", "5;url=/web_test/ServletDemo2");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
