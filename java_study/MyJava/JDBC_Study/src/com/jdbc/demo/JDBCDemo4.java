package com.jdbc.demo;

import org.junit.Test;

/*
 * SQL注入漏洞
 */
public class JDBCDemo4 {

	@Test
	/*
	 * SQL注入漏洞演示
	 * 原因：在变量中存在SQL关键字 "--"、"or"
	 * 解决方法：采用PreparedStatement对象解决注入漏洞。它将SQL预先进行编译，使用？作为占位符。
	 */
	public void demo1(){
		UserDao userDao = new UserDao();
		boolean flag = userDao.login("test", "test");	//使用正确的用户名和密码
		//boolean flag = userDao.login("test' -- ", "test1");
		//boolean flag = userDao.login("test' or '1=1", "test2");	//好像不行，
		if(flag){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
	}
}
