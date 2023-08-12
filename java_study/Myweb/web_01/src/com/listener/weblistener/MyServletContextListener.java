package com.listener.weblistener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * ServletContextListener������
 * �¼�Դ��ServletContext
 * ��������MyServletContextListener
 * �¼�Դ�ͼ������󶨣�ͨ�����÷�ʽ��,��web.xml�ļ���<listener><listener-class>���������ȫ·��</listener-class> </listener>
 * ServletContext���������٣��ڷ���������ʱ��Ϊÿ��webӦ�õ�������ServletContext�����ڷ������ر�ʱ������Ŀ��web�������Ƴ�ʱ���١�
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	//����ServletContext�����٣�
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext��������...");
	}

	@Override
	//����ServletContext�Ĵ�����
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext��������...");
		
	}

}
