package com.thegutuproject.pizzashop.model;

import java.sql.Date;

/**
 * Model is used to send to the web interface
 * Possible uses might include removing backend specific fields
 */

public class OrderEntryModel {
	
	private String foodItem;
	private Date orderTime;
	
	public OrderEntryModel() {
	
	}
	
	public OrderEntryModel(String foodItem, Date orderTime) {
		this.foodItem = foodItem;
		this.orderTime = orderTime;
	}
	
	public OrderEntryModel(String foodItem, String orderTime) {
		this.foodItem = foodItem;
		this.orderTime = new Date(Long.parseLong(orderTime) * 1000);
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
}
