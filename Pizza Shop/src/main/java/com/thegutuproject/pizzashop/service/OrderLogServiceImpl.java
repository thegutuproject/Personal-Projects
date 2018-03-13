package com.thegutuproject.pizzashop.service;

import com.thegutuproject.pizzashop.dataprovider.OrderLogDataProvider;
import com.thegutuproject.pizzashop.domain.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLogServiceImpl implements OrderLogService {
	
	@Autowired
	OrderLogDataProvider orderLogDataProvider;
	
	@Override
	public OrderLog getOrderLogById(Integer orderLogId) {
		return orderLogDataProvider.getOrderLogById(orderLogId);
	}
	
}
