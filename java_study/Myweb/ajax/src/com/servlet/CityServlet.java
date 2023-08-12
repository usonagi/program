package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CityUtils;

/**
 * 处理“获取城市信息”请求的Servlet
 */
public class CityServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决POST请求乱码
		request.setCharacterEncoding("UTF-8");
		
		//得到省份名称
		String pname = request.getParameter("provinceName");
		
		//根据省份名称得到对应城市
		String cities = CityUtils.getCity(pname);
		
		//写回到浏览器
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(cities);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
