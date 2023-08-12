package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.JDBCUtils.JDBCUtils;

public class UserDao {

	/*
	 * ʹ��PrepareStatement���sqlע��©��
	 */
	public boolean login(String username,String password){
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			String sql = "select * from U where username = ? and password = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//ִ��sql
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return flag;
	}
	
	/*
	 * �û���¼�ķ���
	 */
	/*public boolean login(String username,String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			// select * from U where username = ' ����' or '1=1 ' and password = ////;
			String sql = "select * from U where username = '"+username+"' and password = '"+password+"'";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return flag;
	}*/
}
