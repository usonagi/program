package com.jdbc.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * JDBC������Ĳ���
 */
public class JDBCDemo3 {

	@Test
	/*
	 * ��ѯ������ʹ�ù�����
	 */
	public void demo1(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//�������
			conn = JDBCUtils.getConnection();
			//����ִ��sql����
			stmt = conn.createStatement();
			//��дSQL���
			String sql = "select * from U";
			//ִ��sql���
			rs = stmt.executeQuery(sql);
			//���������
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, stmt, conn);
		}
	}
}
