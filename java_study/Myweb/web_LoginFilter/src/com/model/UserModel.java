package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.domain.User;
import com.utils.JDBCUtils;

public class UserModel {

	public User login(User user) throws SQLException {
		/*QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from U where username = ? and password = ?";
		User existUser = queryRunner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		return existUser;*/
		
		User existUser = new User();
		String username = user.getUsername();
		String password = user.getPassword();
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			//±‡–¥sql
			String sql = "select * from U where username = ? and password = ?";
			//‘§±‡“Îsql
			pstmt = conn.prepareStatement(sql);
			//…Ë÷√≤Œ ˝
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//÷¥––sql
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
