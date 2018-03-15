package com.thegutuproject.pizzashop.resource;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.domain.OrderLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderLogResource.class)
public class OrderLogResourceTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderLogResource orderLogResource;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private OrderLog sampleOrderLog = new OrderLog();
	private OrderEntry sampleListOrderEntry1 = new OrderEntry();
	private OrderEntry sampleListOrderEntry2 = new OrderEntry();
	private OrderEntry sampleListOrderEntry3 = new OrderEntry();
	
	@Before
	public void init() throws Exception {
		
		/**
		 * Manually building the first element in the DB
		 * (controlled environment) to test the REST API
		 */
		List<OrderEntry> sampleOrderEntryList = new ArrayList<>();
		
		sampleListOrderEntry1.setOrderEntryId(1);
		sampleListOrderEntry1.setFoodItem("Pizza");
		sampleListOrderEntry1.setOrderTime(new Timestamp(simpleDateFormat.parse("2017-09-23 10:24:47").getTime()));
		sampleListOrderEntry1.setOrderLogId(1);
		
		sampleListOrderEntry2.setOrderEntryId(2);
		sampleListOrderEntry2.setFoodItem("Cheese");
		sampleListOrderEntry2.setOrderTime(new Timestamp(simpleDateFormat.parse("2016-10-27 10:24:47").getTime()));
		sampleListOrderEntry2.setOrderLogId(1);
		
		sampleListOrderEntry3.setOrderEntryId(3);
		sampleListOrderEntry3.setFoodItem("Bread");
		sampleListOrderEntry3.setOrderTime(new Timestamp(simpleDateFormat.parse("2016-10-26 10:24:47").getTime()));
		sampleListOrderEntry3.setOrderLogId(1);
		
		sampleOrderEntryList.add(sampleListOrderEntry1);
		sampleOrderEntryList.add(sampleListOrderEntry2);
		sampleOrderEntryList.add(sampleListOrderEntry3);
		
		sampleOrderLog.setOrderLogId(1);
		sampleOrderLog.setOrderEntryList(sampleOrderEntryList);
	}
	
	@Test
	public void shouldReturnOrderEntryObject() throws Exception {
		
		System.out.println("TEST RUNNING: shouldReturnOrderEntryObject");
		
		when(orderLogResource.getOrderLogById(1)).thenReturn(sampleOrderLog);
		this.mockMvc.perform(get("/orderlog/id/1"))
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.orderLogId").value(1))
				.andExpect(jsonPath("$.orderEntryList[0].orderEntryId").value(1))
				.andExpect(jsonPath("$.orderEntryList[0].foodItem").value("Pizza"))
				.andExpect(jsonPath("$.orderEntryList[0].orderTime").value(simpleDateFormat.format(sampleListOrderEntry1.getOrderTime())))
				.andExpect(jsonPath("$.orderEntryList[0].orderLogId").value(1))
				.andExpect(jsonPath("$.orderEntryList[1].orderEntryId").value(2))
				.andExpect(jsonPath("$.orderEntryList[1].foodItem").value("Cheese"))
				.andExpect(jsonPath("$.orderEntryList[1].orderTime").value(simpleDateFormat.format(sampleListOrderEntry2.getOrderTime())))
				.andExpect(jsonPath("$.orderEntryList[1].orderLogId").value(1))
				.andExpect(jsonPath("$.orderEntryList[2].orderEntryId").value(3))
				.andExpect(jsonPath("$.orderEntryList[2].foodItem").value("Bread"))
				.andExpect(jsonPath("$.orderEntryList[2].orderTime").value(simpleDateFormat.format(sampleListOrderEntry3.getOrderTime())))
				.andExpect(jsonPath("$.orderEntryList[2].orderLogId").value(1));
	}
}
