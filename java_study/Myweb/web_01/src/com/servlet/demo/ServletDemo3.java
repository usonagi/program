package com.servlet.demo;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/*
 * Servlet的生命周期
 */
public class ServletDemo3 implements Servlet{

	@Override
	/*
	 * Servlet对象被实例化的时候init方法就会执行，且只执行一次。
	 */
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("Servlet initionl ...");
	}

	@Override
	/*
	 * 任何一次请求都会执行Service方法。
	 */
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("Servlet is excuting ...");
	}
	
	@Override
	/*
	 *Servlet从服务器中移除或者服务器关闭的时候销毁Servlet。执行一次。
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
