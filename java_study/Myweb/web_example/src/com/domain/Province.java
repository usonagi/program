package com.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/*
 * 案例 省市联动 二 的省类
 */
@XStreamAlias("省份")
public class Province {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
