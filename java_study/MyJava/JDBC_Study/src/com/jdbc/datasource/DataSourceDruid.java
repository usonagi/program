package com.jdbc.datasource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/*
 * ��Դ���ӳ�Druid
 */
public class DataSourceDruid {

	@Test
	/*
	 * Druid��ʹ��	���÷������ò���
	 * Druid�����÷�ʽ����ʹ�������ļ����ã������ļ���key�й涨��
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//ʹ�����ӳ� 1.0.9   1.2.8���°汾����
			//�������ļ��л�ȡ
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/druid.properties"));	//�������÷�ʽ����Ӧ��web��Ŀ
			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			//�������
			conn = dataSource.getConnection();
			//��ͳ����
			//conn = JDBCUtils.getConnection();
			String sql = "select * from account";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
	@Test
	/*
	 * Druid��ʹ��	�ֶ����ò���
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//ʹ�����ӳ� 1.0.9   1.2.8���°汾����
			DruidDataSource dataSource = new DruidDataSource(); 
			//�ֶ��������Ӳ���
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=test");
			dataSource.setUsername("sa");
			dataSource.setPassword("a");
			//�������
			conn = dataSource.getConnection();
			//��ͳ����
			//conn = JDBCUtils.getConnection();
			String sql = "select * from account";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getFloat("money"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
	}
	
}
