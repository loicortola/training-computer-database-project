package com.formation.project.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.dao.DataSourceFactory;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.DaoFactory;
import com.formation.project.computerDatabase.dao.IStatsDao;

public enum ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	INSTANCE;
	
	private IComputerDao computerDao = null;
	private ICompanyDao companyDao = null;
	private IStatsDao statsDao = null;

	private ComputerDatabaseServiceImpl() {
		
		computerDao = DaoFactory.INSTANCE.getComputerDao();
		companyDao 	= DaoFactory.INSTANCE.getCompanyDao();
		statsDao 	= DaoFactory.INSTANCE.getStatsDao();
	}

	@Override
	public void addComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		try {
			DataSourceFactory.INSTANCE.getConn().setAutoCommit(false);
			Integer lastInsertId = computerDao.addComputer(computer);
			statsDao.logOperation(lastInsertId, "add");
			DataSourceFactory.INSTANCE.getConn().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.addComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
		
	}

	@Override
	public void updateComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		try {
			DataSourceFactory.INSTANCE.getConn().setAutoCommit(false);
			computerDao.updateComputer(computer);
			statsDao.logOperation(computer.getId(), "update");
			DataSourceFactory.INSTANCE.getConn().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.updateComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
	}

	@Override
	public void deleteComputer(Integer computerId) {
		
		try {
			DataSourceFactory.INSTANCE.getConn().setAutoCommit(false);	
			computerDao.deleteComputer(computerId);
			statsDao.logOperation(computerId, "delete");
			DataSourceFactory.INSTANCE.getConn().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.deleteComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
	}

	@Override
	public Computer getComputer(Integer computerId) {
		Computer computer = computerDao.getComputer(computerId);
		DataSourceFactory.INSTANCE.closeConn();
		return computer;
	}

	@Override
	public Map<Integer, Company> getCompanies() {
		Map<Integer, Company> companies = companyDao.getCompanies("");
		DataSourceFactory.INSTANCE.closeConn();
		return companies;
	}

	@Override
	public List<Company> getCompaniesList() {
		List<Company> companies = new ArrayList<Company>(companyDao.getCompanies("").values());
		DataSourceFactory.INSTANCE.closeConn();
		return companies;
	}

	@Override
	public Computers getComputers(Integer currentPage,
			Integer resultsPerPage, TableSort sortBy, String name) {
		Computers computers = null;
		
		List<Computer> computersList = computerDao.getComputers(currentPage, resultsPerPage, sortBy.getSortString(),name);
		DataSourceFactory.INSTANCE.closeConn();
		Integer computerCount 		 = computerDao.getComputerCount(name);
		DataSourceFactory.INSTANCE.closeConn();
		
		computers = new Computers(computersList,computerCount,sortBy);
		
		return computers;
	}

	@Override
	public Company getCompany(Integer companyId) {
		Company company = companyDao.getCompany(companyId);
		DataSourceFactory.INSTANCE.closeConn();
		return company;
		
	}

	@Override
	public Integer getComputerCount(String name) {
		Integer count = computerDao.getComputerCount(name);
		DataSourceFactory.INSTANCE.closeConn();
		return count;
	}

}
