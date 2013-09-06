package com.formation.computerdatabase.back.dao;

import org.springframework.data.repository.CrudRepository;

import com.formation.computerdatabase.back.dao.custom.ComputerDaoCustom;
import com.formation.computerdatabase.core.domain.Computer;

public interface ComputerDao extends CrudRepository<Computer, Long>, ComputerDaoCustom {

	//public void delete(Computer computer);
	
	//public Computer findOne(Long id);
	
//	public Page<Computer> getComputers(Pageable page, String computerName, String companyName);

//	public List<Computer> getComputers(String computerName, String companyName);
	
	//Page<Computer> findAll(Pageable pageable);
	//Page<Computer> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
	
	//public boolean exists(Long id);
	
}
