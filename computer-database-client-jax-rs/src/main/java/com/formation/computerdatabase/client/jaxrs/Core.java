package com.formation.computerdatabase.client.jaxrs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.computerdatabase.client.jaxrs.ui.ConsoleInterface;
import com.formation.computerdatabase.webservice.ComputerDatabaseService;

public class Core {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("application-context.xml");
			ComputerDatabaseService service = ctx.getBean(ComputerDatabaseService.class);
		
			ConsoleInterface ci = new ConsoleInterface(service);
			ci.start();
		}
		finally {
			ctx.close(); 
			ctx = null;
		}
	}
}
