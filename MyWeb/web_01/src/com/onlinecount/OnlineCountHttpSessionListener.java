package com.onlinecount;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// session������ ˵����������
		//���ԭ����ֵ
		HttpSession httpSession = se.getSession();
		System.out.println(httpSession.getId()+"������...");
		//���ServletContext�е�ֵ
		Integer count = (Integer)httpSession.getServletContext().getAttribute("count");
		count ++;
		httpSession.getServletContext().setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// session������ ˵����������
		HttpSession httpSession = se.getSession();
		System.out.println(httpSession.getId()+"������...");
		//���ServletContext�е�ֵ
		Integer count = (Integer)httpSession.getServletContext().getAttribute("count");
		count --;
		httpSession.getServletContext().setAttribute("count", count);
	}

}
