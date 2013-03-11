package com.formation.project.computerDatabase.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;

@Repository
public class JpaCompanyDao implements ICompanyDao {
	
	@Autowired
	private CompanyRepository repo;

	
	@Override
	public List<Company> getCompanies() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return repo.findAll(sort);
	}
	
	@Override
	public Company getCompany(Long id) {
		return repo.findOne(id);
	}

	
}
