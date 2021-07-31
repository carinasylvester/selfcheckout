package edu.westga.cs6311.kiosk.view;

import edu.westga.cs6311.kiosk.model.InventoryManager;

import java.util.Scanner;

/**
 * This class creates a Text User Interface (TUI) that allows the user 
 * to interact with the program and display the necessary output
 * 
 * @author Carina Sylvester
 * @version 11/26/2020
 *
 */
public class ManagerTUI {
	private Scanner input;
	private InventoryManager computerInventory;

	/**
	 * Constructor that accepts an InventoryManager object
	 * 
	 * @param currentInventory 		The current inventory level 
	 */
	
	public ManagerTUI(InventoryManager currentInventory) {
		if (currentInventory == null) {
			return;
		} else {
			this.input = new Scanner(System.in);
			this.computerInventory = currentInventory;
		}
	}

	/**
	 * Method that serves as the 'director' of the TUI by calling
	 * on private helper methods to perform its functions
	 * 
	 */
	public void runManager() {
		this.welcome();
		this.menu();
	}

	private void welcome() {
		System.out.println("\nWelcome to the Manager Application\n");
	}

	private void displayMenu() {
		System.out.println("\t1 - Open new store\n" + "\t2 - Add new Computer\n" + "\t3 - View inventory\n"
				+ "\t4 - Quit manager application\n");
		System.out.print("Enter your choice: ");
	}

	private void menu() {
		KioskTUI returnToMenu = new KioskTUI(this.computerInventory);
		int userChoice = 0;
		do {
			this.displayMenu();
			userChoice = Integer.parseInt(this.input.nextLine());
			switch (userChoice) {
				case 1:
					this.openNewStore();
					break;
				case 2:
					this.addNewComputer();
					break;
				case 3:
					this.currentInventory();
					break;
				case 4:
					this.exitMessage();
					returnToMenu.runKiosk();
					break;
				default:
					System.out.println("That is not a valid choice. Please try again\n");
			}
		} while (userChoice != 4);
	}

	private void openNewStore() {
		this.computerInventory.startStore();
		System.out.println("\nNew store opened\n");
	}

	private void addNewComputer() {
		String newSKU;
		int inventory;
		double price;

		System.out.print("\nPlease enter the computer's SKU: ");
		newSKU = this.input.nextLine();
		do {
			System.out.print("Please enter the computer's price: ");
			price = Double.parseDouble(this.input.nextLine());
			price = Math.round(price * 100);
			price = (price / 100);
		} while (price > 10000 || price <= 0);
		do {
			System.out.print("Please enter the number of computers in stock: ");
			inventory = Integer.parseInt(this.input.nextLine());
		} while (inventory > 1000 || inventory <= 0);
		this.computerInventory.addComputer(newSKU, price, inventory);
		System.out.print("\nYou added " + inventory + " " + newSKU + "(s) with a price of $");
		System.out.printf("%.2f", price);
		System.out.print(" to the inventory\n\n");
	}

	private void currentInventory() {
		System.out.println(this.computerInventory.toString());

		if (this.computerInventory.getTotalInStock() != 0) {
			System.out.println("Total quantity of all computers: \t" + this.computerInventory.getTotalInStock());
			System.out.println("Most expensive computer: \t" + this.computerInventory.getMostExpensive());
			System.out.println("Least expensive computer: \t" + this.computerInventory.getLeastExpensive());
			System.out.print("The Average computer cost: \t\t");
			System.out.printf("%.2f", this.computerInventory.getAveragePrice());
			System.out.println("\n");
		}
	}

	private void exitMessage() {
		System.out.println("\nThank you for using the manager application. Have a nice day.\n");
	}

}
