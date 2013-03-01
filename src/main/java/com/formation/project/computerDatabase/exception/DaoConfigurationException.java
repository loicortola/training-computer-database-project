package com.formation.project.computerDatabase.exception;

public class DaoConfigurationException extends RuntimeException {

	public DaoConfigurationException(String message) {
		System.err.println(message);
	}
	public DaoConfigurationException(String message, Exception e) {
		System.err.println(message + " " + e.getMessage());
	}
}
