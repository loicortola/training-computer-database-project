package com.formation.project.computerDatabase.dao;

public abstract class DaoFactory {
	private static ICompanyDao companyDao 	= new JdbcDbCompanyDao();
	private static IComputerDao computerDao 	= new JdbcDbComputerDao();
	
	public static ICompanyDao getCompanyDao() {
		return companyDao;
	}

	public static IComputerDao getComputerDao() {
		return computerDao;
	}
	
	
	
}
