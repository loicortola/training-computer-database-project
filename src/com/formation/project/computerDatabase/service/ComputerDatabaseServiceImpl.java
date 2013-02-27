package com.formation.project.computerDatabase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.DaoFactory;
import com.formation.project.computerDatabase.dao.IStatsDao;

public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	private DaoFactory daoFactory 		= null;
	private IComputerDao computerDao 	= null;
	private ICompanyDao companyDao		= null;
	private IStatsDao statsDao			= null;
	public ComputerDatabaseServiceImpl() {
		daoFactory 	= DaoFactory.getInstance();
		
		computerDao = daoFactory.getComputerDao();
		companyDao	= daoFactory.getCompanyDao();
		statsDao	= daoFactory.getStatsDao();
	}
	
	
	@Override
	public void addComputer(Computer computer) {
		if(computer.getName() == null || computer.getIntroduced() == null || computer.getCompany() == null)
    		throw new IllegalArgumentException();
		
		computerDao.addComputer(computer);
		Integer lastInsertId = computerDao.getLastInsertId();
		statsDao.logOperation(lastInsertId, "add");
	}
	
	@Override
	public void updateComputer(Computer computer) {
		if(computer.getName() == null || computer.getIntroduced() == null || computer.getCompany() == null)
    		throw new IllegalArgumentException();
		computerDao.updateComputer(computer);
		statsDao.logOperation(computer.getId(), "update");
	}
	
	@Override
	public void deleteComputer(Integer computerId) {
		computerDao.deleteComputer(computerId);
		statsDao.logOperation(computerId, "delete");
	}
	
	@Override
	public Computer getComputer(Integer computerId) {
		return computerDao.getComputer(computerId);
	}
	
	@Override
	public HashMap<Integer,Company> getCompanies() {
		return companyDao.getCompanies("");
	}
	
	@Override
	public ArrayList<Company> getCompaniesList() {
		return new ArrayList<Company>(companyDao.getCompanies("").values());
	}
	
	@Override
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, String sortBy, String name) {
		return computerDao.getComputers(currentPage, resultsPerPage, sortBy, name);
	}

	@Override
	public Company getCompany(Integer companyId) {
		return companyDao.getCompany(companyId);
	}

	@Override
	public Integer getComputerCount(String name) {
		return computerDao.getComputerCount(name);
	}
	
}
