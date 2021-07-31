package edu.westga.cs6311.kiosk.model;

import java.util.ArrayList;

/**
 * This class defines an ArrayList of Computer objects
 * 
 * @author Carina Sylvester
 * @version 11/25/2020
 *
 */
public class InventoryManager {
	private ArrayList<Computer> computersInStock;

	/**
	 * Constructor that builds an ArrayList of Computer objects
	 */
	public InventoryManager() {
		this.computersInStock = new ArrayList<Computer>();
	}

	/**
	 * Method that adds standard items to the inventory: 15 HP1234s at $699.99 each,
	 * 120 DL333s at $1,349.28 each
	 */
	public void startStore() {
		Computer newHP = new Computer("HP1234", 699.99, 15);
		Computer newDL = new Computer("DL333", 1349.28, 120);
		this.computersInStock.add(newHP);
		this.computersInStock.add(newDL);
	}

	/**
	 * Method that accepts a String holding a computer's SKU Searches the inventory
	 * and returns a computer with matching SKU
	 * 
	 * @param computerSKU The SKU of the computer
	 * 
	 * @return the computer found that has a matching SKU
	 */
	public Computer findComputer(String computerSKU) {
		if (computerSKU == null) {
			return null;
		}

		for (Computer computerSearch : this.computersInStock) {
			if (computerSearch.getSKU().equalsIgnoreCase(computerSKU)) {
				return computerSearch;
			}
		}
		return null;
	}

	/**
	 * Method that accepts 3 parameters to create a computer that is added to the
	 * inventory
	 *
	 * @param newSKU    The new computer's SKU
	 * 
	 * @param price     The new computer's price
	 * 
	 * @param inventory The number of new computers added to the inventory
	 */
	public void addComputer(String newSKU, double price, int inventory) {
		Computer newComputer = new Computer(newSKU, price, inventory);
		this.computersInStock.add(newComputer);
	}

	/**
	 * Method that searches for and returns the least expensive computer as a String
	 * value
	 * 
	 * 
	 * @return leastExpensive String representation of the cheapest computer object
	 */

	public String getLeastExpensive() {
		double minimumPrice = Integer.MAX_VALUE;
		String leastExpensive = "";
		for (Computer computerObject : this.computersInStock) {
			if (computerObject.getPrice() < minimumPrice) {
				minimumPrice = computerObject.getPrice();
				leastExpensive = computerObject.toString();
			}
		}
		return leastExpensive;
	}

	/**
	 * Method that searches for and returns the most expensive computer as a String
	 * value
	 * 
	 * @return mostExpensive String representation of the most expensive computer
	 *         object
	 */

	public String getMostExpensive() {
		double maximumPrice = Integer.MIN_VALUE;
		String mostExpensive = "";
		for (Computer computerObject : this.computersInStock) {
			if (computerObject.getPrice() > maximumPrice) {
				maximumPrice = computerObject.getPrice();
				mostExpensive = computerObject.toString();
			}
		}
		return mostExpensive;
	}

	/**
	 * Method that calculates and returns the total number of computers in stock
	 * 
	 * @return sum The sum total of computers in the inventory
	 */

	public int getTotalInStock() {
		int sum = 0;

		for (Computer totalInventory : this.computersInStock) {
			sum += totalInventory.getInventory();
		}
		return sum;
	}

	/**
	 * Method that calculates and returns the average computer cost
	 * 
	 * @return getAveragePrice The average price of the computers in the inventory
	 */

	public double getAveragePrice() {
		double price = 0.0;
		if (this.computersInStock == null) {
			return 0;
		}
		for (Computer averagePrice : this.computersInStock) {
			price += averagePrice.getPrice();
		}
		return price / this.computersInStock.size();
	}

	/**
	 * Method that returns a String containing a complete description of the
	 * inventory in the form of a listing with each computer on its own line
	 * 
	 * @return totalInventory String representation of the inventory
	 */

	public String toString() {
		if (this.computersInStock.isEmpty()) {
			return "Inventory is empty";
		}
		String totalInventory = "\nTotal Inventory\n";
		for (Computer listOfComputers : this.computersInStock) {
			totalInventory += listOfComputers.toString() + "\n";
		}
		return totalInventory;
	}

}