package com.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * JDBC�Ĺ�����  ���ӳ�
 */
public class JDBCUtils2 {

	//����һ�����ӳ�
	private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/*
	 * ������ӵķ���
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	/*
	 * ������ӳ�
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/*
	 * �ͷ���Դ
	 */
	public static void release(Statement stmt,Connection conn){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
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
	
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
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
