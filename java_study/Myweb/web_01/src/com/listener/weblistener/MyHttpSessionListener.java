package com.listener.weblistener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/*
 * 监听HttpSession的创建和销毁的监听器
 * 配置1分钟无操作session被销毁	<session-config><session-timeout>1</session-timeout></session-config>
 */
public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	//HttpSession的创建
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("HttpSession对象被创建了...");
	}

	@Override
	//HttpSession的销毁
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("HttpSession对象被销毁了...");
	}

}
