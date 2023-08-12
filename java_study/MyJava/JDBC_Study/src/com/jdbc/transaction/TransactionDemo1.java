package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * 事务管理的测试类
 * 事务管理的API Connection
 * setAutoCommit设置为自|手动提交，rollback取消事务中进行的所有更改，commit提交事务
 */
public class TransactionDemo1 {

	@Test
	/*
	 * 完成转账的案例
	 */
	public void Transaction(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//获得连接
			conn = JDBCUtils.getConnection();
			//开启事务 设为手动提交
			conn.setAutoCommit(false);
			//编写sql
			String sql = "update account set money = money + ? where name = ?";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//扣除账号xxx 300
			pstmt.setFloat(1, -300);
			pstmt.setString(2, "xxx");
			pstmt.executeUpdate();
			//给账号xmm 添加300
			pstmt.setFloat(1, 300);
			pstmt.setString(2, "xmm");
			pstmt.executeUpdate();
			
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
}
