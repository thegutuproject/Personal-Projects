package com.thegutuproject.pizzashop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Model is used to send to the web interface
 * Possible uses might include removing backend specific fields
 */

public class OrderEntryModel implements Serializable {
	
	private String foodItem;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp orderTime;
	
	public OrderEntryModel() {
	
	}
	
	public OrderEntryModel(String foodItem, Timestamp orderTime) {
		this.foodItem = foodItem;
		this.orderTime = orderTime;
	}
	
	public OrderEntryModel(String foodItem, String orderTime) {
		this.foodItem = foodItem;
		this.orderTime = new Timestamp(Long.parseLong(orderTime) * 1000);
	}
	
	public String getFoodItem() {
		return foodItem;
	}
	
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	
	public Timestamp getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
}
