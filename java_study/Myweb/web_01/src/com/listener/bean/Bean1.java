package com.listener.bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/*
 * ����HttpSession��Java��İ󶨺ͽ���󶨵ļ�����
 */
public class Bean1 implements HttpSessionBindingListener{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("Bean1��session���а�...");
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("Bean1��session�����...");
		
	}
}
