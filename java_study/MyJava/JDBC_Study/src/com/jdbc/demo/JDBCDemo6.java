package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * ������Ĳ���
 */
public class JDBCDemo6 {

	@Test
	/*
	 * ������������
	 */
	public void demo2(){
		//��¼ʱ�俪��
		long begin = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//ʹ��ʱ��Ҫ�޸������ļ�  ��Ur�����ݿ�test1��
			conn = JDBCUtils.getConnection();
			String sql = "insert into Ur(name) values(?)";
			//Ԥ����SQL
			pstmt = conn.prepareStatement(sql);
			for(int i = 1; i <= 1000; i++){
				pstmt.setString(1, "name"+i);
				//��ӵ�������
				pstmt.addBatch();
				//ע�� ��ֹ�ڴ����
				if(i % 100 == 0){
					//ִ��������
					pstmt.executeBatch();
					//���������
					pstmt.clearBatch();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
		
		long end = System.currentTimeMillis();
		System.out.println((end-begin)/1000);
	}
	
	@Test
	/*
	 * ������Ļ�������
	 */
	public void demo1(){
		Connection conn = null;
		Statement stmt = null;
		try {
			//�������
			conn = JDBCUtils.getConnection();
			//����ִ�����������
			stmt = conn.createStatement();
			//��дһ��SQL���
			String sql1 = "create database test1";
			String sql2 = "use test1";
			//����Ϊidentity�󣬲�������ʱ����ָ�� id
			String sql3 = "create table Ur(id int primary key IDENTITY(1,1),name varchar(20))";
			String sql4 = "insert into Ur(name) values('aaa')";
			String sql5 = "insert into Ur(name) values('bbb')";
			String sql6 = "insert into Ur(name) values('ccc')";
			String sql7 = "update Ur set name = 'two' where id = 2";
			String sql8 = "delete from Ur where id = 3";
			//��ӵ�������
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.addBatch(sql5);
			stmt.addBatch(sql6);
			stmt.addBatch(sql7);
			stmt.addBatch(sql8);

			//ִ��������
			stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(stmt, conn);
		}
		
	}
}
