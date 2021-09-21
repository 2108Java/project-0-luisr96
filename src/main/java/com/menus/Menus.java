package com.menus;

import java.util.Scanner;
import com.models.UserAccount;

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

	public static UserAccount registering() {

		Scanner sc = new Scanner(System.in);

		System.out.println("To set up an account, please choose a username and password");

		String username = "";
		String password = "";

		// Choose non-empty username
		while (username.trim().length() < 1) {
			System.out.println("Username: ");
			username = sc.nextLine();
			username = username.trim();
			if (username.trim().length() < 1) {
				System.out.println("Invalid username. Please provide a valid username.");
			}
		}

		// Choose non-empty password
		while (password.trim().length() < 1) {
			System.out.println("Password: ");
			password = sc.nextLine();
			password = password.trim();
			if (password.trim().length() < 1) {
				System.out.println("Invalid password. Please provide a valid password.");
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
					accountTypeSelect = "Checking and Savings";
					break;
				default:
					System.out.println("Not a valid option. Please select another.");
					System.out.println("1) Checking");
					System.out.println("2) Savings");
					System.out.println("3) Checking and Savings");
			}

		}

		// Optional name
		System.out.println("(Optional) Enter your name: ");
		String realName = sc.nextLine();
		realName = realName.trim();
		sc.close();

		// Welcome messages and instantiation
		if (realName.trim().length() == 0) {
			System.out
					.println("Welcome! You chose the username '" + username + "' and the password '" + password + "'.");
			System.out.println("You created a " + accountTypeSelect + " account.");
			UserAccount u = new UserAccount(username, password, accountTypeSelect);
			return u;
		} else {
			System.out.println("Welcome " + realName + "! You chose the username '" + username + "' and the password '"
					+ password + "'.");
			System.out.println("You created a " + accountTypeSelect + " account.");

			UserAccount u = new UserAccount(username, password, accountTypeSelect, realName);
			return u;
		}

	}

	public static void showCustomerLoginMenu() {

		System.out.println("Please enter your username and password to continue.");
		System.out.println();

	}

	public static void showEmployeeLoginMenu() {
		System.out.println("Please enter your Employee ID number and password.");

	}

}
