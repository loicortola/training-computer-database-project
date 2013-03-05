package com.formation.project.computerDatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;

public class ComputerResultSetExtractor implements ResultSetExtractor<Computer> {

	@Override
	public Computer extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Computer computer = null;
		computer = new ComputerBuilder()
						.id(rs.getInt("id_computer"))
						.name(rs.getString("computer_name"))
						.introduced(rs.getTimestamp("introduced"))
						.discontinued(rs.getTimestamp("discontinued"))
						.company(new Company(rs.getInt("id_company"),rs.getString("company_name")))
						.build();
		return computer;
	}

}
