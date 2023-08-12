package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * �������Ĳ�����
 * ��������API Connection
 * setAutoCommit����Ϊ��|�ֶ��ύ��rollbackȡ�������н��е����и��ģ�commit�ύ����
 */
public class TransactionDemo1 {

	@Test
	/*
	 * ���ת�˵İ���
	 */
	public void Transaction(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//�������
			conn = JDBCUtils.getConnection();
			//�������� ��Ϊ�ֶ��ύ
			conn.setAutoCommit(false);
			//��дsql
			String sql = "update account set money = money + ? where name = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//�۳��˺�xxx 300
			pstmt.setFloat(1, -300);
			pstmt.setString(2, "xxx");
			pstmt.executeUpdate();
			//���˺�xmm ���300
			pstmt.setFloat(1, 300);
			pstmt.setString(2, "xmm");
			pstmt.executeUpdate();
			
			//�ύ����
			conn.commit();
		} catch (Exception e) {
			//�ع�����
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
