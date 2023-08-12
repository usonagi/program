package com.listener.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
/*
 * ����HttpSession��Java��Ķۻ��ͻ�ļ�����
 */
public class Bean2 implements HttpSessionActivationListener ,Serializable{

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("Bean2��session���...");
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("Bean2��session�ۻ���...");
	}

}
