package com.formation.project.computerDatabase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.QComputer;
import com.formation.project.computerDatabase.base.Stat;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.IStatDao;
import com.mysema.query.BooleanBuilder;

@Service
@Transactional(readOnly=true)
public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	
	@Autowired
	private IComputerDao computerDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IStatDao statDao;
	
	private final static Logger logger = LoggerFactory.getLogger(ComputerDatabaseServiceImpl.class);
	
	private Sort getSort(TableSort sortBy) {
		return new Sort(sortBy.isAsc() 
				? Sort.Direction.ASC
				: Sort.Direction.DESC, 
				sortBy.getSortString());
	}
	
	@Override
	@Transactional(readOnly=false)
	public void addComputer(Computer computer) {
		logger.debug("Entering Service.addComputer");
		try {
			Assert.notNull(computer);
			Assert.hasText(computer.getName().trim());
			
			computerDao.save(computer);
			
			Stat stat = new Stat(computer.getId(),"add");
			
			statDao.save(stat);
		} catch (IllegalArgumentException e) {
			logger.warn("WARNING: iae in Service.addComputer: {}", e.getMessage());
			throw new IllegalArgumentException();
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void updateComputer(Computer computer) {
		logger.debug("Entering Service.updateComputer");
		try {
			Assert.notNull(computer);
			Assert.notNull(computer.getId());
			Assert.hasText(computer.getName().trim());
			
			//Checking that the computer actually exists in the database
			Assert.isTrue(computerDao.exists(computer.getId()));
					
			computerDao.save(computer);
			
			Stat stat = new Stat(computer.getId(),"update");
			
			statDao.save(stat);
		} catch (IllegalArgumentException e) {
			logger.warn("WARNING: iae in Service.updateComputer: {}", e.getMessage());
			throw new IllegalArgumentException();
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComputer(Long computerId) {
		logger.debug("Entering Service.deleteComputer");
		try {
			Assert.notNull(computerId);
			
			Computer c = getComputer(computerId);
			//Checking that the computer actually exists in the database
			Assert.notNull(c);
			
			computerDao.delete(c);
			
			Stat stat = new Stat(computerId, "delete");
			
			statDao.save(stat);
		} catch (IllegalArgumentException e){
			logger.warn("WARNING: iae in Service.deleteComputer: {}", e.getMessage());
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Computer getComputer(Long computerId) {
		logger.debug("Entering Service.getComputer");		
		Assert.notNull(computerId);
		
		return computerDao.findOne(computerId);
	}

	@Override
	public List<Company> getCompaniesList() {
		logger.debug("Entering Service.getCompaniesList");
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return companyDao.findAll(sort);
	}

	@Override
	public Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String computerName, String companyName) {
		logger.debug("Entering Service.getComputers: currentPage={} resultsPerPage={} sortBy={} searchComputerName={} searchCompanyName={}",currentPage,resultsPerPage,sortBy.getSortString(),computerName,companyName);
		Assert.notNull(currentPage);
		Assert.notNull(resultsPerPage);
		Assert.notNull(sortBy);
		Assert.notNull(computerName);
		Assert.notNull(companyName);
		
		Computers computers = null;
		Pageable pageable = new PageRequest(currentPage-1, resultsPerPage, getSort(sortBy));
		
		
		BooleanBuilder bb = new BooleanBuilder();
	    if (!computerName.trim().isEmpty())
	        bb.and(QComputer.computer.name.containsIgnoreCase(computerName));
	    if (!companyName.trim().isEmpty())
	        bb.and(QComputer.computer.company.name.containsIgnoreCase(companyName));
	    
		Page<Computer> computersPage = computerDao.findAll(bb, pageable);
		
		computers = new Computers(computersPage.getContent(),computersPage.getTotalElements(),sortBy);
		computers.setPageCount(((Long) computers.getComputerCount()/resultsPerPage) + 1);
		computers.setCurrentPage(currentPage);
		computers.setNumberOfElements(computersPage.getNumberOfElements());
		
		return computers;
	}

	@Override
	public Company getCompany(Long companyId) {
		logger.debug("Entering Service.getCompany");
		Assert.notNull(companyId);
		return companyDao.findOne(companyId);
	}

}
