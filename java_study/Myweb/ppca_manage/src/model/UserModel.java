package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PageBean;
import domain.User;
import utils.JDBCUtils;

public class UserModel {

	Connection conn = null;
	PreparedStatement  pstmt = null;
	ResultSet rs = null;
	List<User> userList = null;
	String sql = "";
	
	//按手机号查询
	public User findByPhone(String phone) {

		User existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			sql = "select * from TUSER where phone = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				existUser = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//查询所有记录
	public List<User> findAllUser() {
		
		userList = new ArrayList<User>();
		User user = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			sql = "select * from TUSER order by UNO";
			//sql = "select COUNT(UNO) total from TUSER ";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 执行sql
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return userList;
	}

	//根据phone更新用户信息
	public void updateUser(User user) {
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql	update TUSER set SEX = '男' where PHONE='13333333333'
			sql = "update TUSER set UNO=?,UNAME=?,UPWD=?,SEX=?,EMAIL=? where PHONE = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, user.getUno());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getSex());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhone());
			// 执行sql
			count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("修改用户信息失败...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//根据phone删除用户  
	public void delUser(String phone) {
		int count = 0;
		
		try {
			
			if( isCertify(phone) ){
				//更新操作，使用存储过程完成删除 EXEC DEL_USERINFO '13333333333'
				sql = "EXEC DEL_USERINFO ?";
			}else if( !isCertify(phone) ){
				// 编写sql	delete from TUSER where UPWD='123456'
				sql = "delete from TUSER where PHONE=?";
			}
			//连接数据库
			conn = JDBCUtils.getConnection();
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("删除用户信息失败...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		
	}

	//查询用户是否实名
	public boolean isCertify(String phone) {
		//在USERINFO查询被删除的手机号是否有记录
		try {
			conn = JDBCUtils.getConnection();
			// 编写sql select * from USERINFO where PHONE = '13333333313'
			sql = "select * from USERINFO where PHONE = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, phone);
			// 执行sql
			rs = pstmt.executeQuery();

			if ( rs.next() ) {
				return true;	//有记录表示已经实名
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		return false;
	}
	
	//分页查询用户信息
	public List<User> findUserPage(int pageNO, int pageSize) {
		
		userList = new ArrayList<User>();
		User user = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql	select * from (select *,ROW_NUMBER() over (order by UNO ) as PNO from TUSER) temp where PNO >= 1 and PNO <= 6;
			sql = "select * from (select *,ROW_NUMBER() over (order by UNO ) as "
					+ "PNO from TUSER) temp where PNO >= ? and PNO <= ?";
			// 预编译sql
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize);
			// 执行sql
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}

		return userList;
		
	}

	//封装分页数据的方法
	public PageBean getPages(int pageNO, int pageSize) {
		
		userList = findUserPage(pageNO,pageSize);
		
		PageBean<User> page = new PageBean<User>();
		page.setContent(userList);
		page.setPageNo(pageNO);
		page.setPageSize(pageSize);

		/*int totalCount = findTotalCount(); // 数据总条数调用表查询得到
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize); // 总页数根据总条数向上取整
		page.setTotalPage(totalPage);
		page.setTotalCount(totalCount);*/
		
		return null;
	}
}
