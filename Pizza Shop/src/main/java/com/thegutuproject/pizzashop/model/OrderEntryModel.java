package com.thegutuproject.pizzashop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Model is used to send to the web interface
 * Possible uses might include removing backend specific fields
 */

public class OrderEntryModel implements Serializable {
	
	private String foodItem;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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
