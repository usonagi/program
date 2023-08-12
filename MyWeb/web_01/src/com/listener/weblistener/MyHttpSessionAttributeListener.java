package com.listener.weblistener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/*
 * 演示HttpSessionAttributeListener 监听器
 * ServletContextAttributeListener、ServletRequestAttributeListener的方法、功能一致，只是参数不同。
 */
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		System.out.println("向session中添加了属性...");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		System.out.println("从session中移除了属性...");
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		System.out.println("向session中替换了属性...");
		
	}

}
