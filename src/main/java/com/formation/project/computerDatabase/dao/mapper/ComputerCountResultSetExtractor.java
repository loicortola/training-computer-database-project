package com.formation.project.computerDatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ComputerCountResultSetExtractor implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		return rs.getInt(1);
	}
	

}
