package com.jdbc.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * 自定义连接池的测试类
 */
public class DataSourceDemo {
	
	@Test
	public void demo1(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyDataSource dataSource = null;
		try {
			//获得连接
			//Connection conn = null;
			dataSource = new MyDataSource();
			conn = dataSource.getConnection();
			conn = JDBCUtils.getConnection();
			String sql = "select * from account";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id")+" " +rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//JDBCUtils.release(rs, pstmt, conn);
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//归还连接
			dataSource.addBack(conn);
		}
	}
}
