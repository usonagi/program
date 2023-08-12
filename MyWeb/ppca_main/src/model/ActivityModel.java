package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Activity;
import utils.JDBCUtils;

/*
 * 处理Activity数据的JavaBean
 * ====查询操作  显示所有活动信息
 * 1、当home.jsp页面加载时就会向服务器发送请求获取Activity
 * 2、在servlet中处理请求，调用model完成信息查询，并将信息转换成json响应到浏览器。
 * 3、在浏览器端处理得到的json数据
 * 
 * ====添加操作 
 * 1、点击添加按钮后弹出窗口，在窗口录入相关信息
 * 2、将信息发送到服务器，在servlet中调用model完成数据库插入操作
 * 3、将新增信息在浏览器显示
 */
public class ActivityModel {

	private List<Activity> acts;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String SQL = "";

	// 根据id查询Activity ACT_INFO记录
	public Activity findById(String act_id) {
		Activity activity = null;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql	EXEC INFO_BYACTID 'yd003'
			SQL = "EXEC INFO_BYACTID ?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, act_id);
			// 执行sql
			rs = pstmt.executeQuery();

			if (rs.next()) {
				activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(pstmt, conn);
		}

		return activity;
	}

	//查询用户发布的所有活动  根据UNAME
	public List<Activity> findActDetail(String username) {
		Activity activity = null;
		acts = new ArrayList<Activity>();

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql		EXEC ACTINFOS '李华'
			SQL = "EXEC ACTINFOS ?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, username);
			// 执行sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
				acts.add(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(pstmt, conn);
		}

		return acts;
	}
	
	// 添加活动的方法 向ACTIVITY、ACT_INFO中同时添加一条记录
	public void addActivity(Activity act) {
		int addCount = 0;

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql EXEC ADD_ACTINFO 'test02','通天塔','tttt','sss','李华','测试活动',null
			SQL = "EXEC ADD_ACTINFO ?,?,?,?,?,?,?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, act.getAct_id());
			pstmt.setString(2, act.getAct_name());
			pstmt.setString(3, act.getAct_type());
			pstmt.setString(4, act.getAct_status());
			pstmt.setString(5, act.getUname());
			pstmt.setString(6, act.getIntrod());
			pstmt.setDate(7, act.getDeadline());
			//pstmt.setString(7, act.getDeadline());

			// 执行sql
			addCount = pstmt.executeUpdate();

			if (addCount == 0) {
				System.out.println("对数据库插入操作失败。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// 释放资源
			JDBCUtils.release(pstmt, conn);
		}

	}

	// 根据ACT_ID删除活动信息  删除ACTIVITY中的记录
	public void delActivity(String act_id) {
		int addCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//在数据库创建删除用的存储过程	EXEC DEL_ACTINFO 'yd002'
			SQL = "EXEC DEL_ACTINFO ?";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act_id);
			//执行sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("对数据库记录的删除操作失败。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
	}

	//修改活动信息
	public void updateActivity(Activity updateAct) {
		
		int updateCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql	EXEC UPDATE_ACTINFO 'yd003','足球','运动','未开始','比赛',null
			SQL = "EXEC UPDATE_ACTINFO ?,?,?,?,?,?";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, updateAct.getAct_id());
			pstmt.setString(2, updateAct.getAct_name());
			pstmt.setString(3, updateAct.getAct_type());
			pstmt.setString(4, updateAct.getAct_status());
			pstmt.setString(5, updateAct.getIntrod());
			//pstmt.setString(6, updateAct.getDeadline());
			pstmt.setDate(6, updateAct.getDeadline());
			
			//执行sql
			updateCount = pstmt.executeUpdate();
			
			if(updateCount == 0){
				System.out.println("对数据库修改操作失败。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
		
	}

	//根据活动名查询记录
	public List<Activity> findByActName(String username, String act_name) {
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			// 编写sql		EXEC INFO_BYACTNAME '李华','蓝球'
			SQL = "EXEC INFO_BYACTNAME ?,?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, username);
			pstmt.setString(2, act_name);
			// 执行sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Activity activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
				//System.out.println("日期:"+rs.getDate("DEADLINE"));
				acts.add(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(pstmt, conn);
		}
		
		return acts;
	}
	
	public List<Activity> findActName(String act_name) {
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			// 编写sql		EXEC VISITOR_FINDACT '测试'
			SQL = "EXEC VISITOR_FINDACT ?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, act_name);
			// 执行sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Activity activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
				acts.add(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			JDBCUtils.release(rs,pstmt, conn);
		}
		
		return acts;
	}
}
