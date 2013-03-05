package com.formation.project.computerDatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.formation.project.computerDatabase.base.Computer;

public class ComputerRowMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int line) throws SQLException {
		ComputerResultSetExtractor extractor = new ComputerResultSetExtractor();
	    return extractor.extractData(rs);
	}
}
