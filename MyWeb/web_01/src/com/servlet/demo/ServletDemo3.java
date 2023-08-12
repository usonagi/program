package com.servlet.demo;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/*
 * Servlet����������
 */
public class ServletDemo3 implements Servlet{

	@Override
	/*
	 * Servlet����ʵ������ʱ��init�����ͻ�ִ�У���ִֻ��һ�Ρ�
	 */
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("Servlet initionl ...");
	}

	@Override
	/*
	 * �κ�һ�����󶼻�ִ��Service������
	 */
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("Servlet is excuting ...");
	}
	
	@Override
	/*
	 *Servlet�ӷ��������Ƴ����߷������رյ�ʱ������Servlet��ִ��һ�Ρ�
	 */
	public void destroy() {
		System.out.println("Servlet was been destroyed ...");
	}

	
	
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

}
