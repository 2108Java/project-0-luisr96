package com.menus;

import java.util.Scanner;

import com.models.User;

public class Menus {

	public static void showMainMenu() {

		System.out.println("Welcome to VeryCashMoney Bank!");
		System.out.println();
		System.out.println("Please select an option:");
		System.out.println("1) Register for an account");
		System.out.println("2) Log in to an account");
		System.out.println("3) Employee login");
		System.out.println();
		System.out.println("Select 'q' to quit.");

	}

	public static void registering(User u) {

		System.out.println("To set up an account, please choose a username and password");
		
		System.out.println("Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		System.out.println("You chose the username '" + username + "' and the password '" + password + "'.");
		
		u.setUsername(username);
		u.setPassword(password);
	}

	public static void showCustomerLoginMenu() {

		System.out.println("Please enter your username and password to continue.");
		System.out.println();

	}

	public static void showEmployeeLoginMenu() {
		System.out.println("Please enter your Employee ID number and password.");

	}

}
