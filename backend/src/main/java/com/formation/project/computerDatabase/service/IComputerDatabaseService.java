package com.formation.project.computerDatabase.service;

import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;

public interface IComputerDatabaseService {

	void addComputer(Computer computer);

	void updateComputer(Computer computer);

	void deleteComputer(Long computerId);

	Computer getComputer(Long computerId);
	
	Company getCompany(Long companyId);

	List<Company> getCompaniesList();

	Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name);

}