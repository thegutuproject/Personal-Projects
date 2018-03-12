package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.dataprovider.OrderEntryDataProvider;
import com.thegutuproject.pizzashop.domain.OrderEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEntryServiceImpl implements OrderEntryService {
	
	@Autowired
	OrderEntryDataProvider orderEntryDataProvider;
	
	@Override
	public List<OrderEntry> getOrderEntryByOrderEntryId(Integer orderEntryId) {
		return orderEntryDataProvider.getOrderEntryById(orderEntryId);
	}
}
