package edu.westga.cs6311.kiosk.view;

import edu.westga.cs6311.kiosk.model.InventoryManager;

import java.util.Scanner;

/**
 * This class creates a Kiosk TUI that welcomes the user and gives them the
 * ability to choose between two application options
 * 
 * @author Carina Sylvester
 * @version 11/26/2020
 */
public class KioskTUI {
	private InventoryManager kioskInventoryManger;
	private Scanner input;

	/**
	 * Constructor that accepts an InventoryManager object and initializes the
	 * InventoryManager and Scanner instance variables
	 * 
	 * @param customerCart the customer's shopping cart
	 * 
	 */
	public KioskTUI(InventoryManager customerCart) {
		this.input = new Scanner(System.in);
		this.kioskInventoryManger = new InventoryManager();
		if (customerCart == null) {
			return;
		} else {
			this.kioskInventoryManger = customerCart;
		}
	}

	/**
	 * Method that gives the user the ability to choose to enter the manager's
	 * application, or the shopper's application
	 */
	public void runKiosk() {
		int userChoice = 0;
		this.welcome();
		if (userChoice != 3) {
			this.displayMenu();
			System.out.print("Enter your choice: ");
			userChoice = Integer.parseInt(this.input.nextLine());

			switch (userChoice) {
				case 1:
					this.runManagerTUI();
					break;
				case 2:
					this.runCustomerTUI();
					break;
				case 3:
					this.exitMessage();
					break;

				default:
					this.errorMessage();
					this.runKiosk();
					break;
			}
		}
	}

	private void displayMenu() {
		System.out.println("\t1 - Start Manager Application");
		System.out.println("\t2 - Start Customer Application");
		System.out.println("\t3 - Quit\n");
	}

	private void runManagerTUI() {
		ManagerTUI managerTUIapplication = new ManagerTUI(this.kioskInventoryManger);
		managerTUIapplication.runManager();
	}

	private void welcome() {
		System.out.println("\nWelcome to the Great Purchase Application\n");
	}

	private void runCustomerTUI() {
		CustomerTUI customerTUIapplication = new CustomerTUI(this.kioskInventoryManger);
		customerTUIapplication.runCustomer();
	}

	private void errorMessage() {
		System.out.println("\nThat's not a valid choice. Please try again\n");
	}

	private void exitMessage() {
		System.out.println("\nThank you for using the manager application. \nHave a nice day.");
	}
}