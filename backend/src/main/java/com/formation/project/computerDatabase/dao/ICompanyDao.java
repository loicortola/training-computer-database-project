package com.formation.project.computerDatabase.dao;

import java.util.List;

import com.formation.project.computerDatabase.base.Company;

public interface ICompanyDao {
	public List<Company> getCompanies();
	public Company getCompany(Long companyId);
}
