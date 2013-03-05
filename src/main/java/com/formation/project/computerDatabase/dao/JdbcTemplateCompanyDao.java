package com.formation.project.computerDatabase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.dao.mapper.CompanyRowMapper;

@Repository
public class JdbcTemplateCompanyDao implements ICompanyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateCompanyDao() {
	}

	@Override
	public Map<Integer,Company> getCompanies(String name) {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT * FROM company WHERE ? = (name OR '');");
		
		Map<Integer,Company> companies 	= new HashMap<Integer,Company>();
		List<Company> companiesList 	= new ArrayList<Company>();
		
		companiesList = jdbcTemplate.query(query.toString(),new Object[] { name }, new CompanyRowMapper());
		
		for (Company i : companiesList) companies.put(i.getId(),i);
		
		return companies;
	}

	@Override
	public Company getCompany(Integer companyId) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT * FROM company WHERE id_company = ?;");
		
		List<Company> companiesList 	= new ArrayList<Company>();
		
		companiesList = jdbcTemplate.query(query.toString(),new Object[] { companyId }, new CompanyRowMapper());
		
		if(companiesList.size() == 1)
			return companiesList.get(0);
		
		return null;
	}
}
