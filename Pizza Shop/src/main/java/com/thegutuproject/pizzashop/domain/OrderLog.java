package com.thegutuproject.pizzashop.domain;

import java.util.List;

public class OrderLog {

	private List<OrderEntry> orderEntryList;

	public List<OrderEntry> getOrderEntryList() {
		return orderEntryList;
	}

	public void setOrderEntryList(List<OrderEntry> orderEntryList) {
		this.orderEntryList = orderEntryList;
	}
}
