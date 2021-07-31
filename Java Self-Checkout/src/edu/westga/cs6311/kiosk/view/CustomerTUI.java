package edu.westga.cs6311.kiosk.view;

import edu.westga.cs6311.kiosk.model.Computer;
import edu.westga.cs6311.kiosk.model.Customer;
import edu.westga.cs6311.kiosk.model.InventoryManager;

import java.util.Scanner;

/**
 * This class creates a Customer TUI that gives the user the ability to interact
 * with the Shopper Application
 * 
 * @author Carina Sylvester
 * @version 11/29/2020
 */
public class CustomerTUI {
	private InventoryManager computerInventory;
	private Customer kioskCustomer;
	private Scanner input;

	/**
	 * Constructor that accepts an InventoryManager object and initializes the
	 * InventoryManager and scanner instance variables
	 * 
	 * @param currentInventory The store's current computer inventory
	 * 
	 */
	public CustomerTUI(InventoryManager currentInventory) {
		if (currentInventory == null) {
			return;
		} else {
			this.computerInventory = currentInventory;
		}
		this.kioskCustomer = new Customer("", 0.0);
		this.input = new Scanner(System.in);
	}

	/**
	 * Method that runs CustomerTUI by calling the appropriate helpers
	 */
	public void runCustomer() {
		this.customerWelcome();
		this.getCustomerInformation();
		this.customerMenu();
	}

	private void customerWelcome() {
		System.out.println("\nWelcome to the Shopper Application\n");
	}

	private void getCustomerInformation() {
		String name;
		double budgetAmount;
		System.out.print("Please enter your name: ");
		name = this.input.nextLine();
		do {
			System.out.print("Please enter the amount you have to spend: ");
			budgetAmount = Double.parseDouble(this.input.nextLine());
			budgetAmount = Math.round(budgetAmount * 100);
			budgetAmount = budgetAmount / 100;
		} while (budgetAmount < 0.0);
		this.kioskCustomer = new Customer(name, budgetAmount);

		System.out.println("\n Welcome " + name + ". Enjoy spending your $" + budgetAmount);
		this.customerBudget();
		System.out.println("");
	}

	private void customerMenu() {
		KioskTUI returnToMenu = new KioskTUI(this.computerInventory);
		int userChoice;
		do {
			this.displayCustomerMenu();
			userChoice = Integer.parseInt(this.input.nextLine());
			switch (userChoice) {
				case 1:
					this.currentInventory();
					break;
				case 2:
					this.addComputerToCart();
					break;
				case 3:
					this.contentOfCart();
					break;
				case 4:
					this.customerBudget();
					break;
				case 5:
					this.customerCheckout();
					break;
				case 6:
					this.exitMessage();
					returnToMenu.runKiosk();
					break;
				default:
					System.out.println("That is not a valid option. Please try again\n");
			}
		} while (userChoice != 6);
	}

	private void displayCustomerMenu() {
		System.out.println(
				"\nCustomer Menu\n\t1 - View Inventory\n" + "\t2 - Add computer to the cart\n" + "\t3 - View Cart\n"
						+ "\t4 - View money remaining\n" + "\t5 - Checkout \n" + "\t6 - Quit shopper application\n");
		System.out.print("Enter your choice: ");
	}

	private void currentInventory() {
		System.out.println(this.computerInventory.toString());
	}

	private void addComputerToCart() {
		Computer chosenComputer;
		String chosenComputerSKU;
		int purchaseQuantity;
		System.out.print("Enter the computer's SKU: ");
		chosenComputerSKU = this.input.nextLine();
		this.computerInventory.findComputer(chosenComputerSKU);
		if (this.computerInventory.findComputer(chosenComputerSKU) == null) {
			System.out.println("That computer SKU does not exist ");
			return;
		}
		chosenComputer = this.computerInventory.findComputer(chosenComputerSKU);
		System.out.print("How many " + chosenComputerSKU + " computer(s) would you like to purchase: ");
		purchaseQuantity = Integer.parseInt(this.input.nextLine());
		System.out.println("");
		if (chosenComputer.getInventory() < purchaseQuantity) {
			System.out.println("The store does not have that many " + chosenComputerSKU + " computers for sale");
		} else if (this.kioskCustomer.enoughMoneyToBuy(chosenComputer, purchaseQuantity)) {
			this.kioskCustomer.addComputer(chosenComputer, purchaseQuantity);
			System.out.println("You added " + purchaseQuantity + " " + chosenComputerSKU + " to your cart");
			System.out.println("You have " + this.kioskCustomer.getBudget() + " left to spend");
		} else {
			System.out.println("You do not have enough money to purchase " + purchaseQuantity
					+ " " + chosenComputerSKU + " (s).");
		}

	}

	private void contentOfCart() {
		System.out.println("The cart contains ");
		System.out.print(this.kioskCustomer.getCart());
	}

	private void customerBudget() {
		System.out.print(" You have $");
		System.out.printf("%.2f", this.kioskCustomer.getBudget());
		System.out.println(" remaining to spend");
	}

	private void customerCheckout() {
		this.kioskCustomer.completePurchase();
		System.out.println("Your purchase has been completed. ");
	}

	private void exitMessage() {
		System.out.println("Thank you for using the Shopper application\n");
	}

}