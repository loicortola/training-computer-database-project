package com.formation.project.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum JdbcDbStatsDao implements IStatsDao {
	
	INSTANCE;
	
	@Override
	public void logOperation(Integer computerId, String statType) {
		StringBuilder query	 = new StringBuilder();
		Connection conn 	 = DataSourceFactory.INSTANCE.getConn();
		PreparedStatement ps = null;
		
		try {
			query.append("INSERT INTO stats(id_computer,stat_type) ");
			query.append("VALUES(?,?);");
			ps = conn.prepareStatement(query.toString());
			ps.setInt(1, computerId);
			ps.setString(2, statType);
			ps.execute();
				
		} catch (SQLException e) {
			System.out.println("Error in logOperation:" +e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
	}

}
