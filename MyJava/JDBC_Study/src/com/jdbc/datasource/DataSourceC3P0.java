package com.jdbc.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;
import com.JDBCUtils.JDBCUtils2;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * C3P0���ӳصĲ���
 */
public class DataSourceC3P0 {

	@Test
	/*
	 * ������JDBCUtils2�Ĳ���
	 */
	public void demo3(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//�����ӳ��л������
			conn = JDBCUtils2.getConnection();
			//��дsql
			String sql = "select * from account ";
			//Ԥ����
			pstmt = conn.prepareStatement(sql);
			//ִ��sql
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
	 * ���������ļ��O�Å���   ctrl+shift+f �������� 
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//�������ӳ�  Ĭ�Jȥ��·���²���c3p0-config.xml
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//�����ӳ��л������
			conn = dataSource.getConnection();
			//��дsql
			String sql = "select * from account";
			//Ԥ����
			pstmt = conn.prepareStatement(sql);
			//ִ��sql
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
	 * �ֶ����ò���
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//�����ӳ��л������
			//�������ӳ�
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			//�������Ӳ���
			dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=test");
			dataSource.setUser("sa");
			dataSource.setPassword("a");
			//�����ӳ��л������
			conn = dataSource.getConnection();
			//��дsql
			String sql = "select * from account";
			//Ԥ����
			pstmt = conn.prepareStatement(sql);
			//ִ��sql
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
