package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.domain.OrderEntry;

import java.util.List;

public interface OrderEntryService {
	List<OrderEntry> getOrderEntryByOrderEntryId(Integer orderEntryId);
}
