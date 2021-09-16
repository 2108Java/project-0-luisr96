package com.bank;

import com.menus.MainMenu;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		MainMenu.showMainMenu();
		
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		sc.close();
		
		System.out.println("You selected: " + choice);
		
		switch (choice) {
			case "1":
				System.out.println("Customer login.");
				break;
			case "2":
				System.out.println("Employee login.");
				break;
			case "q":
				System.out.println("Quit.");
				break;
		}
		
	}

}
