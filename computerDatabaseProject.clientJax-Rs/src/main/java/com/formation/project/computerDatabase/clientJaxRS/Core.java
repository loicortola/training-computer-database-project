package com.formation.project.computerDatabase.clientJaxRS;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.formation.project.computerDatabase.ui.ConsoleInterface;
import com.formation.project.computerDatabase.webService.IComputerDatabaseWebServiceJaxRS;

public class Core {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		IComputerDatabaseWebServiceJaxRS service = ctx.getBean(IComputerDatabaseWebServiceJaxRS.class);
		
		ConsoleInterface ci = new ConsoleInterface(service);
		ci.start();
	}
}
