package com.formation.project.computerDatabase.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.ICompanyDao;
import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.dao.DaoFactory;

public class ComputerDatabaseServiceImpl implements IComputerDatabaseService {
	private IComputerDao computerDao = DaoFactory.getComputerDao();
	private ICompanyDao companyDao 	= DaoFactory.getCompanyDao();
	
	public ComputerDatabaseServiceImpl() {
		computerDao = DaoFactory.getComputerDao();
		companyDao 	= DaoFactory.getCompanyDao();
	}
	
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#addComputer(com.formation.project.computerDatabase.base.Computer)
	 */
	@Override
	public void addComputer(Computer computer) {
		computerDao.addComputer(computer);
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#updateComputer(com.formation.project.computerDatabase.base.Computer)
	 */
	@Override
	public void updateComputer(Computer computer) {
		computerDao.updateComputer(computer);
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#deleteComputer(java.lang.Integer)
	 */
	@Override
	public void deleteComputer(Integer computerId) {
		computerDao.deleteComputer(computerId);
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#getComputer(java.lang.Integer)
	 */
	@Override
	public Computer getComputer(Integer computerId) {
		return computerDao.getComputer(computerId);
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#getComputers()
	 */
	@Override
	public List<Computer> getComputers() {
		return computerDao.getComputers("");
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#getCompanies()
	 */
	@Override
	public HashMap<Integer,Company> getCompanies() {
		return companyDao.getCompanies("");
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#getCompaniesList()
	 */
	@Override
	public ArrayList<Company> getCompaniesList() {
		return new ArrayList<Company>(companyDao.getCompanies("").values());
	}
	/* (non-Javadoc)
	 * @see com.formation.project.computerDatabase.service.IComputerDatabaseService#getComputers(java.lang.String)
	 */
	@Override
	public List<Computer> getComputers(String name) {
		return computerDao.getComputers(name);
	}

	@Override
	public Company getCompany(Integer companyId) {
		return companyDao.getCompany(companyId);
	}
	
}
