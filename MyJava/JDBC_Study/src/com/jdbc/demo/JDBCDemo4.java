package com.jdbc.demo;

import org.junit.Test;

/*
 * SQLע��©��
 */
public class JDBCDemo4 {

	@Test
	/*
	 * SQLע��©����ʾ
	 * ԭ���ڱ����д���SQL�ؼ��� "--"��"or"
	 * �������������PreparedStatement������ע��©��������SQLԤ�Ƚ��б��룬ʹ�ã���Ϊռλ����
	 */
	public void demo1(){
		UserDao userDao = new UserDao();
		boolean flag = userDao.login("test", "test");	//ʹ����ȷ���û���������
		//boolean flag = userDao.login("test' -- ", "test1");
		//boolean flag = userDao.login("test' or '1=1", "test2");	//�����У�
		if(flag){
			System.out.println("��¼�ɹ�");
		}else{
			System.out.println("��¼ʧ��");
		}
	}
}
