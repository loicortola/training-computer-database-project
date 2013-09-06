package com.formation.computerdatabase.webservice;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.formation.computerdatabase.back.dao.ComputerDao;
import com.formation.computerdatabase.core.domain.Computer;

@WebService(endpointInterface = "com.formation.computerdatabase.webservice.ComputerDatabaseService")
public class ComputerDatabaseWebServiceJaxWSImpl implements ComputerDatabaseService {
	@Autowired
	ComputerDao computerDao;
	
	@Override
	public List<Computer> getComputers(String computerName, String companyName) {
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		
		return computerDao.getComputers(computerName, companyName);
	}

}
