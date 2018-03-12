package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.dataprovider.OrderEntryDataProvider;
import com.thegutuproject.pizzashop.domain.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderEntryServiceImpl implements OrderEntryService {
	
	@Autowired
	OrderEntryDataProvider orderEntryDataProvider;
	
	@Override
	public OrderEntry getOrderEntryById(Integer orderEntryId) {
		return orderEntryDataProvider.getOrderEntryById(orderEntryId);
	}
	
	@Override
	public List<OrderEntry> getOrderEntriesByFood(String foodItem) {
		return orderEntryDataProvider.getOrderEntriesByFood(foodItem);
	}
	
	@Override
	public List<OrderEntry> getOrderEntriesByTime(Timestamp orderTime) {
		return orderEntryDataProvider.getOrderEntriesByTime(orderTime);
	}
}
