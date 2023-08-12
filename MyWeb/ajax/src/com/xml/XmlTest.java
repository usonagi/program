package com.xml;

import java.util.ArrayList;
import java.util.List;

import com.domain.Project;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/*
 * 测试、学习XStream工具
 */
public class XmlTest {
	public static void main(String[] args) {
		Project p1 = new Project(1, "智能手机", 200, 2500);
		Project p2 = new Project(2, "液晶电视", 100, 4000);
		Project p3 = new Project(3, "美的空调", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		//使用XStream完成Java对象转换成xml
		XStream stream = new XStream();
		
		//设置类或属性别名
		/*stream.alias("商品", Project.class);
		stream.alias("商品清单", java.util.List.class);
		stream.aliasField("商品名", Project.class, "name");*/	//参数1-别名  参数2-属于哪个类  参数3-属性
		
		//忽略属性	参数1-类名  参数2-属性名
		//stream.omitField(Project.class, "id");
		
		//将类的属性设置成节点的属性	<商品> <id="1"> <...> ==> <商品 id="1"> <...>
		//stream.useAttributeFor(Project.class,"id");
		
		//使用注解完成 上述操作 见Project.java 在对应的Java类中相应位置添加--@XStreamAlias("别名")
		//使用注解必须开启注解扫描
		stream.autodetectAnnotations(true);
		
		String xml = stream.toXML(list);
		
		System.out.println(xml);
	}
}
