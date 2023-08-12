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
 * ����Activity���ݵ�JavaBean
 * ====��ѯ����  ��ʾ���л��Ϣ
 * 1����home.jspҳ�����ʱ�ͻ�����������������ȡActivity
 * 2����servlet�д������󣬵���model�����Ϣ��ѯ��������Ϣת����json��Ӧ���������
 * 3����������˴���õ���json����
 * 
 * ====��Ӳ��� 
 * 1�������Ӱ�ť�󵯳����ڣ��ڴ���¼�������Ϣ
 * 2������Ϣ���͵�����������servlet�е���model������ݿ�������
 * 3����������Ϣ���������ʾ
 */

public class ActivityModel {

	private List<Activity> acts ;
	
	Connection conn = null;
	PreparedStatement  pstmt = null;
	ResultSet rs = null;
	String SQL = "";
	
	//��ѯ���л��Ϣ�ķ���
	public List<Activity> findAllActivity() {
		acts = new ArrayList<Activity>();
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			SQL = "select * from ACTIVITY order by ID";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//ִ��sql
			rs = pstmt.executeQuery();
			//���������
			while(rs.next()){
				Activity activity = new Activity(rs.getString("ACT_ID"),rs.getString("ACT_NAME"),
						rs.getString("UNAME"),rs.getString("ACT_TYPE"),rs.getString("ACT_STATUS"));
				
				acts.add(activity);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return acts;
	}

	//��ӻ�ķ���
	public void addActivity(Activity act) {
		int addCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//��дsql	insert into ACTIVITY(ACT_ID,ACT_NAME,UNAME,ACT_TYPE,ACT_STATUS) values
			SQL = "insert into ACTIVITY(ACT_ID,ACT_NAME,UNAME,ACT_TYPE,ACT_STATUS) values(?,?,?,?,?)";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act.getAct_id());
			pstmt.setString(2, act.getAct_name());
			pstmt.setString(4, act.getAct_type());
			pstmt.setString(3, act.getUname());
			pstmt.setString(5, act.getAct_status());
			
			//ִ��sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("�����ݿ�������ʧ�ܡ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
		
	}

	//����id��ѯActivity
	public Activity findById(String act_id) {
		Activity activity = null;
		
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			SQL = "select * from ACTIVITY where ACT_ID = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act_id);
			//ִ��sql
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				activity = new Activity(rs.getString("ACT_ID"),rs.getString("ACT_NAME"),
						rs.getString("UNAME"),rs.getString("ACT_TYPE"),rs.getString("ACT_STATUS"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
		
		return activity;
	}

	//���ݻ ��id�޸� Activity
	public void updateActivity(Activity act) {
		int updateCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			SQL = "update ACTIVITY set ACT_NAME=?,ACT_TYPE=?,UNAME=?,ACT_STATUS=? where ACT_ID = ?;";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act.getAct_name());
			pstmt.setString(2, act.getAct_type());
			pstmt.setString(3, act.getUname());
			pstmt.setString(4, act.getAct_status());
			pstmt.setString(5, act.getAct_id());
			
			//ִ��sql
			updateCount = pstmt.executeUpdate();
			
			if(updateCount == 0){
				System.out.println("�����ݿ��޸Ĳ���ʧ�ܡ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
	}

	//��ҳ��ѯactivity �������װ��JavaBean��
	public List<Activity> findByPage(int pageNO, int pageSize) {
		
		Activity activity = null;
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			//String sql = "select * from products";
			
			//ʹ��ROW_NUMBER��������id��ΪPNO���±�temp ָ��PNO�ķ�Χ
			SQL = "select * from (select *,ROW_NUMBER() over (order by id ) as PNO "
						+ "from ACTIVITY) temp where PNO >= ? and PNO <= ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);	//����1--��������¼��ʼ
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize); //����2--ÿҳ��ʾ��������¼  ����������¼Ϊֹ
			
			//ִ��sql
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
	
	//��װ��ҳ���---��¼����ҳ���������
	public PageBean findPage(int pageNO, int pageSize) {

		List<Activity> list = findByPage(pageNO, pageSize);

		// ����ҳ���ݷ�װ��pagebean
		PageBean<Activity> pa = new PageBean<Activity>();
		pa.setContent(list);
		pa.setPageNo(pageNO);
		pa.setPageSize(pageSize);

		int totalCount = findTotalCount(); // �������������ñ��ѯ�õ�
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize); // ��ҳ����������������ȡ��
		pa.setTotalPage(totalPage);
		pa.setTotalCount(totalCount);

		return pa;

	}

	// ��ѯActivity������������
	private int findTotalCount() {
		try {
			conn = JDBCUtils.getConnection();

			// ��дsql ͨ��count����ͳ������id�ĸ��� total��Ϊ����
			SQL = "select COUNT(ID) total from ACTIVITY";

			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);

			// ִ��sql
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

	//��������������ݲ��� �ݶ�UNAME
	public List<Activity> findByText(String arg) {
		Activity activity = null;
		acts = new ArrayList<Activity>();

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			SQL = "select * from ACTIVITY where UNAME = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, arg);
			// ִ��sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				activity = new Activity(rs.getString("ACT_ID"), rs.getString("ACT_NAME"), rs.getString("UNAME"),
						rs.getString("ACT_TYPE"), rs.getString("ACT_STATUS"));
				acts.add(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}

		return acts;
	}

	//����ACT_IDɾ����ķ���
	public void delActivity(String act_id) {
		int addCount = 0;
		try {
			
			if( isDetail(act_id) ){
				//���º��ɾ������ʹ�ô�����̽��� EXEC DEL_ACTINFO ?
				SQL = "EXEC DEL_ACTINFO ?";
			}else if( !isDetail(act_id)){
				//��дsql	delete from ACTIVITY where ACT_ID = ?
				SQL = "delete from ACTIVITY where ACT_ID = ?";
			}
			
			conn = JDBCUtils.getConnection();
			
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act_id);
			//ִ��sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("�����ݿ�ɾ������ʧ�ܡ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
	}
	
	public boolean isDetail(String act_id){
		try {
			conn = JDBCUtils.getConnection();
			//��ѯACT_INFO�����Ƿ��м�¼
			SQL = "select * from ACT_INFO where ACT_ID = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act_id);
			//ִ��sql
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		return false;
	}

}
