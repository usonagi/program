package com.servlet.demo;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * Servlet�����ų���
 */
public class FirstServlet implements Servlet{

	@Override
	/**
	 * ���ڴ���ͻ������󣬲�������Ӧ�ķ���
	 */
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		//��ҳ�����һ���ַ���
		resp.getWriter().println("Heelo Servlet....!");
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		
	}

	
}
