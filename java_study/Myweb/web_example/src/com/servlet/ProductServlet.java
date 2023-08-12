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
 * ���� ��ҳ��ʾ��Ϣ ��servlet  ���ڽ������󣬻�ȡ���в�Ʒ����Ϣ
 */
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/text;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		//������������������ pageNO  pageSize  ҳ��   ÿҳ����
		int pageNO = Integer.parseInt(request.getParameter("pageNO"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		//��ѯ���еĲ�Ʒ��Ϣ���õ�List<Product>����ת����json������Ӧ�������
		ProductService service = new ProductService();
		try {
			//List<Product> ps = service.findAllL(pageNO,pageSize);
			
			//ʹ��PageBean
			PageBean pb = service.findAll(pageNO, pageSize);
			
			/*for(int i=0;i<ps.size();i++)
			{
				System.out.println(ps.get(i).getName());
			}*/
			
			
			//��psת����json����Ӧ�������
			//String json = JSONObject.toJSONString(ps);
			
			String json = JSONObject.toJSONString(pb);
			
			response.getWriter().write(json);
			
		} catch (SQLException e) {
			e.printStackTrace();
			//���������������������Ϣ��json����
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
