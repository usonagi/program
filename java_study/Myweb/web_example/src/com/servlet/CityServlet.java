package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.domain.City;
import com.domain.Province;
import com.thoughtworks.xstream.XStream;

/**
 * ʡ������ �� ��servlet
 */
public class CityServlet extends HttpServlet {
	
	private Map<Province,List<City>> map;
	
	@Override
	public void init() throws ServletException {
		map = new HashMap<Province,List<City>>();
		//����ʡ��
		Province p1 = new Province();
		p1.setId(1);
		p1.setName("������");
		
		City c1 = new City();
		c1.setId(1);
		c1.setName("������");
		
		City c2 = new City();
		c2.setId(2);
		c2.setName("����");
		
		City c3 = new City();
		c3.setId(3);
		c3.setName("�������");
		
		List<City> l1 = new ArrayList<City>();
		l1.add(c1);
		l1.add(c2);
		l1.add(c3);
		map.put(p1, l1);
		
		
		Province p2 = new Province();
		p2.setId(2);
		p2.setName("����");
		
		City c21 = new City();
		c21.setId(1);
		c21.setName("����");
		
		City c22 = new City();
		c22.setId(2);
		c22.setName("����");
		
		City c23 = new City();
		c23.setId(3);
		c23.setName("��ƽ");
		
		List<City> l2 = new ArrayList<City>();
		l2.add(c21);
		l2.add(c22);
		l2.add(c23);
		map.put(p2, l2);
		
		
		Province p3 = new Province();
		p3.setId(3);
		p3.setName("����");
		
		City c31 = new City();
		c31.setId(1);
		c31.setName("����");
		
		City c32 = new City();
		c32.setId(2);
		c32.setName("����");
		
		City c33 = new City();
		c33.setId(3);
		c33.setName("��«��");
		
		List<City> l3 = new ArrayList<City>();
		l3.add(c31);
		l3.add(c32);
		l3.add(c33);
		map.put(p3, l3);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		
		//�ж�method��ֵ
		if("pro".equals(method)){
			//��ʡ�ݶ����set����ת����json������Ӧ�������
			Set<Province> keys = map.keySet();
			String json = JSONObject.toJSONString(keys);
			response.getWriter().write(json);
		}
		
		//��ȡ������Ϣ
		if("city".equals(method)){
			//��ȡ��������ݹ�����ʡ����
			String pname = request.getParameter("pname");
			
			//����ʡ�����ƻ�ȡ��Ӧ��List<City>
			for(Province p:map.keySet()){
				if(pname.equals(p.getName())){
					List<City> cities = map.get(p);
					//���õ���citiesת����json���ݣ����쵽�����
					String json = JSONObject.toJSONString(cities);
					
					response.getWriter().write(json);
					break;
				}
			}
		}
		
		//����xml����
		if("xml".equals(method)){
			XStream xStream = new XStream();
			xStream.autodetectAnnotations(true);
			String xml = xStream.toXML(map);
			response.getWriter().write(xml);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
