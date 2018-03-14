package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.domain.OrderLog;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class OrderSorterHelper {
	
	/**
	 * Method used to take in text and convert it into
	 * an appropriate Order Log, with Order Entries
	 * created along the way.
	 *
	 * @param inputFileLocation
	 * @return OrderLog
	 */
	
	public OrderLog createOrderLog(String inputFileLocation) {

		OrderLog orderLog = new OrderLog();
		try {

			List<OrderEntry> orderEntryList = new ArrayList<>();
			String[] currentLine;

			Scanner inputReading = new Scanner(new FileReader(inputFileLocation));

			while (inputReading.hasNext()) {
				currentLine = inputReading.nextLine().split("[ ]{2,}|[\t]+");
				if (currentLine.length == 2 && !("order").equals(currentLine[0].toLowerCase()) && !("time").equals(currentLine[1].toLowerCase())) {
					if (!("").equals(currentLine[0]) && !("").equals(currentLine[1])) {
						
						OrderEntry currentOrder = new OrderEntry(currentLine[0], new Date(Long.parseLong(currentLine[1])*1000));
						
						orderEntryList.add(currentOrder);
					}
				}
			}

			orderLog.setOrderEntryList(orderEntryList);

		} catch (FileNotFoundException e) {
			System.out.println("The input file you specified does not exist or cannot be opened. Please try again");
		} catch (IllegalStateException e) {
			System.out.println("The file ended abruptly. Please ensure the file is valid and try again");
		} catch (NullPointerException e) {
			System.out.println("The path you provided is not a valid file.");
		} catch (Exception e) {
			System.out.println("Something went wrong with loading data from the file you requested. Please try again.");
			e.printStackTrace();
		}

		return orderLog;

	}
	
	/**
	 * Simple sorting method, uses the custom
	 * Comparator to sort lexicographically
	 *
	 * @param orderLog
	 */
	
	public void sortOrderLog(OrderLog orderLog) {
		if (orderLog != null) {
			if (orderLog.getOrderEntryList() != null && !orderLog.getOrderEntryList().isEmpty()) {
				Collections.sort(orderLog.getOrderEntryList(), new OrderEntryComparator());
			}
		}
	}
	
	/**
	 * Takes in the Order Log and prints it out
	 * to a file specified by the user. This also
	 * prints out a header, as well as converts the
	 * epoch time (initially given) into a human readable form.
	 *
	 * Please Note: This makes the assumption that timezone is
	 * not relevant. Java automatically converts the given epoch
	 * time into a time relative to your location.
	 *
	 * @param orderLog
	 * @param outputFileLocation
	 */

	public void writeOrderLogToFile(OrderLog orderLog, String outputFileLocation) {

		if (orderLog != null && outputFileLocation != null && !"".equals(outputFileLocation)) {
			try {
				
				SimpleDateFormat outputDataFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");

				BufferedWriter outputWriting = new BufferedWriter(new FileWriter(outputFileLocation));

				outputWriting.write(String.format("%-11s %s%n", "Food", "Time"));

				if (!orderLog.getOrderEntryList().isEmpty()) {
					for (OrderEntry current : orderLog.getOrderEntryList()) {
						outputWriting.write(String.format("%-9s %28s%n", current.getFoodItem(), outputDataFormatter.format(current.getOrderTime())));
					}
				}

				outputWriting.close();

			}  catch (FileNotFoundException e) {
				System.out.println("The output file you specified does not exist or cannot be opened. Please try again.");
			} catch (IOException e) {
				System.out.println("Unfortunately the output cannot be saved to the location you specified. Please try again.");
			} catch (NullPointerException e) {
				System.out.println("The path you provided is not a valid file.");
			} catch (Exception e) {
				System.out.println("Something went wrong with outputting your data to a file. Please try again.");
				e.printStackTrace();
			}
		}

	}

}
