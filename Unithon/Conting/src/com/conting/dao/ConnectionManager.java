package com.conting.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * 
 * @since 	2016. 2. 13.
 * @version	
 * @author 	Yoon JiSoo
 */
public class ConnectionManager {
	private Connection conn = null;
	
	public void connect() throws Exception {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
			conn = ds.getConnection();
		} catch(SQLException e) {
			System.out.println("ConnectionManager : [ connect error ]");
			e.printStackTrace();
		}
	}
	
	public void disconnect() throws Exception {
		try {
			if(conn != null)
				conn.close();
		} catch(SQLException e) {
			System.out.println("ConnectionManager : [ disconnect error ]");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws Exception {
		return conn;
	}
}
