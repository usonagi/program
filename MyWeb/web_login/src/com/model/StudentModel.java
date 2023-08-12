package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.domain.Student;
import com.utils.JDBCUtils;

/*
 * 处理数据的Java类
 */
public class StudentModel {

	List<Student> list = new ArrayList<Student>();
	
	//查询所有信息的方法
	@Test
	public List<Student> finAll() throws SQLException {
		//使用此方法报错
		/*QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		List<Student> list = queryRunner.query("select * from student", new BeanListHandler<Student>(Student.class));*/
		
		
		Connection conn = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "select * from student";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				student = setStudent(rs.getInt("sid"), rs.getString("sname"), rs.getString("sex"), rs.getInt("age"));
				list.add(student);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	public Student setStudent(int id,String name,String sex,int age){
		Student student = new Student();
		student.setSid(id);
		student.setSname(name);
		student.setSex(sex);
		student.setAge(age);
		return student;
	}

}
