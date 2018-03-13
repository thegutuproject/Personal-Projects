package com.thegutuproject.pizzashop.domain;

import java.io.Serializable;
import java.util.List;

public class OrderLog implements Serializable {

	private Integer orderLogId;
	private List<OrderEntry> orderEntryList;
	
	public Integer getOrderLogId() {
		return orderLogId;
	}
	
	public void setOrderLogId(Integer orderLogId) {
		this.orderLogId = orderLogId;
	}
	
	public List<OrderEntry> getOrderEntryList() {
		return orderEntryList;
	}

	public void setOrderEntryList(List<OrderEntry> orderEntryList) {
		this.orderEntryList = orderEntryList;
	}
}
