package com.formation.project.computerDatabase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;

public interface IComputerDatabaseService {

	public abstract void addComputer(Computer computer);

	public abstract void updateComputer(Computer computer);

	public abstract void deleteComputer(Integer computerId);

	public abstract Computer getComputer(Integer computerId);
	
	public abstract Company getCompany(Integer companyId);

	public abstract List<Computer> getComputers();

	public abstract HashMap<Integer, Company> getCompanies();

	public abstract ArrayList<Company> getCompaniesList();

	public abstract List<Computer> getComputers(String name);

}