package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * JDBC的CRUD操作：使用PreparedStatement对象
 */
public class JDBCDemo5 {

	@Test
	/*
	 * 查询操作
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
	 * 删除操作
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
				System.out.println("删除成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/*
	 * 修改操作
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
				System.out.println("修改成功。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	@Test
	/*
	 * 插入操作
	 */
	public void Insert(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//获得连接
			conn = JDBCUtils.getConnection();
			//编写SQL
			String sql = "insert into U values(null,?,?)";
			//预编译SQL
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, "sdf");
			pstmt.setString(2, "123");
			//执行SQL
			int num = pstmt.executeUpdate();
			
			if(num > 0 ){
				System.out.println("插入数据成功。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
}
