package com.formation.project.computerDatabase.dao;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;


public class JdbcDbHandler{

	private static final String _dbName = "computerDatabaseProject_DB";
	private Properties _connectionProps = null;

	public JdbcDbHandler()
	{
		_connectionProps = new Properties();
	    _connectionProps.put("user", "root");
	    _connectionProps.put("password", "password");


	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Error in JdbcDbHandler constructor: " + e.getMessage());
			e.printStackTrace();
		}


	}

	public Connection getConn()
	{ 
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+_dbName, _connectionProps);
		} catch (Exception e) {			
			System.err.println("Error in getConn: " + e.getMessage());
		}
		return conn;
	}

	public ArrayList<ArrayList<String>> rsToArrayList(ResultSet rs)
	{
		ArrayList<ArrayList<String>> _result = new ArrayList<ArrayList<String>>();

		_result.add(new ArrayList<String>());

		return _result;
	}

	public boolean closeConn(Connection conn)
	{
		try {
			conn.close();
			return true;
		}catch (Exception e) {
			System.err.println("Error in closeConn:" + e.getMessage());
		}
		return false;

	}
}