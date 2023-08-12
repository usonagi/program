package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.domain.User;
import com.utils.JDBCUtils;

/**
 * �������ݵ�JavaBean
 */
public class UserModel {
	
	@Test
	public User login(User user) throws SQLException {
		
		User existUser = new User();
		
		// �������ݿ⣺ͨ��������û���������ȥ���ݿ��н��в�ѯ
		//QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		/*User existUser = queryRunner.query("select * from U where username = 'xxs'",
				new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());*/
		
		String username = user.getUsername();
		String password = user.getPassword();
		
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
				existUser = user;
			}else {
				existUser = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return existUser;
		
	}

}
