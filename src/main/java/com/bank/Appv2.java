package com.bank;

import java.sql.SQLException;
import java.util.Scanner;

import com.models.User;
import com.service.Service;
import com.userdao.UserDAO;

public class Appv2 {

	public static void main(String[] args) throws SQLException {

		UserDAO userDAO = new UserDAO();
		Service service = new Service();
		User u = new User();
		
		boolean isCustomer = false;
		boolean isEmployee = false;
		
		Scanner sc = null;
		while (!isCustomer && !isEmployee) {
			
			System.out.println("\nWelcome to VeryCashMoney Bank!");
			System.out.println();
			System.out.println("Please select an option:");
			System.out.println("1) Register for an account");
			System.out.println("2) Log in to an account");
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
					System.out.println("2222222222222222222");
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
	
		
		if(isCustomer) {
			
		}
		
		if(isEmployee) {
			
			System.out.println("What would you like to do?");
			System.out.println();
			System.out.println("Please select an option:");
			System.out.println("1) Approve applications");
			System.out.println("2) View customer accounts");
			System.out.println("3) View a log of transactions");
			System.out.println();
			System.out.println("Select 'q' to quit.");
			
			Scanner scEmployee = new Scanner(System.in);;
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
