package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import domain.Activity;
import domain.PageBean;
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

	private List<Activity> acts ;
	
	Connection conn = null;
	PreparedStatement  pstmt = null;
	ResultSet rs = null;
	String SQL = "";
	
	//查询所有活动信息的方法
	public List<Activity> findAllActivity() {
		acts = new ArrayList<Activity>();
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			SQL = "select * from ACTIVITY order by ID";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//执行sql
			rs = pstmt.executeQuery();
			//遍历结果集
			while(rs.next()){
				Activity activity = new Activity(rs.getString("ACT_ID"),rs.getString("ACT_NAME"),
						rs.getString("UNAME"),rs.getString("ACT_TYPE"),rs.getString("ACT_STATUS"));
				
				acts.add(activity);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return acts;
	}

	//添加活动的方法
	public void addActivity(Activity act) {
		int addCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql	insert into ACTIVITY(ACT_ID,ACT_NAME,UNAME,ACT_TYPE,ACT_STATUS) values
			SQL = "insert into ACTIVITY(ACT_ID,ACT_NAME,UNAME,ACT_TYPE,ACT_STATUS) values(?,?,?,?,?)";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act.getAct_id());
			pstmt.setString(2, act.getAct_name());
			pstmt.setString(4, act.getAct_type());
			pstmt.setString(3, act.getUname());
			pstmt.setString(5, act.getAct_status());
			
			//执行sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("对数据库插入操作失败。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
		
	}

	//根据id查询Activity
	public Activity findById(String act_id) {
		Activity activity = null;
		
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			SQL = "select * from ACTIVITY where ACT_ID = ?";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act_id);
			//执行sql
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				activity = new Activity(rs.getString("ACT_ID"),rs.getString("ACT_NAME"),
						rs.getString("UNAME"),rs.getString("ACT_TYPE"),rs.getString("ACT_STATUS"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
		
		return activity;
	}

	//根据活动 的id修改 Activity
	public void updateActivity(Activity act) {
		int updateCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			SQL = "update ACTIVITY set ACT_NAME=?,ACT_TYPE=?,UNAME=?,ACT_STATUS=? where ACT_ID = ?;";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act.getAct_name());
			pstmt.setString(2, act.getAct_type());
			pstmt.setString(3, act.getUname());
			pstmt.setString(4, act.getAct_status());
			pstmt.setString(5, act.getAct_id());
			
			//执行sql
			updateCount = pstmt.executeUpdate();
			
			if(updateCount == 0){
				System.out.println("对数据库修改操作失败。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
	}

	//分页查询activity 将结果封装到JavaBean中
	public List<Activity> findByPage(int pageNO, int pageSize) {
		
		Activity activity = null;
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			//编写sql
			//String sql = "select * from products";
			
			//使用ROW_NUMBER函数，将id作为PNO的新表temp 指定PNO的范围
			SQL = "select * from (select *,ROW_NUMBER() over (order by id ) as PNO "
						+ "from ACTIVITY) temp where PNO >= ? and PNO <= ?";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);	//参数1--从哪条记录开始
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize); //参数2--每页显示多少条记录  即到哪条记录为止
			
			//执行sql
			rs = pstmt.executeQuery();
			while(rs.next()){
				activity = new Activity(rs.getString("ACT_ID"),rs.getString("ACT_NAME"),
						rs.getString("UNAME"),rs.getString("ACT_TYPE"),rs.getString("ACT_STATUS"));
				acts.add(activity);
			}
			
			/*System.out.println("ActModel:");
			for(int i=0;i<acts.size();i++)
			{
				Activity p = acts.get(i);
				System.out.println(p.getName());
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return acts;
	}
	
	//封装分页结果---记录、分页所需的数据
	public PageBean findPage(int pageNO, int pageSize) {

		List<Activity> list = findByPage(pageNO, pageSize);

		// 将分页数据封装到pagebean
		PageBean<Activity> pa = new PageBean<Activity>();
		pa.setContent(list);
		pa.setPageNo(pageNO);
		pa.setPageSize(pageSize);

		int totalCount = findTotalCount(); // 数据总条数调用表查询得到
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize); // 总页数根据总条数向上取整
		pa.setTotalPage(totalPage);
		pa.setTotalCount(totalCount);

		return pa;

	}

	// 查询Activity的总数量数量
	private int findTotalCount() {
		try {
			conn = JDBCUtils.getConnection();

			// 编写sql 通过count函数统计主键id的个数 total作为列名
			SQL = "select COUNT(ID) total from ACTIVITY";

			// 预编译sql
			pstmt = conn.prepareStatement(SQL);

			// 执行sql
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// System.out.println(rs.getInt("total"));
				return rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, pstmt, conn);
		}

		return 0;
	}

	//按照搜索框的内容查找 暂定UNAME
	public List<Activity> findByText(String arg) {
		Activity activity = null;
		acts = new ArrayList<Activity>();

		try {
			conn = JDBCUtils.getConnection();
			// 编写sql
			SQL = "select * from ACTIVITY where UNAME = ?";
			// 预编译sql
			pstmt = conn.prepareStatement(SQL);
			// 设置参数
			pstmt.setString(1, arg);
			// 执行sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity = new Activity(rs.getString("ACT_ID"), rs.getString("ACT_NAME"), rs.getString("UNAME"),
						rs.getString("ACT_TYPE"), rs.getString("ACT_STATUS"));
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

	//根据ACT_ID删除活动的方法
	public void delActivity(String act_id) {
		int addCount = 0;
		try {
			
			if( isDetail(act_id) ){
				//更新后的删除操作使用储存过程进行 EXEC DEL_ACTINFO ?
				SQL = "EXEC DEL_ACTINFO ?";
			}else if( !isDetail(act_id)){
				//编写sql	delete from ACTIVITY where ACT_ID = ?
				SQL = "delete from ACTIVITY where ACT_ID = ?";
			}
			
			conn = JDBCUtils.getConnection();
			
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act_id);
			//执行sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("对数据库删除操作失败。");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	public boolean isDetail(String act_id){
		try {
			conn = JDBCUtils.getConnection();
			//查询ACT_INFO表中是否有记录
			SQL = "select * from ACT_INFO where ACT_ID = ?";
			//预编译sql
			pstmt = conn.prepareStatement(SQL);
			//设置参数
			pstmt.setString(1, act_id);
			//执行sql
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
			JDBCUtils.release(rs, pstmt, conn);
		}
		return false;
	}

}
