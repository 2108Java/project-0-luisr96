package com.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.userdao.UserDAO;

public class Service {
	
	UserDAO userDAO = new UserDAO();
	
	public void register() throws SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("To set up an account, please choose a username and password");

		String username = "";
		String password = "";

		// Choose non-empty username
		while (username.trim().length() < 1) {
			System.out.println("Username: ");
			username = sc.nextLine();
			if (username.trim().length() < 1) {
				System.out.println("Blank username. Please provide a valid username.");
			}
		}

		// Choose non-empty password
		while (password.trim().length() < 1) {
			System.out.println("Password: ");
			password = sc.nextLine();
			if (password.trim().length() < 1) {
				System.out.println("Blank password. Please provide a valid password.");
			}
		}

		// Choose account type
		System.out.println("What type of account do you want to set up?");
		System.out.println("1) Checking");
		System.out.println("2) Savings");
		System.out.println("3) Checking and Savings");
		
		String accountTypeSelect = null;

		while (accountTypeSelect == null) {
			String choice = sc.nextLine();
			switch (choice) {
				case "1":
					accountTypeSelect = "Checking";
					break;
				case "2":
					accountTypeSelect = "Savings";
					break;
				case "3":
					accountTypeSelect = "Checking & Savings";
					break;
				default:
					System.out.println("Not a valid option. Please select another.");
					System.out.println("1) Checking");
					System.out.println("2) Savings");
					System.out.println("3) Checking and Savings");
			}
			
		userDAO.registration(username, password, accountTypeSelect);

		}
		
		// Send to Applications for Review table
		
		System.exit(0);
	}

	public void employeeLogin() throws SQLException {
		
		System.out.println("Please enter your employee username and password.");
		Scanner sc = new Scanner(System.in);
		System.out.println("Username: ");
		String username = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		String correctPassword = userDAO.employeeLogin(username);
		
		if(password.equals(correctPassword)) {
			System.out.println("Log in success!");
			
		} else {
			System.out.println("Incorrect password. Please try again.");
		}
		
	}
	
	
	
}
