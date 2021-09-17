package com.bank;
import com.menus.Menus;
import com.models.User;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		User u = new User();
		
		Menus.showMainMenu();
		
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		
		switch (choice) {
			case "1":
				Menus.registering(u);
				break;
			case "2":
				Menus.showCustomerLoginMenu();
				break;
			case "3":
				Menus.showEmployeeLoginMenu();
				break;
			case "q":
				System.out.println("Quitting application.");
				break;
		}

		sc.close();
	}
	
	

}
