package com.thegutuproject.pizzashop;

import com.thegutuproject.pizzashop.helper.OrderEntryComparatorTests;
import com.thegutuproject.pizzashop.helper.OrderSorterHelperTests;
import com.thegutuproject.pizzashop.resource.OrderEntryResourceTests;
import com.thegutuproject.pizzashop.resource.OrderLogResourceTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PizzaShopApplicationTests {
	
	public static void main(String[] args) {
		
		List<Class> classList = new ArrayList<>();
		
		if (null != args && args.length == 1) {
			if (args[0].equals("simple")) {
			
				classList.add(OrderEntryComparatorTests.class);
				classList.add(OrderSorterHelperTests.class);
				
			} else if (args[0].equals("advanced")) {
				
				classList.add(OrderEntryComparatorTests.class);
				classList.add(OrderSorterHelperTests.class);
				classList.add(OrderEntryResourceTests.class);
				classList.add(OrderLogResourceTests.class);
				
			}
			
			for (Class currentClass : classList) {
				
				runTest(currentClass);
				
			}
			
		} else {
			
			PizzaShopApplication.main(args);
			
		}
	}
	
	public static void runTest(Class testClass) {
		
		JUnitCore core = new JUnitCore();
		Result result = core.run(testClass);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
		
	}

}
