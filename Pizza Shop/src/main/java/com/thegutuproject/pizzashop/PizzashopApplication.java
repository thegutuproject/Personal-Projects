package com.thegutuproject.pizzashop;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.helper.OrderEntryComparator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

@SpringBootApplication
public class PizzashopApplication {

	public static void main(String[] args) {

		String fileInput = args[0];
		String fileOutput = args[1];

		List<OrderEntry> orderEntryList = new ArrayList<>();

		try {
			Scanner inputReading = new Scanner(new FileReader(fileInput));
			String[] currentLine;

			while(inputReading.hasNext()) {
				currentLine = inputReading.nextLine().split("[\t]+");
				if (currentLine.length == 2 && !("order").equals(currentLine[0].toLowerCase()) && !("time").equals(currentLine[1].toLowerCase())) {
					if (!("").equals(currentLine[0]) && !("").equals(currentLine[1])) {
						OrderEntry currentOrder = new OrderEntry(currentLine[0], new Date(Long.parseLong(currentLine[1])*1000));
						orderEntryList.add(currentOrder);
					}
				}
			}

			if (!orderEntryList.isEmpty()) {
				Collections.sort(orderEntryList, new OrderEntryComparator());
				BufferedWriter outputWriting = new BufferedWriter(new FileWriter(fileOutput));

				outputWriting.write(String.format("%-11s %s%n", "Food", "Time"));

				for (OrderEntry current : orderEntryList) {
					outputWriting.write(String.format("%-9s %30s%n", current.getFoodItem(), current.getOrderTime()));
				}

				outputWriting.close();
			}

			for (OrderEntry orderEntry : orderEntryList) {
				System.out.println("Food: " + orderEntry.getFoodItem() + " | Order Time: " + orderEntry.getOrderTime());
			}

		} catch (Exception e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

		/**
		 * @TODO
		 * proper exception catching
		 * split code up more, make it modular
		 * write unit tests
		 * expand structure for possible web use/db use
		 * stub out resource, service, data provider, etc
		 */


//		SpringApplication.run(PizzashopApplication.class, args);
	}
}
