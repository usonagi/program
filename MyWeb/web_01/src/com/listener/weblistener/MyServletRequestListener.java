package com.listener.weblistener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/*
 * ����ServletRequest����Ĵ��������ٵļ�����
 */
public class MyServletRequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("ServletRequest����������...");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("ServletRequest���󱻴�����...");
		
	}

}
