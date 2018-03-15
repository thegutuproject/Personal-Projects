package com.thegutuproject.pizzashop;

import com.thegutuproject.pizzashop.helper.OrderEntryComparatorTests;
import com.thegutuproject.pizzashop.helper.OrderSorterHelperTests;
import com.thegutuproject.pizzashop.resource.OrderEntryResource;
import com.thegutuproject.pizzashop.resource.OrderEntryResourceTests;
import com.thegutuproject.pizzashop.resource.OrderLogResource;
import com.thegutuproject.pizzashop.resource.OrderLogResourceTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaShopApplicationTests {
	
	@Autowired
	OrderEntryResource orderEntryResource;
	
	@Autowired
	OrderLogResource orderLogResource;
	
	public static void main(String[] args) {
		
		JUnitCore jUnitCore = new JUnitCore();
		
		if (args[0].equals("simpleTest")) {
			
			System.out.println("Simple test");
			jUnitCore.run(PizzaShopApplicationTests.class);
			jUnitCore.run(OrderSorterHelperTests.class);
			jUnitCore.run(OrderEntryComparatorTests.class);
			
		} else if (args[0].equals("advancedTest")) {
			
			System.out.println("Advanced Test");
			jUnitCore.run(PizzaShopApplicationTests.class);
			jUnitCore.run(OrderSorterHelperTests.class);
			jUnitCore.run(OrderEntryComparatorTests.class);
			jUnitCore.run(OrderEntryResourceTests.class);
			jUnitCore.run(OrderLogResourceTests.class);
			
		} else {
			
			System.out.println("You haven't specified a valid test. Options: \"simpleTest\" or \"advancedTest\"");
			
		}
	}
	
	@Test
	public void initializationTest() {
		
		System.out.println("TEST RUNNING: initializationTest");
		
		Assert.assertNotNull(orderEntryResource);
		Assert.assertNotNull(orderLogResource);
	}
}
