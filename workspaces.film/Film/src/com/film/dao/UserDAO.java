package com.film.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import com.film.utils.DbUtil;

/*
 * 操作用户表
 */
public class UserDAO {
/*
 * 用户注册
 */
	public static int register(String uname,String email,String pwd)throws Exception{
		int result = 0;
		//获取连接对象
		Connection conn = DbUtil.getConn();
		//编写sql语句 使用？代替外界传入的值
		String sql = "insert into tb_user values(null,?,?,?,1)";
		//获取预编译对象
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		pstmt.setObject(1, uname); //1表示第一个问号
		pstmt.setObject(2, email);
		pstmt.setObject(3, pwd);
		//执行更新操作
		result = pstmt.executeUpdate();
		//关闭编译对象
		DbUtil.closeAll(conn, pstmt, null);
		
		return result;
	}
	/*
	 * 修改密码
	 * uid 编号
	 * pwd 密码
	 */
	public static int updataPwd(int uid,String pwd) throws Exception{
		//声明整型变量
		int result = 0;
		//获取连接对象
		Connection conn = DbUtil.getConn();
		//编写sql语句 使用？代替外界传入的值
		String sql = "update tb_user set upwd = ? where uid = ?";
		//获取预编译对象
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		pstmt.setObject(1, pwd);
		pstmt.setObject(2, uid);
		//执行更新操作
		result = pstmt.executeUpdate();
		//关闭编译对象
		DbUtil.closeAll(conn, pstmt, null);
				
		return result;
	}
	/*
	 * 用户登录 最多返回一个数据
	 */
	public static Map<String, Object> login(String email,String pwd) throws Exception{
		//key value
		//uid->1 uname->u001 email->u001@qq.com upwd->a state->1
		//声明map变量
		Map<String,Object> map = null;
		//获取连接对象
		Connection conn = DbUtil.getConn();
		//编写sql语句 使用？代替外界传入的值
		String sql = "select uid,uname,upwd,email,state from tb_user where email = ? and upwd = ? and state = 1";
		//获取预编译对象
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		pstmt.setObject(1, email);
		pstmt.setObject(2, pwd);
		//执行查询操作 返回结果集对象
		ResultSet rs = pstmt.executeQuery();
		//对结果集处理
		if(rs.next()){
			//查询结果
			//创建map对象
			map = new HashMap<String,Object>();
			//对应字段值设置到map 获取对应字段的值rs.getObject(字段名称)
			map.put("uid", rs.getObject("uid"));
			map.put("uname", rs.getObject("uname"));
			map.put("email", rs.getObject("email"));
			map.put("upwd", rs.getObject("upwd"));
			map.put("state", rs.getObject("state"));
		}
		//关闭编译对象
		DbUtil.closeAll(conn, pstmt, rs);
		return map;
	}
	
	
	/*
	 * 根据邮箱查询
	 */
	public static Map<String,Object> findByEmail(String email) throws Exception{
		Map<String, Object> map = null;
		Connection conn = DbUtil.getConn();
		String sql = "select uid,uname,upwd,email,state from tb_user where email =? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, email);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			map = new HashMap<String,Object>();
			map.put("uid", rs.getObject("uid"));
			map.put("uname", rs.getObject("uname"));
			map.put("email", rs.getObject("email"));
			map.put("upwd", rs.getObject("upwd"));
			map.put("state", rs.getObject("state"));
		}
		//关闭资源
		DbUtil.closeAll(conn, pstmt, rs);
		return map;										
	}
	
}
