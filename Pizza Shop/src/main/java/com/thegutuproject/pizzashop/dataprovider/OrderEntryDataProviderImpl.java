package com.thegutuproject.pizzashop.dataprovider;


import com.thegutuproject.pizzashop.dao.OrderEntryDao;
import com.thegutuproject.pizzashop.dao.OrderLogDao;
import com.thegutuproject.pizzashop.db.model.OrderEntryDb;
import com.thegutuproject.pizzashop.db.model.OrderEntryDbExample;
import com.thegutuproject.pizzashop.domain.OrderEntry;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * All methods relating to getting Order Entries from DB will be stored here.
 *
 */

@Repository
public class OrderEntryDataProviderImpl implements OrderEntryDataProvider {
	
	@Autowired
	private DozerBeanMapper dbBeanMapper;
	
	@Autowired
	OrderLogDao orderLogDao;
	
	@Autowired
	OrderEntryDao orderEntryDao;
	
	// TODO: Add check to ensure only 1 order log is being returned. cancel if multiple
	@Override
	public OrderEntry getOrderEntryById(Integer orderEntryId) {
		// Using the primary key implementation to return a single order entry object, since each ID is unique
		OrderEntry orderEntry = new OrderEntry();
		OrderEntryDb orderEntryDb = orderEntryDao.get(orderEntryId);
		dbBeanMapper.map(orderEntryDb, orderEntry);
		return orderEntry;
	}
	
	@Override
	public List<OrderEntry> getOrderEntriesByFood(String foodItem) {
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andFoodItemEqualTo(foodItem);
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryDomainList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryDomainList);
		
		return orderEntryDomainList;
	}
	
	@Override
	public List<OrderEntry> getOrderEntriesByTime(Timestamp orderTime) {
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andOrderTimeEqualTo(orderTime);
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryDomainList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryDomainList);
		
		return orderEntryDomainList;
	}
}
