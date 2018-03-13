package com.thegutuproject.pizzashop.helper;

import com.thegutuproject.pizzashop.domain.OrderEntry;

import java.util.Comparator;

public class OrderEntryComparator implements Comparator<OrderEntry> {
	
	/**
	 * Comparator that redefines the "compare" method. This allows us
	 * to sort using built-in functions, first by item name and then
	 * if the items match, by time.
	 *
	 * @param orderEntry1
	 * @param orderEntry2
	 * @return
	 */

	public int compare(OrderEntry orderEntry1, OrderEntry orderEntry2) {
		int itemComparisonResult = orderEntry1.getFoodItem().compareToIgnoreCase(orderEntry2.getFoodItem());
		if (itemComparisonResult != 0) {
			return itemComparisonResult;
		} else {
			return orderEntry1.getOrderTime().compareTo(orderEntry2.getOrderTime());
		}
	}
}
