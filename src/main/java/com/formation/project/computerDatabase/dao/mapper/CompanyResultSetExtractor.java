package com.formation.project.computerDatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.formation.project.computerDatabase.base.Company;

public class CompanyResultSetExtractor implements ResultSetExtractor<Company> {

	@Override
	public Company extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		Company company = null;
		company = new Company(rs.getInt("id_company"),rs.getString("name"));
		return company;
	}

}
