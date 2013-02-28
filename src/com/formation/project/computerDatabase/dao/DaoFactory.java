package com.formation.project.computerDatabase.dao;

public enum DaoFactory {
	
	INSTANCE;
	
	private ICompanyDao companyDao;
	private IComputerDao computerDao;
	private IStatsDao statsDao;
	
	public ICompanyDao getCompanyDao() {
		if(companyDao == null)
			companyDao = new JdbcDbCompanyDao();
		return companyDao;
	}

	public IComputerDao getComputerDao() {
		if(computerDao == null)
			computerDao = new JdbcDbComputerDao();
		return computerDao;
	}
	
	public IStatsDao getStatsDao() {
		if(statsDao == null)
			statsDao = new JdbcDbStatsDao();
		return statsDao;
	}
}
