package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;

/**
 * 案例 分页显示信息 的servlet  用于接收请求，获取所有产品的信息
 */
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/text;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		//接受浏览器的请求参数 pageNO  pageSize  页码   每页条数
		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		//查询所有的产品信息，得到List<Product>对象，转换成json数据响应到浏览器
		ProductService service = new ProductService();
		try {
			//List<Product> ps = service.findAllL(pageNO,pageSize);
			
			//使用PageBean
			PageBean pb = service.findAll(pageNO, pageSize);
			
			/*for(int i=0;i<ps.size();i++)
			{
				System.out.println(ps.get(i).getName());
			}*/
			
			
			//将ps转换成json，响应到浏览器
			//String json = JSONObject.toJSONString(ps);
			
			String json = JSONObject.toJSONString(pb);
			
			response.getWriter().write(json);
			
		} catch (SQLException e) {
			e.printStackTrace();
			//像浏览器发送描述错误信息的json数据
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
