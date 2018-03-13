package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.domain.OrderEntry;

import java.util.Date;
import java.util.List;

public interface OrderEntryService {
	
	OrderEntry getOrderEntryById(Integer orderEntryId);
	
	List<OrderEntry> getOrderEntriesByFood(String foodItem);
	
	List<OrderEntry> getOrderEntriesByTime(Date orderTime);
	
	List<OrderEntry> getOrderEntriesByOrderLogId(Integer orderLogId);
	
	Integer insertOrderEntry(String foodItem, String orderTime, Integer orderLogId);
	
}
