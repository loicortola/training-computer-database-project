package com.formation.project.computerDatabase.dao;

import java.util.Map;

import com.formation.project.computerDatabase.base.Company;

public interface ICompanyDao {
	public Company getCompany(Integer companyId);
	public Map<Integer,Company> getCompanies(String name);
	
}
