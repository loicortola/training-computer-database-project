package com.formation.project.computerDatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.DataSourceFactory;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.DaoFactory;
import com.formation.project.computerDatabase.dao.IStatsDao;

public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	private IComputerDao computerDao = null;
	private ICompanyDao companyDao = null;
	private IStatsDao statsDao = null;

	public ComputerDatabaseServiceImpl() {
		
		computerDao = DaoFactory.INSTANCE.getComputerDao();
		companyDao 	= DaoFactory.INSTANCE.getCompanyDao();
		statsDao 	= DaoFactory.INSTANCE.getStatsDao();
	}

	@Override
	public void addComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		ThreadLocal<Connection> connThread = DataSourceFactory.INSTANCE.getConnThread();
		try {
			connThread.get().setAutoCommit(false);			
			Integer lastInsertId = computerDao.addComputer(computer);
			statsDao.logOperation(lastInsertId, "add");
			connThread.get().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.addComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.closeConn(connThread);
		}
		
	}

	@Override
	public void updateComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		ThreadLocal<Connection> connThread = DataSourceFactory.INSTANCE.getConnThread();
		try {
			connThread.get().setAutoCommit(false);	
			computerDao.updateComputer(computer);
			statsDao.logOperation(computer.getId(), "update");
			connThread.get().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.updateComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.closeConn(connThread);
		}
	}

	@Override
	public void deleteComputer(Integer computerId) {
		ThreadLocal<Connection> connThread = DataSourceFactory.INSTANCE.getConnThread();
		try {
			connThread.get().setAutoCommit(false);	
			computerDao.deleteComputer(computerId);
			statsDao.logOperation(computerId, "delete");
			connThread.get().commit();
		} catch (SQLException e) {
			System.err.println("Error in ComputerDatabaseService.deleteComputer: " + e.getMessage());
		} finally {
			DataSourceFactory.closeConn(connThread);
		}
	}

	@Override
	public Computer getComputer(Integer computerId) {
		return computerDao.getComputer(computerId);
	}

	@Override
	public HashMap<Integer, Company> getCompanies() {
		return companyDao.getCompanies("");
	}

	@Override
	public ArrayList<Company> getCompaniesList() {
		return new ArrayList<Company>(companyDao.getCompanies("").values());
	}

	@Override
	public List<Computer> getComputers(Integer currentPage,
			Integer resultsPerPage, String sortBy, String name) {
		return computerDao.getComputers(currentPage, resultsPerPage, sortBy,
				name);
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
