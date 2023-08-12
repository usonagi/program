package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.Activity;
import domain.Admin;
import utils.JDBCUtils;

/*
 * 处理类ADMIN数据的JavaBean
 */
public class AdminModel {
	
	public Admin login(Admin admin) throws SQLException {
		
		Admin existAdmin = null;
		
		String ANO = admin.getAno();
		String APWD = admin.getApwd();
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "select * from ADMIN where ANO = ? and APWD = ?";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, ANO);
			pstmt.setString(2, APWD);
			//执行sql
			rs = pstmt.executeQuery();
			//遍历结果集
			if(rs.next()){
				existAdmin = new Admin(rs.getString("ANO"),rs.getString("APWD"),rs.getString("ANAME"));
			}else {
				existAdmin = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return existAdmin;
		
	}
}
