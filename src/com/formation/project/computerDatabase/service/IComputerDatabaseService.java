package com.formation.project.computerDatabase.service;

import java.util.List;
import java.util.Map;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;

public interface IComputerDatabaseService {

	public abstract void addComputer(Computer computer);

	public abstract void updateComputer(Computer computer);

	public abstract void deleteComputer(Integer computerId);

	public abstract Computer getComputer(Integer computerId);
	
	public abstract Integer getComputerCount(String name);	
	
	public abstract Company getCompany(Integer companyId);

	public abstract Map<Integer, Company> getCompanies();

	public abstract List<Company> getCompaniesList();

	public abstract Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name);

}