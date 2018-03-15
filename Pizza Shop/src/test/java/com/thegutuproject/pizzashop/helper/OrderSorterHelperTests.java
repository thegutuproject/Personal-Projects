package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.domain.OrderLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

@RunWith(SpringRunner.class)
public class OrderSorterHelperTests {

	private OrderSorterHelper orderSorterHelper;

	String original_data = new File("src/test/resources/data/original_data.txt").getAbsolutePath();
	String unit_test_output = new File("src/test/resources/data/unit_test_output.txt").getAbsolutePath();

	private OrderLog manualOrderLog;
	
	@Before
	public void setup() {
		orderSorterHelper = new OrderSorterHelper();

		// Manually create orderLog with same sample data
		manualOrderLog = new OrderLog();

		OrderEntry orderEntry1 = new OrderEntry("Meat", "1506176687");
		OrderEntry orderEntry2 = new OrderEntry("pizza", "1477578287");
		OrderEntry orderEntry3 = new OrderEntry("p1zza", "1477491887");
		OrderEntry orderEntry4 = new OrderEntry("Bread", "1477405487");
		OrderEntry orderEntry5 = new OrderEntry("Pizza", "1477319087");
		OrderEntry orderEntry6 = new OrderEntry("bread", "1477232687");
		OrderEntry orderEntry7 = new OrderEntry("bread", "1474640687");
		OrderEntry orderEntry8 = new OrderEntry("meatMeaT", "1474295087");
		OrderEntry orderEntry9 = new OrderEntry("VegVeg", "1474295087");

		List<OrderEntry> orderEntries = new ArrayList<>();
		orderEntries.add(orderEntry1);
		orderEntries.add(orderEntry2);
		orderEntries.add(orderEntry3);
		orderEntries.add(orderEntry4);
		orderEntries.add(orderEntry5);
		orderEntries.add(orderEntry6);
		orderEntries.add(orderEntry7);
		orderEntries.add(orderEntry8);
		orderEntries.add(orderEntry9);

		manualOrderLog.setOrderEntryList(orderEntries);
	}

	@Test
	public void testLogBuildingSameFoodItems() {
		
		System.out.println("TEST RUNNING: testLogBuildingSameFoodItems");
		
		OrderLog automatedOrderLog = orderSorterHelper.createOrderLog(original_data);

		Assert.assertEquals("Size of both arrays are equal", automatedOrderLog.getOrderEntryList().size(),manualOrderLog.getOrderEntryList().size());

		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(0).getFoodItem(), automatedOrderLog.getOrderEntryList().get(0).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(1).getFoodItem(), automatedOrderLog.getOrderEntryList().get(1).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(2).getFoodItem(), automatedOrderLog.getOrderEntryList().get(2).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(3).getFoodItem(), automatedOrderLog.getOrderEntryList().get(3).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(4).getFoodItem(), automatedOrderLog.getOrderEntryList().get(4).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(5).getFoodItem(), automatedOrderLog.getOrderEntryList().get(5).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(6).getFoodItem(), automatedOrderLog.getOrderEntryList().get(6).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(7).getFoodItem(), automatedOrderLog.getOrderEntryList().get(7).getFoodItem());
		Assert.assertEquals("Verifying the order food item values are the same for each order entry", manualOrderLog.getOrderEntryList().get(8).getFoodItem(), automatedOrderLog.getOrderEntryList().get(8).getFoodItem());

	}

	@Test
	public void testLogBuildingSameOrderTime() {
		
		System.out.println("TEST RUNNING: testLogBuildingSameOrderTime");
		
		OrderLog automatedOrderLog = orderSorterHelper.createOrderLog(original_data);

		Assert.assertEquals("Size of both arrays are equal", automatedOrderLog.getOrderEntryList().size(),manualOrderLog.getOrderEntryList().size());

		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(0).getOrderTime(), automatedOrderLog.getOrderEntryList().get(0).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(1).getOrderTime(), automatedOrderLog.getOrderEntryList().get(1).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(2).getOrderTime(), automatedOrderLog.getOrderEntryList().get(2).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(3).getOrderTime(), automatedOrderLog.getOrderEntryList().get(3).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(4).getOrderTime(), automatedOrderLog.getOrderEntryList().get(4).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(5).getOrderTime(), automatedOrderLog.getOrderEntryList().get(5).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(6).getOrderTime(), automatedOrderLog.getOrderEntryList().get(6).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(7).getOrderTime(), automatedOrderLog.getOrderEntryList().get(7).getOrderTime());
		Assert.assertEquals("Verifying the order time values are the same for each order entry", manualOrderLog.getOrderEntryList().get(8).getOrderTime(), automatedOrderLog.getOrderEntryList().get(8).getOrderTime());

	}

	@Test
	public void testWritingToAFile() {
		
		System.out.println("TEST RUNNING: testWritingToAFile");
		
		OrderLog originalDataOrderLog = orderSorterHelper.createOrderLog(original_data);
		orderSorterHelper.writeOrderLogToFile(originalDataOrderLog, unit_test_output);

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z");

		try {
			Calendar orderDate = Calendar.getInstance();
			OrderLog reBuiltDataOrderLog = new OrderLog();
			List<OrderEntry> orderEntryList = new ArrayList<>();
			String[] currentLine;

			Scanner inputReading = new Scanner(new FileReader(unit_test_output));

			while (inputReading.hasNext()) {
				currentLine = inputReading.nextLine().split("[ ]{2,}|[\t]+");
				if (currentLine.length == 2 && !("order").equals(currentLine[0].toLowerCase()) && !("time").equals(currentLine[1].toLowerCase())) {
					if (!("").equals(currentLine[0]) && !("").equals(currentLine[1])) {
						
						orderDate.setTime(dateFormatter.parse(currentLine[1]));
						
						OrderEntry currentOrder = new OrderEntry(currentLine[0], orderDate.getTime());
						
						orderEntryList.add(currentOrder);
					}
				}
			}

			reBuiltDataOrderLog.setOrderEntryList(orderEntryList);

			for (int i = 0; i < originalDataOrderLog.getOrderEntryList().size(); i++) {
				Assert.assertEquals("Food Item from output and input should be the same", originalDataOrderLog.getOrderEntryList().get(i).getFoodItem() , reBuiltDataOrderLog.getOrderEntryList().get(i).getFoodItem());
				Assert.assertEquals("Order Time from output and input should be the same", dateFormatter.format(originalDataOrderLog.getOrderEntryList().get(i).getOrderTime()), dateFormatter.format(reBuiltDataOrderLog.getOrderEntryList().get(i).getOrderTime()));
			}

		} catch (FileNotFoundException e) {
			System.out.println("The input file you specified does not exist or cannot be opened. Please try again");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
