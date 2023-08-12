package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.CityUtils;

/**
 * 处理“获取省份信息”请求的Servlet
 */
public class ProvinceServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到省份信息
		String province = CityUtils.getProvince();
		
		//写回到浏览器
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(province);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
