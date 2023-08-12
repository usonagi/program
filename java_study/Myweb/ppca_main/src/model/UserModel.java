package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.User;
import utils.JDBCUtils;

public class UserModel {

	Connection conn = null;
	PreparedStatement  pstmt = null;
	ResultSet rs = null;
	User existUser = null;
	
	//用户登录
	public User login(String username, String password) {
		
		existUser = null;
		
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "select * from TUSER where UNAME = ? and UPWD = ?";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//执行sql
			rs = pstmt.executeQuery();
			//遍历结果集
			if(rs.next()){
				
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
				/*existUser.setUsername(rs.getString("UNAME"));
				existUser.setPassword(rs.getString("UPWD"));*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return existUser;
	}

	//用户注册
	public void addUser(User newUser) {
		
		int result = 0;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "insert into TUSER(UNAME,UPWD,PHONE) values(?,?,?)";	//用户名、密码、手机号
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getPhone());
			//执行sql
			result = pstmt.executeUpdate();
			
			if(result != 0){
				System.out.println("用户注册成功...");
				//return newUser;
			}else {
				System.out.println("用户注册失败...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//根据用户名查询 USER
	public User findByUname(String username) {

		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			String sql = "select * from TUSER where uname = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, username);
			// 执行sql
			rs = pstmt.executeQuery();
			// 用户名已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//根据手机号查询 USER
	public User findByPhone(String phone) {

		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			String sql = "select * from TUSER where phone = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();
			// 用户名已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	// 根据phone更新用户信息
	public void updateUser(User user, String method) {
		int count = 0;
		String sql = "";

		try {
			conn = JDBCUtils.getConnection();

			if ("updateUser".equals(method)) {
				// 编写sql update TUSER set SEX = '男' where PHONE='13333333333'
				sql = "update TUSER set UNAME=?,SEX=?,EMAIL=? where PHONE=?";
				// 预编译sql
				pstmt = conn.prepareStatement(sql);
				// 设置参数
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getSex());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getPhone());
				// 执行sql
				count = pstmt.executeUpdate();

			} else if ("updateUserDetail".equals(method)) {
				System.out.println("进入修改详细信息");
				// EXEC UPDATE_USERINFO '13333333323','张思','保密','233','realname','test111','cs','10'
				sql = "EXEC UPDATE_USERINFO ?,?,?,?,?,?,?,?";
				// 预编译sql
				pstmt = conn.prepareStatement(sql);
				// 设置参数
				pstmt.setString(1, user.getPhone());
				pstmt.setString(2, user.getUsername());
				pstmt.setString(3, user.getSex());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getRealname());
				pstmt.setString(6, user.getStuid());
				pstmt.setString(7, user.getMajor());
				pstmt.setInt(8, user.getAge());
				// 执行sql
				count = pstmt.executeUpdate();
			}
			if (count == 0) {
				System.out.println("修改用户信息失败...");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//根据手机号 查询用户详细信息
	public User getDetailByPhone(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql	使用存储过程	EXEC DETAILS 13333333313
			String sql = "EXEC DETAILS ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();
			// 用户名已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"),rs.getString("REALNAME"),rs.getString("STUID"),rs.getString("MAJOR"),rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//查询USERINFO表
	public User findRealUser(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			String sql = "select * from USERINFO where phone = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();
			// 用户名已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//	查询USERINFO表中 是否已有学号
	public User findStuid(String stuid) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			String sql = "select * from USERINFO where STUID = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, stuid);
			// 执行sql
			rs = pstmt.executeQuery();
			// 学号已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	// 查询USERINFO表中是否已有手机号
	public User findPhone(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			String sql = "select * from USERINFO where phone = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();
			// 用户名已存在 返回
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	//向USERINFO表中添加记录
	public void addUserInfo(User newUser) {
		int result = 0;
		
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			String sql = "insert into USERINFO values(?,?,?,?,?)";
			//预编译sql
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, newUser.getRealname());
			pstmt.setInt(2, newUser.getAge());
			pstmt.setString(3, newUser.getPhone());
			pstmt.setString(4, newUser.getStuid());
			pstmt.setString(5, newUser.getMajor());
			//执行sql
			result = pstmt.executeUpdate();
			
			if(result != 0){
				System.out.println("用户认证成功...");
			}else {
				System.out.println("用户认证失败...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		
	}

}
