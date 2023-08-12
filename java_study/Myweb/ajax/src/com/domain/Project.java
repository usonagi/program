package com.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/*
 * 用于学习Json的类，配合JsonTest
 */
@XStreamAlias("商品")
public class Project {

	//@JSONField(name = "编号")	//为id指定别名
	private int id;
	@XStreamAlias("商品名")	//指定别名，@XStreamOmitField--忽略属性
	private String name;
	private int count;
	private double price;
	//@JSONField(format = "yyyy年MM月dd日")	//指定日期格式
	private Date pdate;	//生产日期
	
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
