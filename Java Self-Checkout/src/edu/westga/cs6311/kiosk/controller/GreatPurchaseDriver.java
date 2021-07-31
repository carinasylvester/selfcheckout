package edu.westga.cs6311.kiosk.controller;

import edu.westga.cs6311.kiosk.model.InventoryManager;
import edu.westga.cs6311.kiosk.view.KioskTUI;

/**
 * Class that defines the main method as the entry point into the application
 * Creates an instance of the InventoryManager class, passes the object to the
 * KioskTUI constructor and uses KioskTUI to call runKiosk()
 * 
 * @author Carina Sylvester
 * @version 11/25/2020
 *
 */
public class GreatPurchaseDriver {

	/**
	 * Entry point into the application
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		InventoryManager storeInventory = new InventoryManager();
		KioskTUI menuTUI = new KioskTUI(storeInventory);
		menuTUI.runKiosk();
	}
}
