package com.formation.project.computerDatabase.dao;


import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;


public abstract class JdbcConnectionFactory{

	private static final String _dbName = "computerDatabaseProject_DB";
	private static Properties _connectionProps = null;

	public JdbcConnectionFactory()
	{

	}

	private static void initConnectionFactory() {

		_connectionProps = new Properties();
	    _connectionProps.put("user", "root");
	    _connectionProps.put("password", "password");

	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Error in JdbcConnectionFactory constructor: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConn()
	{ 
		Connection conn = null;
		if(_connectionProps == null)
			initConnectionFactory();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+_dbName, _connectionProps);
		} catch (Exception e) {			
			System.err.println("Error in getConn: " + e.getMessage());
		}
		return conn;
	}

	public static boolean closeConn(Connection conn)
	{
		if(_connectionProps == null)
			initConnectionFactory();
		try {
			conn.close();
			return true;
		}catch (Exception e) {
			System.err.println("Error in closeConn:" + e.getMessage());
		}
		return false;

	}
}