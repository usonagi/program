package com.onlinecount;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCountHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// session被创建 说明此人在线
		//获得原来的值
		HttpSession httpSession = se.getSession();
		System.out.println(httpSession.getId()+"上线了...");
		//获得ServletContext中的值
		Integer count = (Integer)httpSession.getServletContext().getAttribute("count");
		count ++;
		httpSession.getServletContext().setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// session被销毁 说明此人离线
		HttpSession httpSession = se.getSession();
		System.out.println(httpSession.getId()+"离线了...");
		//获得ServletContext中的值
		Integer count = (Integer)httpSession.getServletContext().getAttribute("count");
		count --;
		httpSession.getServletContext().setAttribute("count", count);
	}

}
