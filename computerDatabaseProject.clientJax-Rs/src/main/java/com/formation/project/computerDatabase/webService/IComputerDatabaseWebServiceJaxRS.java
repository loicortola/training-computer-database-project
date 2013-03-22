package com.formation.project.computerDatabase.webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.formation.project.computerDatabase.model.ComputerList;

@Service("computerDatabaseService")
@Path("/t/")
public interface IComputerDatabaseWebServiceJaxRS {
	@GET
	@Produces("application/xml")
	@Path("{computerName}/{companyName}")
	public ComputerList getComputers(@PathParam("computerName") String computerName, @PathParam("companyName") String companyName);

}