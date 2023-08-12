package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/*
 * JDBC的入门程序
 * API 详解
 * DriverManager： 驱动管理类   作用：注册驱动，  获取连接
 * 	Class.forName(""com.microsoft.sqlserver.jdbc.SQLServerDriver"");加载驱动
 * 	getConnection(url,username,password);获得与数据库的连接
 * 
 * Connection: 与数据库连接对象   作用：创建并执行sql语句的对象  管理事务
 * 	执行sql语句对象：Statement执行SQL，CallableStatement执行存储过程，PrepareStatement执行SQL，对SQL预处理，解决漏洞
 * 
 * Statement：执行SQL   作用：执行SQL ，执行批处理 
 * 	execute(sql)执行查询、修改、添加、删除的sql语句,excuteQuery(sql)执行查询,excuteUpdate(sql)执行修改、添加、删除语句;
 * 
 * ResultSet：通过select语句的结果集    
 * 	使用.next()方法遍历结果集，使用getXXX()方法获取结果集
 * 
 * 释放资源，ResultSet、Statement、Connection，资源释放代码写到finally中
 */
public class JDBCDemo1 {
	@Test
	public void demo1() throws Exception{
		//	加载驱动
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		//	获得连接  url:与数据库连接的路径  user、password--与数据库连接的用户名和密码
		/*url地址：jdbc:mysql://loacalhost:3306/test
		 * jdbc:连接数据库的协议  sqlserver:是jdbc的子协议  localhost：连接数据库服务器的主机地址，
		 * 					是本机可以写成localhost，不是本机则需要写上主机的IP地址。
		 * 3306：sqlserver数据库服务器的端口号
		 *   */
		Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
		
		//	基本操作	获取执行SQl语句的对象  编写sql语句  执行sql语句
		Statement statement = con.createStatement();
		String sql = "select * from U";
		ResultSet res = statement.executeQuery(sql);
		
		while (res.next()){	//遍历结果集
			System.out.print(res.getInt("id")+" ");
			System.out.print(res.getString("username")+" ");
			System.out.print(res.getString("password")+" ");
			System.out.println();
		}
		
		//	释放资源
		res.close();
		statement.close();
		con.close();

	}

}
