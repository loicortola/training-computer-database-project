package com.formation.computerdatabase.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.formation.computerdatabase.back.dao.ComputerDao;
import com.formation.computerdatabase.core.domain.Computer;
import com.formation.computerdatabase.core.dto.ComputerList;

public class ComputerDatabaseServiceJaxRSImpl implements ComputerDatabaseService {
	@Autowired
	ComputerDao computerDao;
	
	@Override
	public ComputerList getComputers(String computerName, String companyName) {
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		System.out.println("In getComputers. Params: " + computerName + " - " + companyName);
		List<Computer> computers = computerDao.getComputers(computerName, companyName);
		System.out.println("Size:" + computers.size());
		return new ComputerList(computers);
	}

}
