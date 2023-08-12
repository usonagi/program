package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * JDBC��CRUD������ʹ��PreparedStatement����
 */
public class JDBCDemo5 {

	@Test
	/*
	 * ��ѯ����
	 */
	public void Search(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select * from U " ;// where id = ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, 5);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/*
	 * ɾ������
	 */
	public void Delete(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "delete from U where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 4);
			int num = pstmt.executeUpdate();
			if(num > 0){
				System.out.println("ɾ���ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/*
	 * �޸Ĳ���
	 */
	public void Update(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "update U set username = ?,password = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "dyg");
			pstmt.setString(2, "222");
			pstmt.setInt(3, 5);
			int num = pstmt.executeUpdate();
			if(num > 0){
				System.out.println("�޸ĳɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/*
	 * �������
	 */
	public void Insert(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//�������
			conn = JDBCUtils.getConnection();
			//��дSQL
			String sql = "insert into U values(null,?,?)";
			//Ԥ����SQL
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, "sdf");
			pstmt.setString(2, "123");
			//ִ��SQL
			int num = pstmt.executeUpdate();
			
			if(num > 0 ){
				System.out.println("�������ݳɹ���");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
}
