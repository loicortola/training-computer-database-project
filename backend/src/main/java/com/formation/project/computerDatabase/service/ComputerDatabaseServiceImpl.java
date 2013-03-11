package com.formation.project.computerDatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.Stat;
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
		
		computerDao.saveOrUpdate(computer);
		Stat stat = new Stat(computer.getId(),"add");
		statsDao.save(stat);
		
	}

	@Override
	@Transactional(readOnly=false)
	public void updateComputer(Computer computer) {
		computerDao.saveOrUpdate(computer);
		Stat stat = new Stat(computer.getId(),"update");
		statsDao.save(stat);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComputer(Long computerId) {
		computerDao.delete(computerId);
		Stat stat = new Stat(computerId, "delete");
		statsDao.save(stat);
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
		
		Page<Computer> computersPage = computerDao.getComputers(currentPage, resultsPerPage, sortBy,name);
		computers = new Computers(computersPage.getContent(),computersPage.getTotalElements(),sortBy);
		
		return computers;
	}

	@Override
	public Company getCompany(Long companyId) {
		if(companyId == null)
			return null;
		return companyDao.getCompany(companyId);
	}

}
