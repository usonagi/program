package com.dbutils;

import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.junit.Test;

import com.JDBCUtils.JDBCUtils2;

/*
 * DBUtils的增删改操作	目前不能用 会报错NoClassDefFoundError
 */
public class DbUtilsDemo {
	
	/*@Test
	
	// 添加操作
	 
	public void Insert() throws SQLException{
		//创建核心类：QueryRunner
		QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
		//编写sql语句
		queryRunner.update("insert into Ur values(?,?)", 3,"zs");
		//queryRunner.update("delete from account where id = ?", 3);
	}*/
	
	/*@Test
	
	 // 修改操作
	 
	public void Update() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
		queryRunner.update("update account set name = ?,money = ? where id = ?", "zz", 7000, 3);
	}*/
	
	/*public static void main(String[] args) throws SQLException {
		search();
	}
	*/
	
	@Test
	/*
	 * 查询操作
	 */
	public void search() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(JDBCUtils2.getDataSource());
		Object[] objs = queryRunner.query("select * from account where id = ?", new ArrayHandler(),2);
		System.out.println(Arrays.toString(objs));
		
	}
	
	
}
