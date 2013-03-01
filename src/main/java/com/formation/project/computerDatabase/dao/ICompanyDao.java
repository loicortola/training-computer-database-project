package com.formation.project.computerDatabase.dao;

import java.util.HashMap;

import com.formation.project.computerDatabase.base.Company;

public interface ICompanyDao {
	public Company getCompany(Integer companyId);
	public HashMap<Integer,Company> getCompanies(String name);
	
}
