package com.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

import com.JDBCUtils.JDBCUtils;

/*
 * 批处理的操作
 */
public class JDBCDemo6 {

	@Test
	/*
	 * 批量插入数据
	 */
	public void demo2(){
		//记录时间开销
		long begin = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//使用时需要修改配置文件  表Ur在数据库test1中
			conn = JDBCUtils.getConnection();
			String sql = "insert into Ur(name) values(?)";
			//预编译SQL
			pstmt = conn.prepareStatement(sql);
			for(int i = 1; i <= 1000; i++){
				pstmt.setString(1, "name"+i);
				//添加到批处理
				pstmt.addBatch();
				//注意 防止内存溢出
				if(i % 100 == 0){
					//执行批处理
					pstmt.executeBatch();
					//清空批处理
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
	 * 批处理的基本操作
	 */
	public void demo1(){
		Connection conn = null;
		Statement stmt = null;
		try {
			//获得连接
			conn = JDBCUtils.getConnection();
			//创建执行批处理对象
			stmt = conn.createStatement();
			//编写一批SQL语句
			String sql1 = "create database test1";
			String sql2 = "use test1";
			//设置为identity后，插入数据时不能指定 id
			String sql3 = "create table Ur(id int primary key IDENTITY(1,1),name varchar(20))";
			String sql4 = "insert into Ur(name) values('aaa')";
			String sql5 = "insert into Ur(name) values('bbb')";
			String sql6 = "insert into Ur(name) values('ccc')";
			String sql7 = "update Ur set name = 'two' where id = 2";
			String sql8 = "delete from Ur where id = 3";
			//添加到批处理
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.addBatch(sql3);
			stmt.addBatch(sql4);
			stmt.addBatch(sql5);
			stmt.addBatch(sql6);
			stmt.addBatch(sql7);
			stmt.addBatch(sql8);

			//执行批处理
			stmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(stmt, conn);
		}
		
	}
}
