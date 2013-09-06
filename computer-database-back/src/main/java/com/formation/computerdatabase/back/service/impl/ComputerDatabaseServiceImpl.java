package com.formation.computerdatabase.back.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.formation.computerdatabase.back.dao.CompanyDao;
import com.formation.computerdatabase.back.dao.ComputerDao;
import com.formation.computerdatabase.back.dao.StatDao;
import com.formation.computerdatabase.back.service.ComputerDatabaseService;
import com.formation.computerdatabase.core.domain.Company;
import com.formation.computerdatabase.core.domain.Computer;
import com.formation.computerdatabase.core.domain.Stat;

@Service
@Transactional(readOnly=true)
public class ComputerDatabaseServiceImpl implements ComputerDatabaseService {
	
	@Autowired
	private ComputerDao computerDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private StatDao statDao;
	
	private final static Logger logger = LoggerFactory.getLogger(ComputerDatabaseServiceImpl.class);
	
	@Override
	@Transactional(readOnly=false)
	public void addComputer(Computer computer) {
		logger.debug("Entering Service.addComputer");
		Assert.notNull(computer,"Assert Exception: Computer is null in Service.addComputer");
		Assert.hasText(computer.getName(),"Assert Exception: Computer.name is null or empty in Service.addComputer");
		
		computerDao.save(computer);
		
		Stat stat = new Stat(computer.getId(),"add");
		
		statDao.save(stat);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateComputer(Computer computer) {
		logger.debug("Entering Service.updateComputer");
		Assert.notNull(computer,"Assert Exception: Computer is null in Service.updateComputer");
		Assert.notNull(computer.getId(),"Assert Exception: Computer.id is null in Service.updateComputer");
		Assert.hasText(computer.getName(),"Assert Exception: Computer.name is null or empty in Service.updateComputer");
		
		//Checking that the computer actually exists in the database
		Assert.isTrue(computerDao.exists(computer.getId()));
				
		computerDao.save(computer);
		
		Stat stat = new Stat(computer.getId(),"update");
		
		statDao.save(stat);		
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComputer(Long computerId) {
		logger.debug("Entering Service.deleteComputer");
		Assert.notNull(computerId,"Assert Exception: computerId is null in Service.deleteComputer");
		
		Computer c = getComputer(computerId);
		//Checking that the computer actually exists in the database
		Assert.notNull(c);
		
		computerDao.delete(c);
		
		Stat stat = new Stat(computerId, "delete");
		
		statDao.save(stat);
		
	}

	@Override
	public Computer getComputer(Long computerId) {
		logger.debug("Entering Service.getComputer");		
		Assert.notNull(computerId,"Assert Exception: computerId is null in Service.getComputer");
		
		return computerDao.findOne(computerId);
	}

	@Override
	public List<Company> getCompaniesList() {
		logger.debug("Entering Service.getCompaniesList");
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return companyDao.findAll(sort);
	}

	@Override
	public Page<Computer> getComputers(Pageable page, String computerName, String companyName) {
		logger.debug("Entering Service.getComputers: currentPage={} resultsPerPage={} sortBy={} searchComputerName={} searchCompanyName={}",page.getPageNumber(),page.getPageSize(),page.getSort(),computerName,companyName);
		Assert.notNull(page.getPageNumber(),"Assert Exception: currentPage is null in Service.getComputers");
		Assert.notNull(page.getPageSize(),"Assert Exception: resultsPerPage is null in Service.getComputers");
		Assert.notNull(page.getSort(),"Assert Exception: sortBy is null in Service.getComputers");
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		
		return computerDao.getComputers(page, computerName, companyName);
	}

	@Override
	public Company getCompany(Long companyId) {
		logger.debug("Entering Service.getCompany");
		Assert.notNull(companyId,"Assert Exception: companyId is null in Service.getCompany");
		return companyDao.findOne(companyId);
	}

}
