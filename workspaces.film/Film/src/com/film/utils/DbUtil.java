package com.film.utils;

//导包
import java.sql.*; //


/*
 * 数据库连接的工具类
 */
public class DbUtil {
	//驱动注册
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//定义方法
	//获取连接的方法
	public static Connection getConn(){
		//驱动管理器获取连接对象
		Connection coon = null;
		/*
		 * url:待添加
		 */
		try {
			coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_film", "root", "a");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coon;
	}
	
	/*public static void main(String []arge){
		System.out.println(getConn().getClass().getName());
	}*/
	
	
	/*关闭所有资源
	 * 
	 */
	public static void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) throws Exception{
		
		//关闭结果集对象
		if(null != rs){
			rs.close();
		}
		//关闭预编译对象
		if(null != pstmt){
			pstmt.close();
		}
		//关闭连接对象
		if(null != conn){
			conn.close();
		}
	}

}
