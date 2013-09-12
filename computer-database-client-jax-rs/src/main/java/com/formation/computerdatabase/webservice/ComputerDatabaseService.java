package com.formation.computerdatabase.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.formation.computerdatabase.core.dto.ComputerList;
//Dans le fichier cxf.xml (WEB-INF), nous avons defini un bean jaxrs:server mappe sur "/service"
//Ici, le @Path annote sur la classe est un prefixe aux paths des methodes.
//Ainsi, pour appeler getComputers, l'URI http://localhost:8080/computer-database-webservices-jax-rs/service/computer/{computerName}/{companyName} sera appele
@Service("computerDatabaseService")
@Path("/computers")
public interface ComputerDatabaseService {
	@GET
	@Produces("application/xml")
	@Path("/{computerName}/{companyName}")
	public ComputerList getComputers(@PathParam("computerName") String computerName, @PathParam("companyName") String companyName);

}