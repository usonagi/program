package com.listener.weblistener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/*
 * ��ʾHttpSessionAttributeListener ������
 * ServletContextAttributeListener��ServletRequestAttributeListener�ķ���������һ�£�ֻ�ǲ�����ͬ��
 */
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("��session�����������...");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("��session���Ƴ�������...");
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("��session���滻������...");
		
	}

}
