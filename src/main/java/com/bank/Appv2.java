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
		
		Scanner sc = null;
		while (u.isLoggedIn() == false) {
			
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
					service.employeeLogin();
					break;
				case "q":
					System.out.println("Quitting application.");
					System.exit(0);
				default:
					System.out.println("Not a valid option.");
			}
		}
		
		
		if(u.isCustomer()) {
			
		}
		
		if(u.isEmployee()) {
			
		}

//			
//		u = userDAO.selectUserByUsername("testuser2");
//
//		System.out.println(u.getUsername());
//		System.out.println(u.getPassword());
//		System.out.println(u.isCustomer());

	}

}
