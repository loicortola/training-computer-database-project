package com.formation.project.computerDatabase.exception;

public class DAOConfigurationException extends RuntimeException {

	public DAOConfigurationException(String message) {
		System.err.println(message);
	}
	public DAOConfigurationException(String message, Exception e) {
		System.err.println(message + " " + e.getMessage());
	}
}
