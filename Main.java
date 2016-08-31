/*	Program Name:	Lab 07
	Programmer:		Marcus Ross
	Date Due:		01 Nov 2013
	Description:	This program takes orders for a cashier. It prompts for menu item numbers and quantities until the cashier chooses to finish the order, then displays a receipt.
*/
package lab07;

import lab07.Menu;
import lab07.Order;
import stuff.MyClass;
import java.io.*;

public class Main {
	static final double TAX_RATE = 0.0775;

	public static void main(String[] args) {
		Menu menu;

		System.out.println("MacDowwell's — POS\n");

		while (true) {
			try { //Get menu info from file (prompt for file name)
				menu = new Menu(MyClass.sprompt("Menu file path: "));
				break;
			} catch (FileNotFoundException s) {
				System.out.println("Error: File not found.");
				if (MyClass.cprompt("Specify path? (y/n): ")!='y') { //choice: find menu file or end program
					System.out.println("Exiting program.");
					return;
				}
			} catch (IOException e) {
				System.out.println("Error: File I/O failure.");
				System.exit(1); //I actually have reason for a nonzero exit code here, don't I? Exit codes are arbitrary and their meanings depend on the system, right?
			}
		}

		while (ProcessOrder(menu)) {} //Process each order
	}

	public static boolean ProcessOrder(Menu menu) {
		Order order = new Order(); //start new order to reset fields
		System.out.println("\nNEW ORDER\n 0 to finish order.\n -1 to exit.");
		if (AddItems(order)) //Add each item to order
			ShowReceipt(menu,order); //Show receipt
		else
			return false;
		return true;
	}

	public static boolean AddItems(Order order) {
		int index, temp;

		while (true) {
			index = MyClass.iprompt("Menu number: ") - 1; //subtract one so it corresponds to array index
			if (index > -1 && index < Menu.SIZE) //if valid index, ask quantity
				order.addItem(index,MyClass.bprompt("Quantity: "));
			else 
				if (index == -1) //if not valid index, check if user is finished
					return true;
				else
					if (index == -2) //if user is not finished, check if program should end
						return false;
					else
						System.out.println("Error: Invalid choice."); //error message if not ending program
		}
	}

	public static void ShowReceipt(Menu menu, Order order) {
		ShowHeader(); //Show receipt header
		ShowItems(menu, order);
		ShowTotals(menu, order);
	}

	public static void ShowHeader() {
		System.out.printf("\n%34s\n","__________________________________");
		System.out.printf("%23s\n","MacDowwell's");
		System.out.printf("%24s\n\n","Burger Emporium");
		System.out.printf("%-14s%5s%6s%9s\n","ITEM","QTY","PRICE","COST");
	}

	public static void ShowItems(Menu menu, Order order) {
		for (int i=0; i<Menu.SIZE; i++)
			if (order.getQty(i)!=0)
				System.out.printf("%-14s%5d%6.2f%9.2f\n",menu.getDesc(i),order.getQty(i),menu.getPrice(i),order.getCost(menu,i));
	}

	public static void ShowTotals(Menu menu, Order order) {
		System.out.printf("\n%25s%9.2f\n","SUBTOTAL",order.getSub(menu));
		System.out.printf("%25s%9.2f\n","TAX 7.75%",order.getTax(menu));
		System.out.printf("%25s%9.2f\n%34s\n","TOTAL",order.getTotal(menu),"¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
	}
}