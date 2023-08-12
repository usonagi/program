package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.domain.User;
import com.utils.JDBCUtils;

/**
 * 处理数据的JavaBean
 */
public class UserModel {
	
	@Test
	public User login(User user) throws SQLException {
		
		User existUser = new User();
		
		// 连接数据库：通过传入的用户名和密码去数据库中进行查询
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
