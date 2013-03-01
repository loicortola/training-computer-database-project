package com.formation.project.computerDatabase.dao;

public enum DaoFactory {
	
	INSTANCE;
	
	private ICompanyDao companyDao;
	private IComputerDao computerDao;
	private IStatsDao statsDao;
	
	private DaoFactory() {
		companyDao 	= JdbcDbCompanyDao.INSTANCE;
		computerDao = JdbcDbComputerDao.INSTANCE;
		statsDao 	= JdbcDbStatsDao.INSTANCE;
	}
	
	public ICompanyDao getCompanyDao() {
		return companyDao;
	}

	public IComputerDao getComputerDao() {
		return computerDao;
	}
	
	public IStatsDao getStatsDao() {
		return statsDao;
	}
}
