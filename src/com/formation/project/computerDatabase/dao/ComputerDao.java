package com.formation.project.computerDatabase.dao;

import java.util.List;

import com.formation.project.computerDatabase.base.Computer;

public interface ComputerDao {
	public void addComputer(Computer computer);
	public void updateComputer(Computer computer);
	public void deleteComputer(Integer computerId);
	public Computer getComputer(Integer computerId);
	public List<Computer> getComputers(String name);
	
}
