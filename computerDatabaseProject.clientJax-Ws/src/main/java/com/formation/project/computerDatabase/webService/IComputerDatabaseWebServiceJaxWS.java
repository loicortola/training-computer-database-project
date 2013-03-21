package com.formation.project.computerDatabase.webService;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.formation.project.computerDatabase.base.Computer;

@WebService
public interface IComputerDatabaseWebServiceJaxWS {
	List<Computer> getComputers(@WebParam(name="computerName") String computerName,
								@WebParam(name="companyName") String companyName);
}
