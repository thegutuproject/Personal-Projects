package com.thegutuproject.pizzashop.model;

import java.util.List;

/**
 * Model is used to send to the web interface
 * Possible uses might include removing backend specific fields
 */

public class OrderLogModel {
	
	private List<OrderEntryModel> orderEntryList;
	
	public List<OrderEntryModel> getOrderEntryList() {
		return orderEntryList;
	}
	
	public void setOrderEntryList(List<OrderEntryModel> orderEntryList) {
		this.orderEntryList = orderEntryList;
	}

}
