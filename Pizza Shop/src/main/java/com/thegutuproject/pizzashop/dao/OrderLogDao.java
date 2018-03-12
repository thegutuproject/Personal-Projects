package com.thegutuproject.pizzashop.dao;

import com.thegutuproject.pizzashop.db.mapper.OrderLogDbMapper;
import com.thegutuproject.pizzashop.db.model.OrderLogDb;
import com.thegutuproject.pizzashop.db.model.OrderLogDbExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderLogDao {
	
	@Autowired
	private OrderLogDbMapper orderLogDbMapper;
	
	public OrderLogDbMapper getMapper() {
		return orderLogDbMapper;
	}
	
	public Class<OrderLogDb> getDbType() {
		return OrderLogDb.class;
	}
	
	public OrderLogDbExample getDbExample() {
		return new OrderLogDbExample();
	}
	
	public List<OrderLogDb> get(OrderLogDbExample orderLogDbExample) {
		try {
			return getMapper().selectByExample(orderLogDbExample);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error retrieving Order Log by example");
		}
		
		return null;
		
	}
	
	public int delete(OrderLogDbExample orderLogDbExample) {
		try {
			return getMapper().deleteByExample(orderLogDbExample);
		} catch (Exception e) {
			System.out.println("Error deleting Order Log by example");
		}
		
		return 0;
	}
	
	public int deleteByPrimaryKey(Integer orderLogId) {
		try {
			return getMapper().deleteByPrimaryKey(orderLogId);
		} catch (Exception e) {
			System.out.println("Error deleting Order Log with ID: " + orderLogId);
		}
		
		return 0;
	}


}
