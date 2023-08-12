package com.cookie;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��¼�û��ϴη���ʱ��
 */
public class VisitServlet extends HttpServlet {
	
	/*
	 * ���⣺���û�����֮��ر���������·�����ᱻ���ɵ�һ�η���
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * �û���һ�η��ʣ���ʾ��ӭ��Ϣ����¼��ǰ����ʱ�䣬�浽cookie����д�������
		 * ���ǵ�һ�η��ʣ���cookie�еõ��ϴ�ʱ�䣬��¼��ǰ����ʱ�䣬����cookie����д�������
		 */
		//��cookie�����л�ȡָ�����Ƶ�cookie���ɴ��ж��Ƿ��ǵ�һ�η���
		//��ô����������������cookie
		Cookie[] cookies = request.getCookies();
		//���������ҵ�ָ�����Ƶ�Cookie
		Cookie cookie = CookieUtils.findCookie(cookies, "lastVisit");
		//�ж��Ƿ��ǵ�һ�η���
		if(cookie == null){
			//��һ�η��ʣ���ʾ��ӭ��Ϣ
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<h1>���ã���ӭʹ�ñ���վ��<h1>");
		}else{
			//���ǵ�һ�η���,����ϴη���ʱ��
			response.setContentType("text/html;charset=UTF-8");
			String lastTime = cookie.getValue(); 
			response.getWriter().println("<h1>���ã������ϴη���ʱ��Ϊ: "+lastTime+"<h1>");
		}
		//��¼��ǰʱ��,���뵽cookie����д�������
		Date date = new Date();
		//����Cookie
		Cookie ck = new Cookie("lastVisit",date.toLocaleString());
		//����Cookie����Ч·��
		ck.setPath("/web_02");
		//����Cookie����Чʱ��Ϊ1Сʱ
		ck.setMaxAge(60 * 60);
		//��д�������
		response.addCookie(ck);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
