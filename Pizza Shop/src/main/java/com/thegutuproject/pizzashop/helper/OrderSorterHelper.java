package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.domain.OrderLog;

import java.io.*;
import java.util.*;

public class OrderSorterHelper {

	public OrderLog createOrderLog(String inputFileLocation) {

		OrderLog orderLog = new OrderLog();

		try {

			List<OrderEntry> orderEntryList = new ArrayList<>();
			String[] currentLine;

			Scanner inputReading = new Scanner(new FileReader(inputFileLocation));

			while (inputReading.hasNext()) {
				currentLine = inputReading.nextLine().split("[\t]+");
				if (currentLine.length == 2 && !("order").equals(currentLine[0].toLowerCase()) && !("time").equals(currentLine[1].toLowerCase())) {
					if (!("").equals(currentLine[0]) && !("").equals(currentLine[1])) {
						OrderEntry currentOrder = new OrderEntry(currentLine[0], new Date(Long.parseLong(currentLine[1]) * 1000));
						orderEntryList.add(currentOrder);
					}
				}
			}

			orderLog.setOrderEntryList(orderEntryList);

		} catch (FileNotFoundException e) {
			System.out.println("The file you specified does not exist or cannot be opened. Please try again");
		} catch (IllegalStateException e) {
			System.out.println("The file ended abruptly. Please ensure the file is valid and try again");
		}  catch (Exception e) {
			System.out.println("Something went wrong with loading data from the file you requested. Please try again.");
			e.printStackTrace();
		}

		return orderLog;

	}

	public void sortOrderLog(OrderLog orderLog) {
		if (orderLog != null) {
			if (orderLog.getOrderEntryList() != null && !orderLog.getOrderEntryList().isEmpty()) {
				Collections.sort(orderLog.getOrderEntryList(), new OrderEntryComparator());
			}
		}
	}

	public void writeOrderLogToFile(OrderLog orderLog, String outputFileLocation) {

		if (orderLog != null && outputFileLocation != null && !"".equals(outputFileLocation)) {
			try {

				BufferedWriter outputWriting = new BufferedWriter(new FileWriter(outputFileLocation));

				outputWriting.write(String.format("%-11s %s%n", "Food", "Time"));

				if (!orderLog.getOrderEntryList().isEmpty()) {
					for (OrderEntry current : orderLog.getOrderEntryList()) {
						outputWriting.write(String.format("%-9s %30s%n", current.getFoodItem(), current.getOrderTime()));
					}
				}

				outputWriting.close();

			}  catch (FileNotFoundException e) {
				System.out.println("The file you are trying to write to cannot be accessed (most likely access denied) Please try again.");
			} catch (IOException e) {
				System.out.println("Unfortunately the output cannot be saved to the location you specified. Please try again.");
			} catch (Exception e) {
				System.out.println("Something went wrong with outputting your data to a file. Please try again.");
				e.printStackTrace();
			}
		}

	}

}
