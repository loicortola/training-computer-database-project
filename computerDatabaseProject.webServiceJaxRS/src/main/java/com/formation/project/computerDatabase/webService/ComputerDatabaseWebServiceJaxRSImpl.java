package com.formation.project.computerDatabase.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.formation.project.computerDatabase.dao.IComputerDao;
import com.formation.project.computerDatabase.model.ComputerList;

public class ComputerDatabaseWebServiceJaxRSImpl implements IComputerDatabaseWebServiceJaxRS {
	@Autowired
	IComputerDao computerDao;
	
	@Override
	public ComputerList getComputers(String computerName, String companyName) {
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		
		return new ComputerList(computerDao.getComputers(computerName, companyName));
	}

}
