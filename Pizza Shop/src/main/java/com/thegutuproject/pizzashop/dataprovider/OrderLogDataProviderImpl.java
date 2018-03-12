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
	
	// TODO: Add check to ensure only 1 order log is being returned. cancel if multiple
	@Override
	public OrderLog getOrderLogById(Integer orderLogId) {
		OrderLogDbExample orderLogDbExample = orderLogDao.getDbExample();
		orderLogDbExample.createCriteria().andOrderLogIdEqualTo(orderLogId);
		OrderLogDb orderLogDb = orderLogDao.get(orderLogDbExample).get(0);
		OrderLog orderLog = new OrderLog();
		dbBeanMapper.map(orderLogDb, orderLog);
		
		OrderEntryDbExample orderEntryDbExample = orderEntryDao.getDbExample();
		orderEntryDbExample.createCriteria().andOrderLogIdEqualTo(orderLogId);
		List<OrderEntryDb> orderEntryDbList = orderEntryDao.get(orderEntryDbExample);
		List<OrderEntry> orderEntryList = new ArrayList<>();
		dbBeanMapper.map(orderEntryDbList, orderEntryList);
		
		orderLog.setOrderEntryList(orderEntryList);
		return orderLog;
	}
}
