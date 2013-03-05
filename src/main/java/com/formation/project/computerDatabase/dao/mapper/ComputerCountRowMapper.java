package com.formation.project.computerDatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ComputerCountRowMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int line) throws SQLException {
		ComputerCountResultSetExtractor extractor = new ComputerCountResultSetExtractor();
	    return extractor.extractData(rs);
	}
}
