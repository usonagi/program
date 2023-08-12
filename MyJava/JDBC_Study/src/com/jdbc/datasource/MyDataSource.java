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
 * 自定义连接池
 */
public class MyDataSource implements DataSource {
	
	//将连接存入到内存中，定义一个集合，用于存储连接对象
	private ArrayList<Connection> connLit = new ArrayList<Connection>();
	
	//在初始化的时候提供一些连
	public MyDataSource(){
		//初始化连接
		for(int i=1; i <= 3; i++){
			//向集合中存入连接
			connLit.add(JDBCUtils.getConnection());
		}
	}
	
	//从连接池中获得连接的方法
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = connLit.remove(0);
		
		return conn;
		
	}
	
	//编写一个归还连接的方法
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
