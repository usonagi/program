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
	
	//�û���¼
	public User login(String username, String password) {
		
		existUser = null;
		
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			String sql = "select * from TUSER where UNAME = ? and UPWD = ?";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			//ִ��sql
			rs = pstmt.executeQuery();
			//���������
			if(rs.next()){
				
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
				/*existUser.setUsername(rs.getString("UNAME"));
				existUser.setPassword(rs.getString("UPWD"));*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return existUser;
	}

	//�û�ע��
	public void addUser(User newUser) {
		
		int result = 0;
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			String sql = "insert into TUSER(UNAME,UPWD,PHONE) values(?,?,?)";	//�û��������롢�ֻ���
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getPhone());
			//ִ��sql
			result = pstmt.executeUpdate();
			
			if(result != 0){
				System.out.println("�û�ע��ɹ�...");
				//return newUser;
			}else {
				System.out.println("�û�ע��ʧ��...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//�����û�����ѯ USER
	public User findByUname(String username) {

		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			String sql = "select * from TUSER where uname = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, username);
			// ִ��sql
			rs = pstmt.executeQuery();
			// �û����Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//�����ֻ��Ų�ѯ USER
	public User findByPhone(String phone) {

		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			String sql = "select * from TUSER where phone = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();
			// �û����Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	// ����phone�����û���Ϣ
	public void updateUser(User user, String method) {
		int count = 0;
		String sql = "";

		try {
			conn = JDBCUtils.getConnection();

			if ("updateUser".equals(method)) {
				// ��дsql update TUSER set SEX = '��' where PHONE='13333333333'
				sql = "update TUSER set UNAME=?,SEX=?,EMAIL=? where PHONE=?";
				// Ԥ����sql
				pstmt = conn.prepareStatement(sql);
				// ���ò���
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getSex());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getPhone());
				// ִ��sql
				count = pstmt.executeUpdate();

			} else if ("updateUserDetail".equals(method)) {
				System.out.println("�����޸���ϸ��Ϣ");
				// EXEC UPDATE_USERINFO '13333333323','��˼','����','233','realname','test111','cs','10'
				sql = "EXEC UPDATE_USERINFO ?,?,?,?,?,?,?,?";
				// Ԥ����sql
				pstmt = conn.prepareStatement(sql);
				// ���ò���
				pstmt.setString(1, user.getPhone());
				pstmt.setString(2, user.getUsername());
				pstmt.setString(3, user.getSex());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getRealname());
				pstmt.setString(6, user.getStuid());
				pstmt.setString(7, user.getMajor());
				pstmt.setInt(8, user.getAge());
				// ִ��sql
				count = pstmt.executeUpdate();
			}
			if (count == 0) {
				System.out.println("�޸��û���Ϣʧ��...");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//�����ֻ��� ��ѯ�û���ϸ��Ϣ
	public User getDetailByPhone(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql	ʹ�ô洢����	EXEC DETAILS 13333333313
			String sql = "EXEC DETAILS ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();
			// �û����Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("UNAME"), rs.getString("PHONE"), rs.getString("SEX"),
						rs.getString("EMAIL"),rs.getString("REALNAME"),rs.getString("STUID"),rs.getString("MAJOR"),rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//��ѯUSERINFO��
	public User findRealUser(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			String sql = "select * from USERINFO where phone = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();
			// �û����Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//	��ѯUSERINFO���� �Ƿ�����ѧ��
	public User findStuid(String stuid) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			String sql = "select * from USERINFO where STUID = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, stuid);
			// ִ��sql
			rs = pstmt.executeQuery();
			// ѧ���Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	// ��ѯUSERINFO�����Ƿ������ֻ���
	public User findPhone(String phone) {
		existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			String sql = "select * from USERINFO where phone = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();
			// �û����Ѵ��� ����
			if (rs.next()) {
				existUser = new User(rs.getString("PHONE"), rs.getString("REALNAME"),
						rs.getString("STUID"), rs.getString("MAJOR"), rs.getInt("AGE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}
	
	//��USERINFO������Ӽ�¼
	public void addUserInfo(User newUser) {
		int result = 0;
		
		try {
			conn = JDBCUtils.getConnection();
			//��дsql
			String sql = "insert into USERINFO values(?,?,?,?,?)";
			//Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, newUser.getRealname());
			pstmt.setInt(2, newUser.getAge());
			pstmt.setString(3, newUser.getPhone());
			pstmt.setString(4, newUser.getStuid());
			pstmt.setString(5, newUser.getMajor());
			//ִ��sql
			result = pstmt.executeUpdate();
			
			if(result != 0){
				System.out.println("�û���֤�ɹ�...");
			}else {
				System.out.println("�û���֤ʧ��...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
	}

}
