package com.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import com.models.User;
import com.service.Service;
import com.userdao.UserDAO;

//public final static Logger log = Logger.getLogger(Appv2.class);
//
//import java.util.logging.*;
//
//private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );

public class Appv2 {

	public static void main(String[] args) throws SQLException, IOException {
		

		UserDAO userDAO = new UserDAO();
		Service service = new Service();
		User u = new User();
		String customerUsername = "";

		boolean isCustomer = false;
		boolean isEmployee = false;

		Scanner sc = null;
		while (!isCustomer && !isEmployee) {

			System.out.println("\nWelcome to the Bank");
			System.out.println();
			System.out.println("Please select an option:");
			System.out.println("1) Register for an account");
			System.out.println("2) Customer login");
			System.out.println("3) Employee login");
			System.out.println();
			System.out.println("Select 'q' to quit.");

			sc = new Scanner(System.in);
			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				service.register();
				break;
			case "2":
				System.out.println("Please enter your customer username and password.");
				Scanner sc1 = new Scanner(System.in);
				System.out.println("Username: ");
				customerUsername = sc1.nextLine();
				isCustomer = service.customerLogin(sc1, customerUsername);
				break;
			case "3":
				isEmployee = service.employeeLogin();
				break;
			case "q":
				System.out.println("Quitting application.");
				System.exit(0);
			default:
				System.out.println("Not a valid option.");
			}
		}

		if (isCustomer) {

			System.out.println("Welcome " + customerUsername);

			String customerChoice = "";

			while (customerChoice != "q") {

				System.out.println("What would you like to do?");
				System.out.println();
				System.out.println("Please select an option:");
				System.out.println("1) View balance");
				System.out.println("2) Withdraw");
				System.out.println("3) Deposit");
				System.out.println("4) Post a money transfer to another account");
				System.out.println("5) Apply for a joint account");
				System.out.println();
				System.out.println("Select 'q' to logout.");

				Scanner scCustomer = new Scanner(System.in);
				;
				customerChoice = scCustomer.nextLine();

				switch (customerChoice) {
				case "1":
					service.getBalance(customerUsername);
					break;
				case "2":
					service.withdraw(customerUsername);
					break;
				case "3":
					service.deposit(customerUsername);
					break;
				case "4":
					service.transferMoney(customerUsername);
					break;
				case "5":
					service.applyForJoint();
					break;
				case "q":
					System.out.println("You are now logged out.");
					System.exit(0);
				default:
					System.out.println("Not a valid option.");
				}
			}

		}

		if (isEmployee) {

			System.out.println("What would you like to do?");
			System.out.println();
			System.out.println("Please select an option:");
			System.out.println("1) Approve or deny applications");
			System.out.println("2) View customer accounts");
			System.out.println("3) View a log of transactions");
			System.out.println();
			System.out.println("Select 'q' to quit.");

			Scanner scEmployee = new Scanner(System.in);
			;
			String employeeChoice = scEmployee.nextLine();

			switch (employeeChoice) {
			case "1":
				service.approveApplications();
				break;
			case "2":
				service.viewCustomerAccounts();
				break;
			case "3":
				service.viewTransactionLog();
				break;
			case "q":
				System.out.println("Quitting application.");
				System.exit(0);
			default:
				System.out.println("Not a valid option.");
			}

		}

//			
//		u = userDAO.selectUserByUsername("testuser2");
//
//		System.out.println(u.getUsername());
//		System.out.println(u.getPassword());
//		System.out.println(u.isCustomer());

	}

}
