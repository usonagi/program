package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
 * 通用的字符集编码过滤器
 * 代码没有错误，可以在web_test正常执行，报错原因好像是web.xml有问题
 */
public class GenericEncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("Encoding被销毁了。");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 在过滤器中增强request对象，并将增强后的request对象传递给Servlet
		HttpServletRequest req = (HttpServletRequest) request;
		// 增强req:
		MyHttpServletRequest myReq = new MyHttpServletRequest(req);
		chain.doFilter(myReq, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Encoding被创建了。");
	}

}
