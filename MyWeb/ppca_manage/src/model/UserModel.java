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
	
	//���ֻ��Ų�ѯ
	public User findByPhone(String phone) {

		User existUser = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			sql = "select * from TUSER where phone = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				existUser = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return existUser;
	}

	//��ѯ���м�¼
	public List<User> findAllUser() {
		
		userList = new ArrayList<User>();
		User user = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql
			sql = "select * from TUSER order by UNO";
			//sql = "select COUNT(UNO) total from TUSER ";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ִ��sql
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return userList;
	}

	//����phone�����û���Ϣ
	public void updateUser(User user) {
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql	update TUSER set SEX = '��' where PHONE='13333333333'
			sql = "update TUSER set UNO=?,UNAME=?,UPWD=?,SEX=?,EMAIL=? where PHONE = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, user.getUno());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getSex());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPhone());
			// ִ��sql
			count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("�޸��û���Ϣʧ��...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

	}

	//����phoneɾ���û�  
	public void delUser(String phone) {
		int count = 0;
		
		try {
			
			if( isCertify(phone) ){
				//���²�����ʹ�ô洢�������ɾ�� EXEC DEL_USERINFO '13333333333'
				sql = "EXEC DEL_USERINFO ?";
			}else if( !isCertify(phone) ){
				// ��дsql	delete from TUSER where UPWD='123456'
				sql = "delete from TUSER where PHONE=?";
			}
			//�������ݿ�
			conn = JDBCUtils.getConnection();
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			count = pstmt.executeUpdate();
			
			if (count == 0) {
				System.out.println("ɾ���û���Ϣʧ��...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
	}

	//��ѯ�û��Ƿ�ʵ��
	public boolean isCertify(String phone) {
		//��USERINFO��ѯ��ɾ�����ֻ����Ƿ��м�¼
		try {
			conn = JDBCUtils.getConnection();
			// ��дsql select * from USERINFO where PHONE = '13333333313'
			sql = "select * from USERINFO where PHONE = ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setString(1, phone);
			// ִ��sql
			rs = pstmt.executeQuery();

			if ( rs.next() ) {
				return true;	//�м�¼��ʾ�Ѿ�ʵ��
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		return false;
	}
	
	//��ҳ��ѯ�û���Ϣ
	public List<User> findUserPage(int pageNO, int pageSize) {
		
		userList = new ArrayList<User>();
		User user = null;

		try {
			conn = JDBCUtils.getConnection();
			// ��дsql	select * from (select *,ROW_NUMBER() over (order by UNO ) as PNO from TUSER) temp where PNO >= 1 and PNO <= 6;
			sql = "select * from (select *,ROW_NUMBER() over (order by UNO ) as "
					+ "PNO from TUSER) temp where PNO >= ? and PNO <= ?";
			// Ԥ����sql
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			pstmt.setInt(1, (pageNO - 1)*pageSize+1);
			pstmt.setInt(2, (pageNO - 1)*pageSize+pageSize);
			// ִ��sql
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User(rs.getString("UNO"), rs.getString("UNAME"), rs.getString("UPWD"),
						rs.getString("SEX"), rs.getString("PHONE"), rs.getString("EMAIL"));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}

		return userList;
		
	}

	//��װ��ҳ���ݵķ���
	public PageBean getPages(int pageNO, int pageSize) {
		
		userList = findUserPage(pageNO,pageSize);
		
		PageBean<User> page = new PageBean<User>();
		page.setContent(userList);
		page.setPageNo(pageNO);
		page.setPageSize(pageSize);

		/*int totalCount = findTotalCount(); // �������������ñ��ѯ�õ�
		int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize); // ��ҳ����������������ȡ��
		page.setTotalPage(totalPage);
		page.setTotalCount(totalCount);*/
		
		return null;
	}
}
