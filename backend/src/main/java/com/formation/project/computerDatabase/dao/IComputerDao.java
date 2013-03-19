package com.formation.project.computerDatabase.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.formation.project.computerDatabase.base.Computer;

public interface IComputerDao {

	public void save(Computer computer);

	public void delete(Computer computer);
	
	public Computer findOne(Long id);
	
	public Page<Computer> getComputers(Pageable page, String computerName, String companyName);

	public boolean exists(Long id);
	
}
