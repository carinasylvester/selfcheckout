package edu.westga.cs6311.kiosk.model;

import java.text.DecimalFormat;

/**
 * This class creates a Computer object from the SKU, price, and inventory level
 * (the number currently in stock)
 * 
 * @author Carina Sylvester
 * @version 11/25/2020
 *
 */
public class Computer {
	private String computerSKU;
	private double price;
	private int inventory;

	/**
	 * Constructor that builds the default computer object with no SKU, price or
	 * inventory level
	 */
	public Computer() {
		this.computerSKU = " ";
		this.price = 0.00;
		this.inventory = 0;
	}

	/**
	 * Constructor that accepts the values to be stored in the instance variables
	 * 
	 * @param computerSKU       The computer's Stock Keeping Unit (SKU) number
	 * @param price             The price of the computer
	 * @param computerInventory Quantity of computers currently in stock
	 */

	public Computer(String computerSKU, double price, int computerInventory) {
		if (computerSKU == null) {
			computerSKU = " ";
		}
		if (price < 0 || price > 10000) {
			price = 0.0;
		}
		if (computerInventory < 0 || computerInventory > 1000) {
			computerInventory = 0;
		}

		this.computerSKU = computerSKU;
		this.price = price;
		this.inventory = computerInventory;
	}

	/**
	 * Accessor for the computer's SKU
	 * 
	 * @return computerSKU String representation of the computer's SKU
	 */
	public String getSKU() {
		return this.computerSKU;
	}

	/**
	 * Accessor for the computer's price
	 * 
	 * @return price The cost of the computer
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Accessor for the inventory level
	 * 
	 * @return inventory The quantity of computers in stock
	 */
	public int getInventory() {
		return this.inventory;
	}

	/**
	 * Mutator that accepts the new number of computers in stock
	 * 
	 * @param newInventory the new value of the inventory
	 * 
	 */
	public void setInventory(int newInventory) {
		if (newInventory >= 0) {
			this.inventory = newInventory;
		}
	}

	/**
	 * Method that accepts the number of computers being purchased and deducts the
	 * specified number from the inventory.
	 * 
	 * It tests that: a) The number is not less than 0 b) There are enough computers
	 * on hand to fulfill this purchase
	 * 
	 * @param computersPurchased Deducts the specified number of purchased computers
	 *                           from the inventory
	 */
	public void purchase(int computersPurchased) {
		if (computersPurchased > 0 && this.inventory >= computersPurchased) {
			this.inventory = this.inventory - computersPurchased;
		}
		return;
	}

	/**
	 * Method that returns a formatted String representation of the computer's
	 * price, inventory level, and SKU
	 * 
	 * @return computerString String representation of the computer object
	 */
	public String toString() {
		DecimalFormat format = new DecimalFormat("0.00");
		String formattedPrice = format.format(this.price);
		String computerString = "";
		computerString += String.format("%-6.6s", this.computerSKU) + " $";
		computerString += String.format("%7.7s", formattedPrice);
		computerString += " In Stock: ";
		computerString += String.format("%3.3s", this.inventory);

		return computerString;
	}
}
