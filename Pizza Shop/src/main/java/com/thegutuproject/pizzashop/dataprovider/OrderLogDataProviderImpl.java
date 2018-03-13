package com.thegutuproject.pizzashop.dataprovider;

import com.thegutuproject.pizzashop.dao.OrderEntryDao;
import com.thegutuproject.pizzashop.dao.OrderLogDao;
import com.thegutuproject.pizzashop.db.model.OrderEntryDb;
import com.thegutuproject.pizzashop.db.model.OrderEntryDbExample;
import com.thegutuproject.pizzashop.db.model.OrderLogDb;
import com.thegutuproject.pizzashop.db.model.OrderLogDbExample;
import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.domain.OrderLog;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * All methods relating to getting Order Logs from DB will be stored here.
 *
 */

@Repository
public class OrderLogDataProviderImpl implements OrderLogDataProvider {
	
	@Autowired
	private DozerBeanMapper dbBeanMapper;
	
	@Autowired
	OrderLogDao orderLogDao;
	
	@Autowired
	OrderEntryDao orderEntryDao;
	
	/**
	 * Returns a list of Order Log objects from the DB by
	 * selecting all that have matching values for field "OrderLogId"
	 * @param orderLogId
	 * @return
	 */
	@Override
	public OrderLog getOrderLogById(Integer orderLogId) {
		OrderLogDbExample orderLogDbExample = orderLogDao.getDbExample();
		orderLogDbExample.createCriteria().andOrderLogIdEqualTo(orderLogId);
		
		List<OrderLogDb> orderLogDBList = orderLogDao.get(orderLogDbExample);
		
		OrderLog orderLog = new OrderLog();
		
		if (!orderLogDBList.isEmpty()) {
			OrderLogDb orderLogDb =	orderLogDBList.get(0);
			dbBeanMapper.map(orderLogDb, orderLog);
		}
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andOrderLogIdEqualTo(orderLogId);
		
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		
		List<OrderEntry> orderEntryList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryList);
		
		orderLog.setOrderEntryList(orderEntryList);
		
		return orderLog;
	}
}
