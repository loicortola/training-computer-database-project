package com.formation.computerdatabase.client.jaxrs.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.formation.computerdatabase.core.domain.Computer;
import com.formation.computerdatabase.core.dto.ComputerList;
import com.formation.computerdatabase.webservice.ComputerDatabaseService;

public class ConsoleInterface {

	ComputerDatabaseService service = null;

	public ConsoleInterface(ComputerDatabaseService service) {
		this.service = service;
	}

	public void start() {

		System.out.println("Hello customer, welcome to our Computer Database!");
		System.out.println("What would you like to do?");
		mainMenu();
	}

	public void waitKey() {
		System.out.println("Press enter to continue...");
		try {
			System.in.read();
		} catch (IOException e) {
			System.err.println("IOException in waitKey: " + e.getMessage());
		}
	}

	public void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
	}

	private void mainMenu() {

		clearConsole();

		Scanner scanner = new Scanner(System.in);
		int select = 0;

		while (true) {
			System.out.println("[1] Search computers by computer name");
			System.out.println("[2] Search computers by company name");
			System.out.println("[3] Search computers by both names");
			System.out.println("[4] Exit");

			System.out.println("Selection: ");

			try {
				select = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Hey, it has to be a number dummie!");
				select = -1;
				scanner.nextLine();
			}

			if (select == 4) {
				System.out.println("Goodbye!");
				return;
			}
			switch (select) {
			case 1:
				searchByComputerName();
				break;
			case 2:
				searchByCompanyName();
				break;
			case 3:
				searchByBoth();
				break;
			case -1:
				return;
			default:
				System.out
						.println("Come on! A real value please, I'm not that dumb");
			}
			;
		}
	}

	public void searchByComputerName() {
		clearConsole();
		Scanner scanner = new Scanner(System.in);
		String name;

		System.out.println("Enter computer name:");
		name = scanner.nextLine();
		ComputerList computers = service.getComputers(name, " ");
		if(computers != null && computers.getList() != null)
			for(Computer c : computers.getList()) {
				System.out.println(c.toString());
			}
		else
		{
			System.out.println("No computer found");
		}
		waitKey();
		mainMenu();
	}

	public void searchByCompanyName() {
		clearConsole();
		Scanner scanner = new Scanner(System.in);
		String name;

		System.out.println("Enter company name:");
		name = scanner.nextLine();
		
		ComputerList computers = service.getComputers(" ", name);
		if(computers != null && computers.getList() != null)
			for(Computer c : computers.getList()) {
				System.out.println(c.toString());
			}
		else
		{
			System.out.println("No computer found");
		}
		
		
		waitKey();
		mainMenu();
	}
	
	public void searchByBoth() {
		clearConsole();
		Scanner scanner = new Scanner(System.in);
		String computerName, companyName;

		System.out.println("Enter computer name:");
		computerName = scanner.nextLine();
		System.out.println("Enter company name:");
		companyName = scanner.nextLine();
		List<Computer> computers = service.getComputers(computerName, companyName).getList();
		if(computers != null)
			for(Computer c : computers) {
				System.out.println(c.toString());
			}
		
		waitKey();
		mainMenu();
	}

}
