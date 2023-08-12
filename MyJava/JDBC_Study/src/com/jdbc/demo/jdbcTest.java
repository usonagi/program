package com.jdbc.demo;

import java.sql.*;

public class jdbcTest {
	
	public static void main(String[] args) throws SQLException {
		String name = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
		String user = "sa";
		String pwd = "a";
		
		try {
			Class.forName(name);
			Connection dbcon = DriverManager.getConnection(url, user, pwd);
			System.out.println("连接成功。");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("连接失败。");
		}
		
	}
}
