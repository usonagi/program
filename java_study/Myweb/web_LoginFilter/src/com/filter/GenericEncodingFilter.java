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
 * ͨ�õ��ַ������������
 * ����û�д��󣬿�����web_test����ִ�У�����ԭ�������web.xml������
 */
public class GenericEncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("Encoding�������ˡ�");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// �ڹ���������ǿrequest���󣬲�����ǿ���request���󴫵ݸ�Servlet
		HttpServletRequest req = (HttpServletRequest) request;
		// ��ǿreq:
		MyHttpServletRequest myReq = new MyHttpServletRequest(req);
		chain.doFilter(myReq, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Encoding�������ˡ�");
	}

}
