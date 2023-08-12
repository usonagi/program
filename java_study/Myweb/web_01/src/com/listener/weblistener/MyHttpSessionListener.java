package com.listener.weblistener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/*
 * ����HttpSession�Ĵ��������ٵļ�����
 * ����1�����޲���session������	<session-config><session-timeout>1</session-timeout></session-config>
 */
public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	//HttpSession�Ĵ���
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HttpSession���󱻴�����...");
	}

	@Override
	//HttpSession������
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("HttpSession����������...");
	}

}
