package com.jdbc.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;
import com.JDBCUtils.JDBCUtils2;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * C3P0连接池的测试
 */
public class DataSourceC3P0 {

	@Test
	/*
	 * 工具类JDBCUtils2的测试
	 */
	public void demo3(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//从连接池中获得连接
			conn = JDBCUtils2.getConnection();
			//编写sql
			String sql = "select * from account ";
			//预编译
			pstmt = conn.prepareStatement(sql);
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils2.release(rs, pstmt, conn);
		}
	}
	
	@Test
	/*
	 * 采用配置文件設置參數   ctrl+shift+f 繁体输入 
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//创建连接池  默認去类路径下查找c3p0-config.xml
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//从连接池中获得连接
			conn = dataSource.getConnection();
			//编写sql
			String sql = "select * from account";
			//预编译
			pstmt = conn.prepareStatement(sql);
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
	@Test
	/*
	 * 手动设置参数
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//从连接池中获得连接
			//创建连接池
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//设置连接参数
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=test");
			dataSource.setUser("sa");
			dataSource.setPassword("a");
			//从连接池中获得连接
			conn = dataSource.getConnection();
			//编写sql
			String sql = "select * from account";
			//预编译
			pstmt = conn.prepareStatement(sql);
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
}
