package com.listener.bean;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/*
 * 监听HttpSession中Java类的绑定和解除绑定的监听器
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
		System.out.println("Bean1与session进行绑定...");
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("Bean1与session解除绑定...");
		
	}
}
