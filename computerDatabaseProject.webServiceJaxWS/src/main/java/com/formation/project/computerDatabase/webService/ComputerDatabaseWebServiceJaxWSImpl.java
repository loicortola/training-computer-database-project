package com.formation.project.computerDatabase.webService;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.dao.IComputerDao;

@WebService(endpointInterface = "com.formation.project.computerDatabase.webService.IComputerDatabaseWebServiceJaxWS")
public class ComputerDatabaseWebServiceJaxWSImpl implements IComputerDatabaseWebServiceJaxWS {
	@Autowired
	IComputerDao computerDao;
	
	@Override
	public List<Computer> getComputers(String computerName, String companyName) {
		Assert.notNull(computerName,"Assert Exception: computerName is null in Service.getComputers");
		Assert.notNull(companyName,"Assert Exception: companyName is null in Service.getComputers");
		
		return computerDao.getComputers(computerName, companyName);
	}

}
