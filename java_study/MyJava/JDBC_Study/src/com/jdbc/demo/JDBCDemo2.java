package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/*
 * JDBC��CRUD�Ĳ���
 */
public class JDBCDemo2 {

	@Test
	/*
	 * ��ѯһ����¼
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
	 * ��ѯ������¼����
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
	 * ɾ������
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
				System.out.println("ɾ���ɹ���");
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
	 * �޸Ĳ���
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
				System.out.println("�޸ĳɹ���");
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
	 * ��������Ĵ���ʵ��
	 */
	public void demo1() {
		
		Connection conn = null;
		Statement stm = null;
		
		try{
			//ע������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//�������
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
			//ִ�в�����
			//����ִ��sql���Ķ���
			stm = conn.createStatement();
			//��дsql���
			String sql = "insert into U values(4,'xw','123')";
			//ִ��sql���
			int num = stm.executeUpdate(sql);
			if(num > 0 ){
				System.out.println("����ɹ���");
			}
			
		}catch(Exception e){	
			e.printStackTrace();
		}finally {
			//��Դ�ͷ�
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
