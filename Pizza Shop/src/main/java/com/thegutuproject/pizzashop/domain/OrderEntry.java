package com.thegutuproject.pizzashop.domain;

import java.util.Date;

public class OrderEntry {

	private String foodItem;
	private Date orderTime;

	public OrderEntry() {

	}

	public OrderEntry(String foodItem, Date orderTime) {
		this.foodItem = foodItem;
		this.orderTime = orderTime;
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