package com.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.domain.Project;

/*
 * ѧϰʹ��Json
 */
public class JsonTest {
	//��һ��Project����ת����json
	@Test
	public void test1(){
		//����һ��Project����
		Project p1 = new Project(1,"���ӻ�",100,2000);
		p1.setPdate(new Date());
		
		//ʹ�ù��ˣ����Խ�����Ҫת��Json��������ת��ʱ���˵�
		/*SerializeFilter filter = new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				System.out.println(arg0);	//����
				System.out.println(arg1);	//������
				System.out.println(arg2);	//����ֵ
				if("���".equals(arg1)){
					return false;	//��ʾ��ת����json����
				}
				return true;
			}
		};*/
		
		// ʹ�ü򻯲���   ָ����Ҫ����������
		SerializeFilter filter = new SimplePropertyPreFilter("count","name","price","pdate");
		
		//ʹ��fastjson��p1ת����jason����
		String json = JSONObject.toJSONString(p1,filter);
		System.out.println(json);
	}
	
	//��List<Project>����ת����json
	@Test
	public void test2(){
		Project p1 = new Project(1, "�����ֻ�", 200, 2500);
		Project p2 = new Project(2, "Һ������", 100, 4000);
		Project p3 = new Project(3, "���Ŀյ�", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		String json = JSONObject.toJSONString(list);
		System.out.println(json);
	}
	
	//Java����ת��jsonʱ��ѭ����������
	@Test
	public void test3(){
		Project p1 = new Project(1, "�����ֻ�", 200, 2500);
		Project p2 = new Project(2, "Һ������", 100, 4000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p1);
		list.add(p1);
		
		//ȡ��ѭ������
		String json = JSONObject.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
	}
}
