package com.thegutuproject.pizzashop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderEntry {

	private Integer orderEntryId;
	private String foodItem;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone="US/Eastern")
	private Date orderTime;
	private Integer orderLogId;
	
	public OrderEntry() {

	}

	public OrderEntry(String foodItem, Date orderTime) {
		this.foodItem = foodItem;
		this.orderTime = orderTime;
	}
	
	public OrderEntry(String foodItem, String orderTime) {
		this.foodItem = foodItem;
		this.orderTime = new Date(Long.parseLong(orderTime) * 1000);
	}
	
	public Integer getOrderEntryId() {
		return orderEntryId;
	}
	
	public void setOrderEntryId(Integer orderEntryId) {
		this.orderEntryId = orderEntryId;
	}
	
	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	public Integer getOrderLogId() {
		return orderLogId;
	}

	public void setOrderLogId(Integer orderLogId) {
		this.orderLogId = orderLogId;
	}
}
