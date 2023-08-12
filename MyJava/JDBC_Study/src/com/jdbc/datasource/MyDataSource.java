package com.jdbc.datasource;


import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.JDBCUtils.JDBCUtils;

/*
 * �Զ������ӳ�
 */
public class MyDataSource implements DataSource {
	
	//�����Ӵ��뵽�ڴ��У�����һ�����ϣ����ڴ洢���Ӷ���
	private ArrayList<Connection> connLit = new ArrayList<Connection>();
	
	//�ڳ�ʼ����ʱ���ṩһЩ��
	public MyDataSource(){
		//��ʼ������
		for(int i=1; i <= 3; i++){
			//�򼯺��д�������
			connLit.add(JDBCUtils.getConnection());
		}
	}
	
	//�����ӳ��л�����ӵķ���
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = connLit.remove(0);
		
		return conn;
		
	}
	
	//��дһ���黹���ӵķ���
	public void addBack(Connection conn){
		connLit.add(conn);
		
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
