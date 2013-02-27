package com.formation.project.computerDatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDbStatsDao implements IStatsDao {
	DaoFactory daoFactory = null;
	
	public JdbcDbStatsDao() {
	}
	
	public JdbcDbStatsDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	@Override
	public void logOperation(Integer computerId, String statType) {
		StringBuilder query	 = new StringBuilder();
		Connection conn 	 = daoFactory.getConn();
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
			DaoFactory.closeConn(conn);
		}
	}

}
