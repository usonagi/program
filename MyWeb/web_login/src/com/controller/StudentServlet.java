package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Student;
import com.model.StudentModel;

/**
 * ��ѯѧ����Ϣ��Servlet
 */
public class StudentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		//	1������Java�ദ������
		StudentModel studentModel = new StudentModel();
		List<Student> list = studentModel.finAll();
		
		/*for(int i=0;i < list.size();i++){
			System.out.println(list.size());
			Student s = list.get(i);
			System.out.println(s.getSname()+" "+s.getSid());
		}*/
		
		//	2����ʾ��jspҳ��
		request.setAttribute("list", list);
		request.getRequestDispatcher("/Demo/list.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
