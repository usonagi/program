package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.domain.Project;

/**
 * 用于demo4.html “案例 显示商品信息” 的Servlet
 */
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callback = request.getParameter("callback");  //用于跨域演示  web_example/demo/demo3.html
		
		//处理响应数据的中文乱码
		response.setCharacterEncoding("UTF-8");
		
		Project p1 = new Project(1, "智能手机", 200, 2500);
		Project p2 = new Project(2, "液晶电视", 100, 4000);
		Project p3 = new Project(3, "美的空调", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		String json = JSONObject.toJSONString(list);
		//response.getWriter().write(json);		//返回json数据，用于当前域的请求
		response.getWriter().write(callback+"("+json+")");	//返回jsonp数据，用于解决跨域问题 详情见web_example/demo3.html
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
