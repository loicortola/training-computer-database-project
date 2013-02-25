package com.formation.project.computerDatabase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.CompanyDao;
import com.formation.project.computerDatabase.dao.ComputerDao;
import com.formation.project.computerDatabase.dao.JdbcDbCompanyDao;
import com.formation.project.computerDatabase.dao.JdbcDbComputerDao;

public class ComputerDatabaseService {
	private ComputerDao computerDao = null;
	private CompanyDao companyDao = null;
	
	public ComputerDatabaseService() {
		computerDao = new JdbcDbComputerDao();
		companyDao 	= new JdbcDbCompanyDao();
	}
	
	public void addComputer(Computer computer) {
		computerDao.addComputer(computer);
	}
	public void updateComputer(Computer computer) {
		computerDao.updateComputer(computer);
	}
	public void deleteComputer(Integer computerId) {
		computerDao.deleteComputer(computerId);
	}
	public Computer getComputer(Integer computerId) {
		return computerDao.getComputer(computerId);
	}
	public List<Computer> getComputers() {
		return computerDao.getComputers("");
	}
	public HashMap<Integer,Company> getCompanies() {
		return companyDao.getCompanies("");
	}
	public ArrayList<Company> getCompaniesList() {
		return new ArrayList<Company>(companyDao.getCompanies("").values());
	}
	public List<Computer> getComputers(String name) {
		return computerDao.getComputers(name);
	}
	
}
