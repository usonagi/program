package com.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/*
 * ����ѧϰJson���࣬���JsonTest
 */
@XStreamAlias("��Ʒ")
public class Project {

	//@JSONField(name = "���")	//Ϊidָ������
	private int id;
	@XStreamAlias("��Ʒ��")	//ָ��������@XStreamOmitField--��������
	private String name;
	private int count;
	private double price;
	//@JSONField(format = "yyyy��MM��dd��")	//ָ�����ڸ�ʽ
	private Date pdate;	//��������
	
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Project(int id,String name,int count,double price){
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
	}
	public Project(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setNamne(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
