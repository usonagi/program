package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.domain.Project;

/**
 * ����demo4.html ������ ��ʾ��Ʒ��Ϣ�� ��Servlet
 */
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = request.getParameter("callback");  //���ڿ�����ʾ  web_example/demo/demo3.html
		
		//������Ӧ���ݵ���������
		response.setCharacterEncoding("UTF-8");
		
		Project p1 = new Project(1, "�����ֻ�", 200, 2500);
		Project p2 = new Project(2, "Һ������", 100, 4000);
		Project p3 = new Project(3, "���Ŀյ�", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		String json = JSONObject.toJSONString(list);
		//response.getWriter().write(json);		//����json���ݣ����ڵ�ǰ�������
		response.getWriter().write(callback+"("+json+")");	//����jsonp���ݣ����ڽ���������� �����web_example/demo3.html
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
