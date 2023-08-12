package com.filter.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 过滤器的入门
 */
public class FilterDemo1 implements Filter{

	@Override
	public void destroy() {
		System.out.println("FilterDemo1销毁了...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("FilterDemo1执行了...");
		chain.doFilter(request, response);  //放行操作
		System.out.println("FilterDemo1执行结束...");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("FilterDemo1被创建了...");
	}

}
