package com.formation.project.computerDatabase.service;

public abstract class ServiceFactory {
	private static IComputerDatabaseService service = new ComputerDatabaseServiceImpl();

	public static IComputerDatabaseService getService() {
		return service;
	}
}
