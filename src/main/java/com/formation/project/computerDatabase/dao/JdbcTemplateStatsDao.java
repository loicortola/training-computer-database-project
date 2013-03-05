package com.formation.project.computerDatabase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateStatsDao implements IStatsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateStatsDao() {
		
	}
	
	@Override
	public void logOperation(Integer computerId, String statType) {
		StringBuilder query	 = new StringBuilder();
		
		query.append("INSERT INTO stats(id_computer,stat_type) ");
		query.append("VALUES(?,?);");
		
		jdbcTemplate.update(query.toString(),new Object[] {computerId, statType});
	}

}
