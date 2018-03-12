package com.thegutuproject.pizzashop.domain;

import java.util.List;

public class OrderLog {

	private Integer orderlogId;
	private List<OrderEntry> orderEntryList;
	
	public Integer getOrderlogId() {
		return orderlogId;
	}
	
	public void setOrderlogId(Integer orderlogId) {
		this.orderlogId = orderlogId;
	}
	
	public List<OrderEntry> getOrderEntryList() {
		return orderEntryList;
	}

	public void setOrderEntryList(List<OrderEntry> orderEntryList) {
		this.orderEntryList = orderEntryList;
	}
}
