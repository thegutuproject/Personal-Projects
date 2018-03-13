package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.dataprovider.OrderEntryDataProvider;
import com.thegutuproject.pizzashop.domain.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
	public List<OrderEntry> getOrderEntriesByTime(Date orderTime) {
		return orderEntryDataProvider.getOrderEntriesByTime(orderTime);
	}
	
	@Override
	public List<OrderEntry> getOrderEntriesByOrderLogId(Integer orderLogId) {
		return orderEntryDataProvider.getOrderEntriesByOrderLogId(orderLogId);
	}
	
	@Override
	public Integer insertOrderEntry(String foodItem, String orderTime, Integer orderLogId) {
		return orderEntryDataProvider.insertOrderEntry(foodItem, orderTime, orderLogId);
	}
	
}
