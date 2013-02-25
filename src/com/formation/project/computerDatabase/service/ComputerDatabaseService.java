package com.formation.project.computerDatabase.service;

import java.util.List;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.ComputerDao;
import com.formation.project.computerDatabase.dao.JdbcDbComputerDao;

public class ComputerDatabaseService {
	private ComputerDao computerDao = null;
	
	public ComputerDatabaseService() {
		computerDao = new JdbcDbComputerDao(); 
	}
	
	public void addComputer(Computer computer) {
		computerDao.addComputer(computer);
	}
	public void updateComputer(Computer computer) {
		computerDao.updateComputer(computer);
	}
	public void deleteComputer(Integer computerId) {
		computerDao.deleteComputer(computerId);
	}
	public Computer getComputer(Integer computerId) {
		return computerDao.getComputer(computerId);
	}
	public List<Computer> getComputers() {
		return computerDao.getComputers();
	}
	
}
