package com.listener.weblistener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * ServletContextListener监听器
 * 事件源：ServletContext
 * 监听器：MyServletContextListener
 * 事件源和监听器绑定：通过配置方式绑定,在web.xml文件中<listener><listener-class>监听器类的全路径</listener-class> </listener>
 * ServletContext创建和销毁：在服务器启动时，为每个web应用单独创建ServletContext对象；在服务器关闭时，或项目从web服务器移除时销毁。
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	//监听ServletContext的销毁：
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext被销毁了...");
	}

	@Override
	//监听ServletContext的创建：
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext被创建了...");
		
	}

}
