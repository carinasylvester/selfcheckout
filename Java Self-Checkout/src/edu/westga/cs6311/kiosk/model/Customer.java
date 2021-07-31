package edu.westga.cs6311.kiosk.model;

import java.util.ArrayList;

/**
 * This class creates a Customer object from the customer's name, budget, and
 * content of their shopping cart
 * 
 * @author Carina Sylvester
 * @version 11/27/2020
 */
public class Customer {
	private String name;
	private double budget;
	private ArrayList<Computer> shoppingCart;

	/**
	 * Constructor that accepts the customer's name and budget
	 * 
	 * @param customerName Name of the Customer
	 * @param budget       Amount of money available to spend
	 */
	public Customer(String customerName, double budget) {
		this.name = customerName;
		this.budget = budget;
		this.shoppingCart = new ArrayList<Computer>();
	}

	/**
	 * Accessor for the customer's name
	 * 
	 * @return name The customer's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Accessor for the customer's budget
	 * 
	 * @return budget The amount of money the customer has available to spend
	 */

	public double getBudget() {
		this.budget = Math.round(this.budget * 100);
		this.budget = this.budget / 100;
		return this.budget;
	}

	/**
	 * Method that returns a String listing the complete information about each
	 * computer in the cart (to be purchased), if any
	 * 
	 * @return List of computers in cart OR that the cart is empty
	 */
	public String getCart() {
		if (this.shoppingCart.isEmpty()) {
			return "The cart is empty \n\n";
		}
		String computersInCart = "\t";
		for (Computer listOfComputers : this.shoppingCart) {
			computersInCart += listOfComputers.toString() + "\n\t";
		}
		computersInCart += "\n";
		return computersInCart;
	}

	/**
	 * Method that ensures the customer has enough money left to purchase the given
	 * quantity of computers
	 * 
	 * @param potentialPurchase Computer objects that the customer wants to buy (in
	 *                          cart)
	 * @param amount            Sum total of the prices of the selected computer
	 *                          objects
	 * 
	 * @return true If the budget is sufficient to complete the purchase
	 */
	public boolean enoughMoneyToBuy(Computer potentialPurchase, int amount) {
		return this.budget > (potentialPurchase.getPrice() * amount);
	}

	/**
	 * Method that adds computer objects to the cart if the customer has enough
	 * money and the store has a sufficient amount of the specified computers in
	 * stock.
	 * 
	 * It subtracts the total price from the customer's budget, and removes the
	 * quantity of computers from the inventory
	 * 
	 * @param selectedComputer Computer objects to be put in cart
	 * @param quantity         Number of computer objects to be put in cart
	 */
	public void addComputer(Computer selectedComputer, int quantity) {
		Computer addToCart = new Computer(selectedComputer.getSKU(), selectedComputer.getPrice(), quantity);
		if (this.budget >= (selectedComputer.getPrice() * quantity) && quantity <= selectedComputer.getInventory()) {
			this.budget = this.budget - (selectedComputer.getPrice() * quantity);
			this.shoppingCart.add(addToCart);
			selectedComputer.purchase(quantity);
		}
	}

	/**
	 * Method that removes all computers from the shopping cart
	 */
	public void completePurchase() {
		this.shoppingCart.clear();
	}
}
