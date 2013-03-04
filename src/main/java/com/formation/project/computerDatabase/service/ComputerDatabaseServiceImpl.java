package com.formation.project.computerDatabase.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.dao.DataSourceFactory;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.IStatsDao;

@Service
public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	
	@Autowired
	private IComputerDao computerDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IStatsDao statsDao;

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
		
		Computer computer = null;
		
		try {
			computer = computerDao.getComputer(computerId);
		} catch (Exception e) {
			System.out.println("Error in getComputers: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}	
		return computer;
	}

	@Override
	public Map<Integer, Company> getCompanies() {
		Map<Integer, Company> companies = null;
		try {
			companies = companyDao.getCompanies("");
		} catch (Exception e) {
			System.out.println("Error in getCompanies: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}	
		return companies;
	}

	@Override
	public List<Company> getCompaniesList() {
		
		List<Company> companies = null;
		
		try {
			companies = new ArrayList<Company>(companyDao.getCompanies("").values());
		} catch (Exception e) {
			System.out.println("Error in getCompaniesList: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}	
		return companies;
	}

	@Override
	public Computers getComputers(Integer currentPage,
			Integer resultsPerPage, TableSort sortBy, String name) {
		Computers computers = null;
		
		try {
			List<Computer> computersList = computerDao.getComputers(currentPage, resultsPerPage, sortBy.getSortString(),name);
			Integer computerCount 		 = computerDao.getComputerCount(name);
		
			computers = new Computers(computersList,computerCount,sortBy);
		} catch (Exception e) {
			System.out.println("Error in getComputers: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
		
		return computers;
	}

	@Override
	public Company getCompany(Integer companyId) {
		Company company = null;
		try {
			company = companyDao.getCompany(companyId);
		} catch (Exception e) {
			System.out.println("Error in getCompany: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}
		
		return company;
		
	}

	@Override
	public Integer getComputerCount(String name) {
		Integer count = null;
		try {
			count = computerDao.getComputerCount(name);		
		} catch (Exception e) {
			System.out.println("Error in getComputerCount: " + e.getMessage());
		} finally {
			DataSourceFactory.INSTANCE.closeConn();
		}	
		
		return count;
	}

}
