package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.userdao.UserDAO;



public class Service {

	UserDAO userDAO = new UserDAO();
	
	public void log(String...message) throws IOException {
		File file = new File("src/resources/transactions.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter("src/resources/transactions.txt", true);
		writer.write((Arrays.deepToString(message)));
		writer.write("\n");
		writer.close();
	}

	public void register() throws SQLException, IOException {
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
			log(username + " registered for account: " + accountTypeSelect);

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

		if (password.equals(correctPassword)) {
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
		System.out.println("Select 'q' to quit.");

		Scanner scApproval = new Scanner(System.in);
		;
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

	public void viewCustomerAccounts() throws SQLException {
		userDAO.viewCustomerAccounts();

	}

	public void viewTransactionLog() throws FileNotFoundException {
		File myFile = new File("src/resources/transactions.txt");
		Scanner myReader = new Scanner(myFile);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			System.out.println(data);
		}
		
		// TODO Auto-generated method stub

	}

	public boolean customerLogin(Scanner sc, String customerUsername) {
//		System.out.println("Please enter your customer username and password.");
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Username: ");
//		String customerUsername = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();

		String correctPassword = userDAO.customerLogin(customerUsername);

		if (password.equals(correctPassword)) {
			System.out.println("Log in success!");
			return true;
		} else {
			System.out.println("Incorrect password. Please try again.");
			return false;
		}
	}

	public void getBalance(String username) throws SQLException, IOException {

		double amountInAccount = userDAO.getBalance(username);
		System.out.println("Your balance is: ");
		System.out.println("$" + amountInAccount);
		log("User " + username + " retrieved their balance.");

	}

	public void withdraw(String username) throws SQLException, IOException {

		System.out.println("How much would you like to withdraw? ");
		Scanner scWithdraw = new Scanner(System.in);
		double amountToWithdraw = scWithdraw.nextDouble();

		// get amount in account
		double amountInAccount = userDAO.getBalance(username);
		// if trying to withdraw more than that amount, throw e
		if (amountToWithdraw > amountInAccount) {
			System.out.println("Not enough funds for transaction.");
			return;
		} else if(amountToWithdraw <= 0) {
			System.out.println("Invalid withdrawal amount!");
			return;
		} else {
		}
			double newAmountInAccount = amountInAccount - amountToWithdraw;
			userDAO.updateBalance(newAmountInAccount, username);

			System.out.println("Your transaction was made successfully.");
			log("User " + username + " made a withdrawal of $" + amountToWithdraw);
		}

	

	public void deposit(String username) throws SQLException, IOException {
		System.out.println("How much would you like to deposit? ");
		Scanner scDeposit = new Scanner(System.in);
		double amountToDeposit = scDeposit.nextDouble();

		// if trying to deposit negative or zero money, throw e
		if (amountToDeposit < 0) {
			System.out.println("You can't deposit negative money!");
			return;
		} else if (amountToDeposit == 0) {
			System.out.println("You can't deposit no money!");
			return;
		} else {
			// get amount in account
			double amountInAccount = userDAO.getBalance(username);
			double newAmountInAccount = amountInAccount + amountToDeposit;
			userDAO.updateBalance(newAmountInAccount, username);

			System.out.println("Your transaction was made successfully.");
			log("User " + username + " made a deposit of $" + amountToDeposit);
		}

	}

	public void transferMoney(String customerUsername) throws SQLException, IOException {
		System.out.println("What is the username of the person you'd like to transfer money to?");
		Scanner scTransferUser = new Scanner(System.in);
		String userToTransferTo = scTransferUser.nextLine();

		System.out.println("How much would you like to transfer? ");
		Scanner scTransferAmount = new Scanner(System.in);
		double amountToTransfer = scTransferAmount.nextDouble();

		double amountInAccount = userDAO.getBalance(customerUsername);

		if (amountToTransfer > amountInAccount) {
			System.out.println("You don't have enough funds!");
		}

		if (amountToTransfer < 0) {
			System.out.println("You can't transfer negative money!");
		} else if (amountToTransfer == 0) {
			System.out.println("You can't transfer no money!");
		}

		double newAmountInCustomerAccount = amountInAccount - amountToTransfer;
		userDAO.updateBalance(newAmountInCustomerAccount, customerUsername);

		double AmountInDestinationAccount = userDAO.getBalance(userToTransferTo);
		double newAmountInDestinationAccount = AmountInDestinationAccount + amountToTransfer;
		userDAO.updateBalance(newAmountInDestinationAccount, userToTransferTo);
		System.out.println("You successfully transferred $" + amountToTransfer + " to " + userToTransferTo);
		log("User " + customerUsername + " made a transfer of $" + amountToTransfer + " to " + userToTransferTo);
	}

	public void applyForJoint() {
		// TODO Auto-generated method stub

	}
}
