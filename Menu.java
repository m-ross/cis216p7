package lab07;

import java.io.*;
import java.util.*;
import stuff.MyClass;

public class Menu {
	static final int SIZE = 10;
	private String[] desc;
	private double[] price;

	public Menu(String fileName) throws FileNotFoundException, IOException {
		DataInputStream inFile;

		desc = new String[SIZE];
		price = new double[SIZE];
		inFile = new DataInputStream(new FileInputStream(fileName));

		for (int i=0; i<SIZE; i++) { //get descriptions and prices
			desc[i] = inFile.readUTF();
			price[i] = inFile.readDouble();
		}

		inFile.close();
	}

	public String getDesc(int i) {
		return desc[i];
	}

	public double getPrice(int i) {
		return price[i];
	}
}