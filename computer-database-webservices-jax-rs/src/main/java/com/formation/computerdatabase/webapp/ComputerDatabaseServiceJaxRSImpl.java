package com.formation.computerdatabase.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.formation.computerdatabase.back.dao.ComputerDao;
import com.formation.computerdatabase.core.dto.ComputerList;

public class ComputerDatabaseServiceJaxRSImpl implements ComputerDatabaseService {
	@Autowired
	ComputerDao computerDao;
	
	@Override
	public ComputerList getComputers(String computerName, String companyName) {
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		
		return new ComputerList(computerDao.getComputers(computerName, companyName));
	}

}
