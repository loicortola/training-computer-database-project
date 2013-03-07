package com.formation.project.computerDatabase.dao;

import org.springframework.data.domain.Page;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.TableSort;

public interface IComputerDao {
	public Computer getComputer(Long computerId);
	public Page<Computer> getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name);
	boolean saveOrUpdate(Computer computer);
	void delete(Long id);
	
}
