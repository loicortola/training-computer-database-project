package com.formation.project.computerDatabase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	
	@Autowired
	private IComputerDao computerDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IStatsDao statsDao;

	@Override
	@Transactional
	public void addComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		Integer lastInsertId = computerDao.addComputer(computer);
		statsDao.logOperation(lastInsertId, "add");
		
	}

	@Override
	@Transactional
	public void updateComputer(Computer computer) {
		if (computer.getName() == null || computer.getIntroduced() == null
				|| computer.getCompany() == null)
			throw new IllegalArgumentException();
		computerDao.updateComputer(computer);
		statsDao.logOperation(computer.getId(), "update");
	}

	@Override
	@Transactional
	public void deleteComputer(Integer computerId) {
		computerDao.deleteComputer(computerId);
		statsDao.logOperation(computerId, "delete");
	}

	@Override
	public Computer getComputer(Integer computerId) {
		
		return computerDao.getComputer(computerId);
	}

	@Override
	public Map<Integer, Company> getCompanies() {
		
		return companyDao.getCompanies("");
	}

	@Override
	public List<Company> getCompaniesList() {
		
		return new ArrayList<Company>(companyDao.getCompanies("").values());
	}

	@Override
	public Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name) {
		Computers computers = null;
		
		List<Computer> computersList = computerDao.getComputers(currentPage, resultsPerPage, sortBy.getSortString(),name);
		Integer computerCount 		 = computerDao.getComputerCount(name);
		computers = new Computers(computersList,computerCount,sortBy);
		
		return computers;
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
