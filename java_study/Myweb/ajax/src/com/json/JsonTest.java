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
 * 学习使用Json
 */
public class JsonTest {
	//将一个Project对象转换成json
	@Test
	public void test1(){
		//创建一个Project对象
		Project p1 = new Project(1,"电视机",100,2000);
		p1.setPdate(new Date());
		
		//使用过滤，可以将不需要转成Json的数据在转换时过滤掉
		/*SerializeFilter filter = new PropertyFilter() {
			
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				System.out.println(arg0);	//类名
				System.out.println(arg1);	//属性名
				System.out.println(arg2);	//属性值
				if("编号".equals(arg1)){
					return false;	//表示不转换成json数据
				}
				return true;
			}
		};*/
		
		// 使用简化操作   指定需要保留的数据
		SerializeFilter filter = new SimplePropertyPreFilter("count","name","price","pdate");
		
		//使用fastjson将p1转换成jason数据
		String json = JSONObject.toJSONString(p1,filter);
		System.out.println(json);
	}
	
	//将List<Project>对象转换成json
	@Test
	public void test2(){
		Project p1 = new Project(1, "智能手机", 200, 2500);
		Project p2 = new Project(2, "液晶电视", 100, 4000);
		Project p3 = new Project(3, "美的空调", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		String json = JSONObject.toJSONString(list);
		System.out.println(json);
	}
	
	//Java对象转成json时的循环引用问题
	@Test
	public void test3(){
		Project p1 = new Project(1, "智能手机", 200, 2500);
		Project p2 = new Project(2, "液晶电视", 100, 4000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p1);
		list.add(p1);
		
		//取消循环引用
		String json = JSONObject.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(json);
	}
}
