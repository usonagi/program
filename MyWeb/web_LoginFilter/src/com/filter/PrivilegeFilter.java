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
 * 权限验证的过滤器
 * 对除login.jsp以外的页面全部进行拦截 <url-pattern>/jsp/*</url-pattern>
 */
public class PrivilegeFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//若用户已登录，则放行，否则回到登录页面
		HttpServletRequest req = (HttpServletRequest) request;
		User existUser = (User) req.getSession().getAttribute("existUser");
		if(existUser == null){
			// 没有登录
			req.setAttribute("msg", "您还没有登录！没有权限访问！");
			req.getRequestDispatcher("/login.jsp").forward(req, response);
		}else{
			// 已经登录
			chain.doFilter(req, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
