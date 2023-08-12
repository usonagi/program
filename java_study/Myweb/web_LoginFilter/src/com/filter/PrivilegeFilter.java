package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.domain.User;

/*
 * Ȩ����֤�Ĺ�����
 * �Գ�login.jsp�����ҳ��ȫ���������� <url-pattern>/jsp/*</url-pattern>
 */
public class PrivilegeFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//���û��ѵ�¼������У�����ص���¼ҳ��
		HttpServletRequest req = (HttpServletRequest) request;
		User existUser = (User) req.getSession().getAttribute("existUser");
		if(existUser == null){
			// û�е�¼
			req.setAttribute("msg", "����û�е�¼��û��Ȩ�޷��ʣ�");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}else{
			// �Ѿ���¼
			chain.doFilter(req, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
