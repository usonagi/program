package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/*
 * JDBC�����ų���
 * API ���
 * DriverManager�� ����������   ���ã�ע��������  ��ȡ����
 * 	Class.forName(""com.microsoft.sqlserver.jdbc.SQLServerDriver"");��������
 * 	getConnection(url,username,password);��������ݿ������
 * 
 * Connection: �����ݿ����Ӷ���   ���ã�������ִ��sql���Ķ���  ��������
 * 	ִ��sql������Statementִ��SQL��CallableStatementִ�д洢���̣�PrepareStatementִ��SQL����SQLԤ�������©��
 * 
 * Statement��ִ��SQL   ���ã�ִ��SQL ��ִ�������� 
 * 	execute(sql)ִ�в�ѯ���޸ġ���ӡ�ɾ����sql���,excuteQuery(sql)ִ�в�ѯ,excuteUpdate(sql)ִ���޸ġ���ӡ�ɾ�����;
 * 
 * ResultSet��ͨ��select���Ľ����    
 * 	ʹ��.next()���������������ʹ��getXXX()������ȡ�����
 * 
 * �ͷ���Դ��ResultSet��Statement��Connection����Դ�ͷŴ���д��finally��
 */
public class JDBCDemo1 {
	@Test
	public void demo1() throws Exception{
		//	��������
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		//	�������  url:�����ݿ����ӵ�·��  user��password--�����ݿ����ӵ��û���������
		/*url��ַ��jdbc:mysql://loacalhost:3306/test
		 * jdbc:�������ݿ��Э��  sqlserver:��jdbc����Э��  localhost���������ݿ��������������ַ��
		 * 					�Ǳ�������д��localhost�����Ǳ�������Ҫд��������IP��ַ��
		 * 3306��sqlserver���ݿ�������Ķ˿ں�
		 *   */
		Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=test", "sa", "a");
		
		//	��������	��ȡִ��SQl���Ķ���  ��дsql���  ִ��sql���
		Statement statement = con.createStatement();
		String sql = "select * from U";
		ResultSet res = statement.executeQuery(sql);
		
		while (res.next()){	//���������
			System.out.print(res.getInt("id")+" ");
			System.out.print(res.getString("username")+" ");
			System.out.print(res.getString("password")+" ");
			System.out.println();
		}
		
		//	�ͷ���Դ
		res.close();
		statement.close();
		con.close();

	}

}
