package com.filter.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * ������������
 */
public class FilterDemo1 implements Filter{

	@Override
	public void destroy() {
		System.out.println("FilterDemo1������...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("FilterDemo1ִ����...");
		chain.doFilter(request, response);  //���в���
		System.out.println("FilterDemo1ִ�н���...");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("FilterDemo1��������...");
	}

}
