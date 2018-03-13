package com.thegutuproject.pizzashop.dataprovider;

import com.thegutuproject.pizzashop.domain.OrderLog;

public interface OrderLogDataProvider {
	
	OrderLog getOrderLogById(Integer orderLogId);
	
}
