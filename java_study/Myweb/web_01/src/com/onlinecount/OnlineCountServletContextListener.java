package com.onlinecount;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OnlineCountServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//�ڷ���������ʱ��ʼ�� һ��ֵΪ0
		
		//�����ֵ�浽ServletContext��
		sce.getServletContext().setAttribute("count", 0);
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
