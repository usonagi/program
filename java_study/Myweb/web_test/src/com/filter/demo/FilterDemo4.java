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
 * 演示FilterConfig
 */
public class FilterDemo4 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("web_test FilterDemo4被执行了...");
		chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获得过滤器的名称
		String filterName = filterConfig.getFilterName();
		System.out.println(filterName);
		
		//获得初始化参数
		String username = filterConfig.getInitParameter("username");
		System.out.println(username);
		
		//获得所有初始化参数的名称
		Enumeration<String> names = filterConfig.getInitParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			String value = filterConfig.getInitParameter(name);
			System.out.println(name + " " + value);
		}

	}

}
