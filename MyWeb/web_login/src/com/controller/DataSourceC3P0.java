package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.login.AccountException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import com.domain.User;
import com.utils.JDBCUtils;

/*
 * C3P0���ӳصĲ���
 */
public class DataSourceC3P0 {

	@Test
	/*
	 * ������JDBCUtils2�Ĳ���
	 */
	public void demo3() throws SQLException{
		/*QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		
		User user = queryRunner.query("select * from U where id = ?", new ResultSetHandler<User>(){

			@Override
			public User handle(ResultSet rs) throws SQLException {
				
				User user = new User();
				if(rs.next()){
					user.setUsername(rs.getString("username"));
				}
				
				return user;
			}
			
		},1);
		
		System.out.println(user);*/
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//�����ӳ��л������
			conn = JDBCUtils.getConnection();
			//��дsql
			String sql = "select * from U where username = 'xxs' ";
			//Ԥ����
			pstmt = conn.prepareStatement(sql);
			//ִ��sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("username")+rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
}
