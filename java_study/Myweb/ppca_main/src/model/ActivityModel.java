package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Activity;
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

	private List<Activity> acts;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String SQL = "";

	// ����id��ѯActivity ACT_INFO��¼
	public Activity findById(String act_id) {
		Activity activity = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql	EXEC INFO_BYACTID 'yd003'
			SQL = "EXEC INFO_BYACTID ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, act_id);
			// ִ��sql
			rs = pstmt.executeQuery();

			if (rs.next()) {
				activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}

		return activity;
	}

	//��ѯ�û����������л  ����UNAME
	public List<Activity> findActDetail(String username) {
		Activity activity = null;
		acts = new ArrayList<Activity>();

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql		EXEC ACTINFOS '�'
			SQL = "EXEC ACTINFOS ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, username);
			// ִ��sql
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
			// �ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}

		return acts;
	}
	
	// ��ӻ�ķ��� ��ACTIVITY��ACT_INFO��ͬʱ���һ����¼
	public void addActivity(Activity act) {
		int addCount = 0;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql EXEC ADD_ACTINFO 'test02','ͨ����','tttt','sss','�','���Ի',null
			SQL = "EXEC ADD_ACTINFO ?,?,?,?,?,?,?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, act.getAct_id());
			pstmt.setString(2, act.getAct_name());
			pstmt.setString(3, act.getAct_type());
			pstmt.setString(4, act.getAct_status());
			pstmt.setString(5, act.getUname());
			pstmt.setString(6, act.getIntrod());
			pstmt.setDate(7, act.getDeadline());
			//pstmt.setString(7, act.getDeadline());

			// ִ��sql
			addCount = pstmt.executeUpdate();

			if (addCount == 0) {
				System.out.println("�����ݿ�������ʧ�ܡ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}

	}

	// ����ACT_IDɾ�����Ϣ  ɾ��ACTIVITY�еļ�¼
	public void delActivity(String act_id) {
		int addCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//�����ݿⴴ��ɾ���õĴ洢����	EXEC DEL_ACTINFO 'yd002'
			SQL = "EXEC DEL_ACTINFO ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, act_id);
			//ִ��sql
			addCount = pstmt.executeUpdate();
			
			if(addCount == 0){
				System.out.println("�����ݿ��¼��ɾ������ʧ�ܡ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
	}

	//�޸Ļ��Ϣ
	public void updateActivity(Activity updateAct) {
		
		int updateCount = 0;
		try {
			conn = JDBCUtils.getConnection();
			//��дsql	EXEC UPDATE_ACTINFO 'yd003','����','�˶�','δ��ʼ','����',null
			SQL = "EXEC UPDATE_ACTINFO ?,?,?,?,?,?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			//���ò���
			pstmt.setString(1, updateAct.getAct_id());
			pstmt.setString(2, updateAct.getAct_name());
			pstmt.setString(3, updateAct.getAct_type());
			pstmt.setString(4, updateAct.getAct_status());
			pstmt.setString(5, updateAct.getIntrod());
			//pstmt.setString(6, updateAct.getDeadline());
			pstmt.setDate(6, updateAct.getDeadline());
			
			//ִ��sql
			updateCount = pstmt.executeUpdate();
			
			if(updateCount == 0){
				System.out.println("�����ݿ��޸Ĳ���ʧ�ܡ�");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(pstmt, conn);
		}
		
	}

	//���ݻ����ѯ��¼
	public List<Activity> findByActName(String username, String act_name) {
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			// ��дsql		EXEC INFO_BYACTNAME '�','����'
			SQL = "EXEC INFO_BYACTNAME ?,?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, username);
			pstmt.setString(2, act_name);
			// ִ��sql
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Activity activity = new Activity(rs.getString("ACT_NAME"), rs.getString("UNAME"), rs.getString("ACT_TYPE"),
						rs.getString("ACT_STATUS"), rs.getInt("NUMBER"), rs.getString("INTROD"),rs.getDate("DEADLINE"));
				activity.setAct_id(rs.getString("ACT_ID"));
				//System.out.println("����:"+rs.getDate("DEADLINE"));
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
	
	public List<Activity> findActName(String act_name) {
		acts = new ArrayList<Activity>();
		
		try {
			conn = JDBCUtils.getConnection();
			// ��дsql		EXEC VISITOR_FINDACT '����'
			SQL = "EXEC VISITOR_FINDACT ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(SQL);
			// ���ò���
			pstmt.setString(1, act_name);
			// ִ��sql
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
			// �ͷ���Դ
			JDBCUtils.release(rs,pstmt, conn);
		}
		
		return acts;
	}
}
