package com.thegutuproject.pizzashop;

import com.thegutuproject.pizzashop.resource.OrderEntryResource;
import com.thegutuproject.pizzashop.resource.OrderLogResource;
import org.junit.Assert;
import org.junit.Test;
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

	@Test
	public void contextLoads() {
		Assert.assertNotNull(orderEntryResource);
		Assert.assertNotNull(orderLogResource);
	}

}
