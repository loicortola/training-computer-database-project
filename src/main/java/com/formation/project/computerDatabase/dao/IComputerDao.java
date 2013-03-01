package com.formation.project.computerDatabase.dao;

import java.util.List;

import com.formation.project.computerDatabase.base.Computer;

public interface IComputerDao {
	public Integer addComputer(Computer computer);
	public Integer getLastInsertId();
	public void updateComputer(Computer computer);
	public void deleteComputer(Integer computerId);
	public Computer getComputer(Integer computerId);
	public Integer getComputerCount(String name);
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, String sortBy, String name);
	
}
