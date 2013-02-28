package com.formation.project.computerDatabase.service;


public enum ServiceFactory {
	
	INSTANCE;
	
	private IComputerDatabaseService service;

	private ServiceFactory() {
		service =  new ComputerDatabaseServiceImpl();
	}
	
	public IComputerDatabaseService getService() {
		return service;
	}
}