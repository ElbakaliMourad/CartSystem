package com.cartsystem.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cartsystem.entities.Items;
import com.cartsystem.services.ItemsServices;

public class CartApp {

	public static void main(String[] args) {

		int choice = 0;
		
		do {
		System.out.println("\n\t\t\t\t**** Cart System App!! ****\n");
		showAllItems();
		
		System.out.println();
		menu();
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		
		switch (choice) {
			case 1: 
				System.out.println("Please enter item id: ");
				scanner = new Scanner(System.in);
				int itmId = scanner.nextInt();
				
				System.out.println("Please enter item name: ");
				scanner = new Scanner(System.in);
				String itmName = scanner.nextLine();
				
				System.out.println("Please enter item description: ");
				scanner = new Scanner(System.in);
				String itmDesc = scanner.nextLine();
				
				System.out.println("Please enter item price: ");
				scanner = new Scanner(System.in);
				double itmPrice = scanner.nextDouble();
				
				System.out.println("Please enter item quantity: ");
				scanner = new Scanner(System.in);
				int itmQtt = scanner.nextInt();
				
				System.out.println("Please enter item category id: ");
				scanner = new Scanner(System.in);
				int itmCtgId = scanner.nextInt();
				
				System.out.println("Please enter item status (1 = Active, 0 = Inacvtive): ");
				scanner = new Scanner(System.in);
				int itmStatus = scanner.nextInt();
				
				Items item = new Items(itmId, itmName, itmDesc, itmPrice, itmQtt, itmCtgId, itmStatus);
				newItem(item);				
				break;
				
			case 2: 
				System.out.println("Please enter id of the Item to update: ");
				scanner = new Scanner(System.in);
				itmId = scanner.nextInt();
				
				item = new Items(itmId, null, null, 0, 0, 0, 0);
				showItemById(item);
				
				System.out.println("\nPlease enter new item name: ");
				scanner = new Scanner(System.in);
				itmName = scanner.nextLine();
				
				System.out.println("Please enter new item description: ");
				scanner = new Scanner(System.in);
				itmDesc = scanner.nextLine();
				
				System.out.println("Please enter new item price: ");
				scanner = new Scanner(System.in);
				itmPrice = scanner.nextDouble();
				
				System.out.println("Please enter new item quantity: ");
				scanner = new Scanner(System.in);
				itmQtt = scanner.nextInt();
				
				System.out.println("Please enter new item category id: ");
				scanner = new Scanner(System.in);
				itmCtgId = scanner.nextInt();
				
				System.out.println("Please enter new item status (1 = Active, 0 = Inacvtive): ");
				scanner = new Scanner(System.in);
				itmStatus = scanner.nextInt();
				
				item = new Items(itmId, itmName, itmDesc, itmPrice, itmQtt, itmCtgId, itmStatus);
				updateItem(item);
				break;
				
			case 3:
				System.out.println("Please enter id of the Item to remove: ");
				scanner = new Scanner(System.in);
				itmId = scanner.nextInt();
				
				item = new Items(itmId, null, null, 0, 0, 0, 0);
				removeItem(item);
				break;
			
			case 4: 
				System.out.println("Please enter the item title: ");
				scanner = new Scanner(System.in);
				itmName = scanner.nextLine();
				
				item = new Items(0, itmName, null, 0, 0, 0, 0);
				showItemsByName(item);
				break;
				
			case 5: 
				System.out.println("Please enter category id to show items: ");
				scanner = new Scanner(System.in);
				itmCtgId = scanner.nextInt();
				
				item = new Items(0, null, null, 0, 0, itmCtgId, 0);
				showItemByCategory(item);
				break;
				
			case 6:
				System.out.println("Please enter id: ");
				scanner = new Scanner(System.in);
				itmId = scanner.nextInt();
				
				item = new Items(itmId, null, null, 0, 0, 0, 0);
				showItemById(item);
				break;
				
			case 7:
				System.out.println("Exiting App...");
				System.exit(0);
				
			default: 
				System.out.println("Invalid choice, Please try again!");
				continue;
		}
	} while (choice !=7);
		
	}
	
	private static void menu() {
		System.out.println("\n1- Add new Item."
			  	         + "\n2- Update Item."
				         + "\n3- Remove Item."
				         + "\n4- Show Item by title."
				         + "\n5- Show Items by category."
				         + "\n6- Show Item by id."
				         + "\n\nPlease enter you choice: ");
	}

	private static void showItemsByName(Items item) {
		ItemsServices itemsService = new ItemsServices();
		List<Items> itemsList = itemsService.getItemByName(item);
		System.out.println("Items By Title: " + item.getTitle());
		for (Items e : itemsList) {
			System.out.println(e.toString());
		}		
	}

	private static void showAllItems() {

		ItemsServices itemsServices = new ItemsServices();
		List<Items> itemsList = new ArrayList<>();
		itemsList = itemsServices.getAllItems();
		for (Items e: itemsList) {
			System.out.println(e.toString());
		}		
	}

	private static void showItemByCategory(Items item) {
		ItemsServices itemsService = new ItemsServices();
		List<Items> itemsList = itemsService.getItemsByCategory(item);
		System.out.println("Items By Category: " + item.getCategoryId());
		System.out.println();
		for (Items e : itemsList) {
			System.out.println(e.toString());
		}			
	}

	private static void showItemById(Items item) {
		ItemsServices itemsServices = new ItemsServices();
		Items foundItem = itemsServices.findItem(item);
		System.out.println();
		if (foundItem == null) {
			System.out.println("Item NOT FOUND, Item ID: " + item.getId());			
		} else {
			System.out.println("Item found!! ");
			System.out.println(foundItem.toString());
		}	
	}

	private static void removeItem(Items item) {

		ItemsServices itemsServices = new ItemsServices();
		boolean result = false;

		Items foundItem = itemsServices.findItem(item);

		if (foundItem != null) {
			System.out.println(foundItem.toString());
			result = itemsServices.deleteItem(foundItem);
			if (result) {
				System.out.println("Item deleted: " + foundItem.getId());
			} else {
				System.out.println("Item Not deleted: " + foundItem.getId());
			}
		} else {
			System.out.println("Item not found! " + item.getId());
		}		
	}

	private static void updateItem(Items item) {
			ItemsServices itemsServices = new ItemsServices();
			boolean result = false;

			Items foundItem = itemsServices.findItem(item);

			if (foundItem != null) {
				foundItem.setTitle(item.getTitle());
				foundItem.setDescription(item.getDescription());
				foundItem.setPrice(item.getPrice());
				foundItem.setQuantity(item.getQuantity());
				foundItem.setCategoryId(item.getCategoryId());
				foundItem.setStatus(item.getStatus());
				result = itemsServices.updateItem(foundItem);
				if (result) {
					System.out.println("Item updated: " + foundItem.getId());
				} else {
					System.out.println("Item Not Updated: " + foundItem.getId());
				}
			} else {
				System.out.println("Item not found! " + item.getId());
			}
		
	}

	private static void newItem(Items item) {
			ItemsServices itemsServices = new ItemsServices();
			boolean result = itemsServices.insertItem(item);
			if (result) {
				System.out.println("Item added, Item ID: " + item.getId());
			} else {
				System.out.println("Item Not Saved, Item ID: " + item.getId());
			}
	}

}
