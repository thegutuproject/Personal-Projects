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
import java.util.Date;
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
	
	/**
	 * Returns a single Order Entry object from
	 * the DB due to using selectByPrimaryKey
	 * @param orderEntryId
	 * @return OrderEntry
	 */
	@Override
	public OrderEntry getOrderEntryById(Integer orderEntryId) {
		// Using the primary key implementation to return a single order entry object, since each ID is unique
		OrderEntry orderEntry = new OrderEntry();
		OrderEntryDb orderEntryDb = orderEntryDao.get(orderEntryId);
		dbBeanMapper.map(orderEntryDb, orderEntry);
		return orderEntry;
	}
	
	/**
	 * Returns a list of Order Entry objects from the DB by
	 * selecting all that have matching values for field "FoodItem"
	 * @param foodItem
	 * @return List<OrderEntry>
	 */
	@Override
	public List<OrderEntry> getOrderEntriesByFood(String foodItem) {
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andFoodItemEqualTo(foodItem);
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryDomainList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryDomainList);
		
		return orderEntryDomainList;
	}
	
	/**
	 * Returns a list of Order Entry objects from the DB by
	 * selecting all that have matching values for field "OrderTime"
	 * @param orderTime
	 * @return List<OrderEntry>
	 */
	@Override
	public List<OrderEntry> getOrderEntriesByTime(Date orderTime) {
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andOrderTimeEqualTo(new Timestamp(orderTime.getTime()*1000));
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryDomainList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryDomainList);
		
		return orderEntryDomainList;
	}
	
	/**
	 * Returns a list of Order Entry objects from the DB by
	 * selecting all that have matching values for field "OrderTime"
	 * @param orderLogId
	 * @return List<OrderEntry>
	 */
	@Override
	public List<OrderEntry> getOrderEntriesByOrderLogId(Integer orderLogId) {
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andOrderLogIdEqualTo(orderLogId);
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryDomainList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryDomainList);
		
		return orderEntryDomainList;
	}
	
	/**
	 * Inserts an Order Entry object into the DB and
	 * returns that object upon completion. This also
	 * populates the ID field automatically.
	 * @param foodItem
	 * @param orderTime
	 * @param orderLogId
	 * @return Integer
	 */
	@Override
	public Integer insertOrderEntry(String foodItem, String orderTime, Integer orderLogId) {
		
		Timestamp orderTimeStamp = new Timestamp(new Date(Long.parseLong(orderTime)*1000).getTime());
		
		OrderEntryDb orderEntryDb = new OrderEntryDb();
		orderEntryDb.setFoodItem(foodItem);
		orderEntryDb.setOrderTime(orderTimeStamp);
		orderEntryDb.setOrderLogId(orderLogId);
		
		return orderEntryDao.create(orderEntryDb);
	}
	
}
