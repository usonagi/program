package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.JDBCUtils.JDBCUtils;

public class UserDao {

	/*
	 * 使用PrepareStatement解决sql注入漏洞
	 */
	public boolean login(String username,String password){
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "select * from U where username = ? and password = ?";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//执行sql
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
	 * 用户登录的方法
	 */
	/*public boolean login(String username,String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			// select * from U where username = ' 变量' or '1=1 ' and password = ////;
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
