package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/*
 * JDBC的CRUD的操作
 */
public class JDBCDemo2 {

	@Test
	/*
	 * 查询一条记录
	 */
	public void demo5(){
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			stm = conn.createStatement();
			String sql = "select * from U where id = 1";
			rs = stm.executeQuery(sql);
			if(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stm = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	@Test
	/*
	 * 查询多条记录操作
	 */
	public void demo4(){
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			stm = conn.createStatement();
			String sql = "select * from U";
			rs = stm.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stm = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	@Test
	/*
	 * 删除操作
	 */
	public void demo3(){
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			stm = conn.createStatement();
			String sql = "delete from U where id = 3";
			int num = stm.executeUpdate(sql);
			if(num > 0){
				System.out.println("删除成功！");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stm = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	@Test
	/*
	 * 修改操作
	 */
	public void demo2(){
		
		Connection conn = null;
		Statement stm = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			stm = conn.createStatement();
			String sql = "update U set password = 'abc' where username = 'xxx'";
			int num = stm.executeUpdate(sql);
			if(num > 0){
				System.out.println("修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stm = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	
	
	/*
	 * 保存操作的代码实现
	 */
	public void demo1() {
		
		Connection conn = null;
		Statement stm = null;
		
		try{
			//注册驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//获得连接
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			//执行操作：
			//创建执行sql语句的对象
			stm = conn.createStatement();
			//编写sql语句
			String sql = "insert into U values(4,'xw','123')";
			//执行sql语句
			int num = stm.executeUpdate(sql);
			if(num > 0 ){
				System.out.println("保存成功！");
			}
			
		}catch(Exception e){	
			e.printStackTrace();
		}finally {
			//资源释放
			if(stm != null){
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stm = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

}
