package com.formation.project.computerDatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.IStatsDao;

@Service
@Transactional(readOnly=true)
public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	
	@Autowired
	private IComputerDao computerDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IStatsDao statsDao;

	@Override
	@Transactional(readOnly=false)
	public void addComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		Long lastInsertId = computerDao.addComputer(computer);
		statsDao.logOperation(lastInsertId, "add");
		
	}

	@Override
	@Transactional(readOnly=false)
	public void updateComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		computerDao.updateComputer(computer);
		statsDao.logOperation(computer.getId(), "update");
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComputer(Long computerId) {
		computerDao.deleteComputer(computerId);
		statsDao.logOperation(computerId, "delete");
	}

	@Override
	public Computer getComputer(Long computerId) {
		
		return computerDao.getComputer(computerId);
	}

	@Override
	public List<Company> getCompaniesList() {
		
		return companyDao.getCompanies();
	}

	@Override
	public Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name) {
		Computers computers = null;
		
		List<Computer> computersList = computerDao.getComputers(currentPage, resultsPerPage, sortBy,name);
		Integer computerCount 		 = computerDao.getComputerCount(name);
		computers = new Computers(computersList,computerCount,sortBy);
		
		return computers;
	}

	@Override
	public Company getCompany(Long companyId) {
		
		return companyDao.getCompany(companyId);
	}

	@Override
	public Integer getComputerCount(String name) {
		
		return computerDao.getComputerCount(name);
	}

}
