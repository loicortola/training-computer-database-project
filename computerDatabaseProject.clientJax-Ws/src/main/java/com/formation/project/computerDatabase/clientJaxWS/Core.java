package com.formation.project.computerDatabase.clientJaxWS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.project.computerDatabase.ui.ConsoleInterface;
import com.formation.project.computerDatabase.webService.IComputerDatabaseWebServiceJaxWS;

public class Core {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		IComputerDatabaseWebServiceJaxWS service = ctx.getBean(IComputerDatabaseWebServiceJaxWS.class);
		
		ConsoleInterface ci = new ConsoleInterface(service);
		ci.start();
	}
	
	
}
