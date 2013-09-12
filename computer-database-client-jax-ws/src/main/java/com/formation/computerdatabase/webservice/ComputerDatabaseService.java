package com.formation.computerdatabase.webservice;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.formation.computerdatabase.core.domain.Computer;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ComputerDatabaseService {
	List<Computer> getComputers(@WebParam(name="computerName") String computerName,
								@WebParam(name="companyName") String companyName);
}
