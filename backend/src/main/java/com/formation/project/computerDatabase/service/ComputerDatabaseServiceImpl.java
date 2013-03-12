package com.formation.project.computerDatabase.service;

import java.util.List;

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
import com.formation.project.computerDatabase.base.Stat;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.dao.CompanyRepository;
import com.formation.project.computerDatabase.dao.ComputerRepository;
import com.formation.project.computerDatabase.dao.StatRepository;

@Service
@Transactional(readOnly=true)
public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	
	@Autowired
	private ComputerRepository computerRepo;
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private StatRepository statRepo;
	
	private Sort getSort(TableSort sortBy) {
		return new Sort(sortBy.isAsc() 
				? Sort.Direction.ASC
				: Sort.Direction.DESC, 
				sortBy.getSortString());
	}
	
	@Override
	@Transactional(readOnly=false)
	public void addComputer(Computer computer) {
		Assert.notNull(computer);
		Assert.hasText(computer.getName().trim());
		
		computerRepo.save(computer);
		
		Stat stat = new Stat(computer.getId(),"add");
		
		statRepo.save(stat);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateComputer(Computer computer) {
		Assert.notNull(computer);
		Assert.notNull(computer.getId());
		Assert.hasText(computer.getName().trim());
		
		Computer c = getComputer(computer.getId());
		//Checking that the computer actually exists in the database
		Assert.notNull(c);
				
		computerRepo.save(computer);
		
		Stat stat = new Stat(computer.getId(),"update");
		
		statRepo.save(stat);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteComputer(Long computerId) {
		Assert.notNull(computerId);
		
		Computer c = getComputer(computerId);
		//Checking that the computer actually exists in the database
		Assert.notNull(c);
		
		computerRepo.delete(c);
		
		Stat stat = new Stat(computerId, "delete");
		
		statRepo.save(stat);
	}

	@Override
	public Computer getComputer(Long computerId) {
		Assert.notNull(computerId);
		
		return computerRepo.findOne(computerId);
	}

	@Override
	public List<Company> getCompaniesList() {
		
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return companyRepo.findAll(sort);
	}

	@Override
	public Computers getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name) {
		Assert.notNull(currentPage);
		Assert.notNull(resultsPerPage);
		Assert.notNull(sortBy);
		Assert.notNull(name);
		
		Computers computers = null;
		Pageable pageable = new PageRequest(currentPage-1, resultsPerPage, getSort(sortBy));
		Page<Computer> computersPage = computerRepo.findAllByNameContainsIgnoreCase(name, pageable);
		
		
		computers = new Computers(computersPage.getContent(),computersPage.getTotalElements(),sortBy);
		
		return computers;
	}

	@Override
	public Company getCompany(Long companyId) {
		Assert.notNull(companyId);		
		return companyRepo.findOne(companyId);
	}

}
