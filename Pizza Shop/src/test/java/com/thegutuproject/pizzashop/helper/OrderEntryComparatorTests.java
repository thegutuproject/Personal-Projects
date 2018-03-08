package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OrderEntryComparatorTests {
	
	private Calendar date = Calendar.getInstance();
	
	@Test
	public void testComparatorDifferentNameSameTime() {
		
		List<OrderEntry> orderEntries = new ArrayList<>();
		
		OrderEntry orderEntry1 = new OrderEntry("AAAC", "1477405487"); // youngest
		OrderEntry orderEntry2 = new OrderEntry("AAAB", "1477405487");
		OrderEntry orderEntry3 = new OrderEntry("AAAA", "1477405487");
		OrderEntry orderEntry4 = new OrderEntry("AAAD", "1477405487"); // oldest
		
		orderEntries.add(orderEntry1);
		orderEntries.add(orderEntry2);
		orderEntries.add(orderEntry3);
		orderEntries.add(orderEntry4);
		
		// Sort from oldest to youngest based on time alone
		Collections.sort(orderEntries, new OrderEntryComparator());
		
		Assert.assertTrue(orderEntries.get(0).getFoodItem().equals("AAAA"));
		Assert.assertTrue(orderEntries.get(1).getFoodItem().equals("AAAB"));
		Assert.assertTrue(orderEntries.get(2).getFoodItem().equals("AAAC"));
		Assert.assertTrue(orderEntries.get(3).getFoodItem().equals("AAAD"));
	}
	
	@Test
	public void testComparatorSameNameDifferentTime() {
		
		List<OrderEntry> orderEntries = new ArrayList<>();
		
		OrderEntry orderEntry1 = new OrderEntry("AAAA", "1477405487"); // youngest
		OrderEntry orderEntry2 = new OrderEntry("AAAA", "1467405487");
		OrderEntry orderEntry3 = new OrderEntry("AAAA", "1457405487");
		OrderEntry orderEntry4 = new OrderEntry("AAAA", "1447405487"); // oldest
		
		orderEntries.add(orderEntry1);
		orderEntries.add(orderEntry2);
		orderEntries.add(orderEntry3);
		orderEntries.add(orderEntry4);
		
		// Sort from oldest to youngest based on time alone
		Collections.sort(orderEntries, new OrderEntryComparator());
		
		Assert.assertTrue(orderEntries.get(0).getOrderTime().equals(convertStringToDate("1447405487")));
		Assert.assertTrue(orderEntries.get(1).getOrderTime().equals(convertStringToDate("1457405487")));
		Assert.assertTrue(orderEntries.get(2).getOrderTime().equals(convertStringToDate("1467405487")));
		Assert.assertTrue(orderEntries.get(3).getOrderTime().equals(convertStringToDate("1477405487")));
		
	}
	
	public Date convertStringToDate(String stringLong) {
		date.setTimeInMillis(Long.parseLong(stringLong) * 1000);
		return date.getTime();
	}
	
	
}
