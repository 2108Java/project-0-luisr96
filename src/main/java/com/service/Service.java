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

		
		System.exit(0);
	}

	public boolean employeeLogin() throws SQLException {
		
		System.out.println("Please enter your employee username and password.");
		Scanner sc = new Scanner(System.in);
		System.out.println("Username: ");
		String username = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		String correctPassword = userDAO.employeeLogin(username);
		
		if(password.equals(correctPassword)) {
			System.out.println("Log in success!");
			return true;
		} else {
			System.out.println("Incorrect password. Please try again.");
			return false;
		}
		
		
	}

	public void approveApplications() throws SQLException {
		
		userDAO.approveApplications();
		
		System.out.println("\n1) Approve by username");
		System.out.println("2) Reject by username");
		System.out.println("3) Approve all");
		System.out.println("4) Reject all");
		
		Scanner scApproval = new Scanner(System.in);;
		String approvalChoice = scApproval.nextLine();
		String username = null;
		
		switch (approvalChoice) {
		case "1":
			System.out.println("Please enter the username to approve");
			Scanner scApproveByUsername = new Scanner(System.in);
			System.out.println("Username: ");
			username = scApproveByUsername.nextLine();
			userDAO.approveByUsername(username);
			break;
		case "2":
			System.out.println("Please enter the username to reject");
			Scanner scRejectByUsername = new Scanner(System.in);
			System.out.println("Username: ");
			username = scRejectByUsername.nextLine();
			userDAO.rejectByUsername(username);
			break;
		case "3":
			userDAO.approveAll();
			break;
		case "4":
			userDAO.rejectAll();
			break;
		case "q":
			System.out.println("Quitting application.");
			System.exit(0);
		default:
			System.out.println("Not a valid option.");
		
		
		
	}
	
	
	
}

	public void viewCustomerAccounts() {
		// TODO Auto-generated method stub
		
	}

	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}
}
