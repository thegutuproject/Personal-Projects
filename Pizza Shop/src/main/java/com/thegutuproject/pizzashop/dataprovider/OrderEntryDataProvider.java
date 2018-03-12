package com.thegutuproject.pizzashop.dataprovider;

import com.thegutuproject.pizzashop.domain.OrderEntry;

import java.sql.Timestamp;
import java.util.List;

public interface OrderEntryDataProvider {
	
	OrderEntry getOrderEntryById(Integer orderEntryId);
	
	List<OrderEntry> getOrderEntriesByFood(String foodItem);
	
	List<OrderEntry> getOrderEntriesByTime(Timestamp orderTime);
	
}
