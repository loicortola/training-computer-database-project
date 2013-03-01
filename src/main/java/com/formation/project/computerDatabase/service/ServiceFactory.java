package com.formation.project.computerDatabase.service;


public enum ServiceFactory {
	
	INSTANCE;
	
	private IComputerDatabaseService service;

	private ServiceFactory() {
		service =  ComputerDatabaseServiceImpl.INSTANCE;
	}
	
	public IComputerDatabaseService getService() {
		return service;
	}
}