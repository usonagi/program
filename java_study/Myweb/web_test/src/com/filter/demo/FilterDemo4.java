package com.filter.demo;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/*
 * ��ʾFilterConfig
 */
public class FilterDemo4 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("web_test FilterDemo4��ִ����...");
		chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//��ù�����������
		String filterName = filterConfig.getFilterName();
		System.out.println(filterName);
		
		//��ó�ʼ������
		String username = filterConfig.getInitParameter("username");
		System.out.println(username);
		
		//������г�ʼ������������
		Enumeration<String> names = filterConfig.getInitParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = filterConfig.getInitParameter(name);
			System.out.println(name + " " + value);
		}

	}

}
