package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;

import java.util.Comparator;

public class OrderEntryComparator implements Comparator<OrderEntry> {

	public int compare(OrderEntry orderEntry1, OrderEntry orderEntry2) {
		int itemComparisonResult = orderEntry1.getFoodItem().compareToIgnoreCase(orderEntry2.getFoodItem());
		if (itemComparisonResult != 0) {
			return itemComparisonResult;
		} else {
			return orderEntry1.getOrderTime().compareTo(orderEntry2.getOrderTime());
		}
	}
}
