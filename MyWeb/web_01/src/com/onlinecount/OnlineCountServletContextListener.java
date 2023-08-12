package com.onlinecount;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnlineCountServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//在服务器启动时初始化 一个值为0
		
		//将这个值存到ServletContext中
		sce.getServletContext().setAttribute("count", 0);
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
