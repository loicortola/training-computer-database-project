package com.formation.project.computerDatabase.dao;

import java.util.List;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.TableSort;

public interface IComputerDao {
	public Long addComputer(Computer computer);
	public void updateComputer(Computer computer);
	public void deleteComputer(Long computerId);
	public Computer getComputer(Long computerId);
	public Integer getComputerCount(String name);
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name);
	
}
