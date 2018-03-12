package com.thegutuproject.pizzashop.dao;

import com.thegutuproject.pizzashop.db.mapper.OrderEntryDbMapper;
import com.thegutuproject.pizzashop.db.model.OrderEntryDb;
import com.thegutuproject.pizzashop.db.model.OrderEntryDbExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderEntryDao {
	
	@Autowired
	private OrderEntryDbMapper orderEntryDbMapper;
	
	public OrderEntryDbMapper getMapper() {
		return orderEntryDbMapper;
	}
	
	public Class<OrderEntryDb> getDbType() {
		return OrderEntryDb.class;
	}
	
	public OrderEntryDbExample getDbExample() {
		return new OrderEntryDbExample();
	}
	
	public List<OrderEntryDb> get(OrderEntryDbExample orderEntryDbExample) {
		List<OrderEntryDb> orderEntryDbList = new ArrayList<>();
		try {
			orderEntryDbList = getMapper().selectByExample(orderEntryDbExample);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error retrieving the Order Entry by example");
		}
		
		if (orderEntryDbList == null || orderEntryDbList.isEmpty()) {
			System.out.println("The Order Entry you are loading was not found in the database");
		}
		
		return orderEntryDbList;
		
	}
	
	public OrderEntryDb get(Integer orderEntryId) {
		OrderEntryDb orderEntryDb = new OrderEntryDb();
		try {
			orderEntryDb = getMapper().selectByPrimaryKey(orderEntryId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error retrieving the Order Entry with ID: " + orderEntryId);
		}
		
		if (orderEntryDb == null) {
			System.out.println("The Order Entry you are looking for was not found in the database");
		}
		
		return orderEntryDb;
	}
	
	public int delete(OrderEntryDbExample orderEntryDbExample) {
		try {
			return getMapper().deleteByExample(orderEntryDbExample);
		} catch (Exception e) {
			System.out.println("Error deleting Order Entry by example");
		}
		
		return 0;
	}
	
	public int delete(Integer orderEntryId) {
		try {
			return getMapper().deleteByPrimaryKey(orderEntryId);
		
		} catch (Exception e) {
			System.out.println("Error deleting the Order Entry with ID: " + orderEntryId);
		}
		
		return 0;
		
	}
	
}
