package lab07;

import lab07.Menu;
import stuff.MyClass;

public class Order {
	private double[] cost = new double[Menu.SIZE];
	private byte[] qty = new byte[Menu.SIZE];

	public void addItem(int i, int add) {
		if (qty[i] + add < 128 && qty[i] + add > -129) //ensure input won't cause value to push over max positive to become max negative and vice versa--what's the word for that event? I hope/wish there was an exception for it (not that I couldn't make my own, I guess)
			qty[i] += add;
		else
			System.out.println("Error: limit exceeded.");
	}

	public byte getQty(int i) {
		return qty[i];
	}

	public double getCost(Menu menu, int i) {
		return menu.getPrice(i) * qty[i];
	}

	public double getSub(Menu menu) {
		double subtotal = 0;
		for (int i=0; i<Menu.SIZE; i++) {
			subtotal += getCost(menu,i);
		}
		return subtotal;
	}

	public double getTax(Menu menu) {
		return getSub(menu) * Main.TAX_RATE;
	}

	public double getTotal(Menu menu) {
		return getSub(menu) + getTax(menu);
	}
}