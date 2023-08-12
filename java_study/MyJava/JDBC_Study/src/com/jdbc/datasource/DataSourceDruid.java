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
 * 开源连接池Druid
 */
public class DataSourceDruid {

	@Test
	/*
	 * Druid的使用	配置方法设置参数
	 * Druid的配置方式可以使用属性文件配置，属性文件的key有规定。
	 */
	public void demo2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//使用连接池 1.0.9   1.2.8最新版本出错
			//从属性文件中获取
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/druid.properties"));	//这种引用方式不适应与web项目
			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			//获得连接
			conn = dataSource.getConnection();
			//传统方法
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
	 * Druid的使用	手动设置参数
	 */
	public void demo1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//使用连接池 1.0.9   1.2.8最新版本出错
			DruidDataSource dataSource = new DruidDataSource(); 
			//手动设置连接参数
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=test");
			dataSource.setUsername("sa");
			dataSource.setPassword("a");
			//获得连接
			conn = dataSource.getConnection();
			//传统方法
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
