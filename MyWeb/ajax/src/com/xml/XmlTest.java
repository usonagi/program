package com.xml;

import java.util.ArrayList;
import java.util.List;

import com.domain.Project;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/*
 * ���ԡ�ѧϰXStream����
 */
public class XmlTest {
	public static void main(String[] args) {
		Project p1 = new Project(1, "�����ֻ�", 200, 2500);
		Project p2 = new Project(2, "Һ������", 100, 4000);
		Project p3 = new Project(3, "���Ŀյ�", 400, 3000);
		
		List<Project> list = new ArrayList<Project>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		//ʹ��XStream���Java����ת����xml
		XStream stream = new XStream();
		
		//����������Ա���
		/*stream.alias("��Ʒ", Project.class);
		stream.alias("��Ʒ�嵥", java.util.List.class);
		stream.aliasField("��Ʒ��", Project.class, "name");*/	//����1-����  ����2-�����ĸ���  ����3-����
		
		//��������	����1-����  ����2-������
		//stream.omitField(Project.class, "id");
		
		//������������óɽڵ������	<��Ʒ> <id="1"> <...> ==> <��Ʒ id="1"> <...>
		//stream.useAttributeFor(Project.class,"id");
		
		//ʹ��ע����� �������� ��Project.java �ڶ�Ӧ��Java������Ӧλ�����--@XStreamAlias("����")
		//ʹ��ע����뿪��ע��ɨ��
		stream.autodetectAnnotations(true);
		
		String xml = stream.toXML(list);
		
		System.out.println(xml);
	}
}
