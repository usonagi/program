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
 * ������ADMIN���ݵ�JavaBean
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
			//��дsql
			String sql = "select * from ADMIN where ANO = ? and APWD = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, ANO);
			pstmt.setString(2, APWD);
			//ִ��sql
			rs = pstmt.executeQuery();
			//���������
			if(rs.next()){
				existAdmin = new Admin(rs.getString("ANO"),rs.getString("APWD"),rs.getString("ANAME"));
			}else {
				existAdmin = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return existAdmin;
		
	}
}
