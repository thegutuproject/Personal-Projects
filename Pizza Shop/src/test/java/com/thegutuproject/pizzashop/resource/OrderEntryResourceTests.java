package com.thegutuproject.pizzashop.resource;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.service.OrderEntryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(SpringRunner.class)
@WebMvcTest(OrderEntryResource.class)
public class OrderEntryResourceTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderEntryService orderEntryService;
	
	@Autowired
	OrderEntryResource orderEntryResource;
	
	public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public OrderEntry sampleOrderEntry;

	@Before
	public void init() throws Exception {
		
		/**
		 * Manually building the first element in the DB
		 * (controlled environment) to test the REST API
		 */
		
		sampleOrderEntry = new OrderEntry();
		sampleOrderEntry.setOrderEntryId(1);
		sampleOrderEntry.setFoodItem("Pizza");
		sampleOrderEntry.setOrderTime(new Timestamp(simpleDateFormat.parse("2017-09-23 10:24:47").getTime()));
		sampleOrderEntry.setOrderLogId(1);
		
	}

	@Test
	public void testAutowireWorks() {
		Assert.assertNotNull(orderEntryResource);
	}
	
	@Test
	public void testOrderEntryResourceSendsProperObject() throws Exception {
		when(orderEntryService.getOrderEntryById(2)).thenReturn(sampleOrderEntry);
		this.mockMvc.perform(get("/orderentry/id/2"))
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.orderEntryId").value(1))
				.andExpect(jsonPath("$.foodItem").value("Pizza"))
				.andExpect(jsonPath("$.orderTime").value(simpleDateFormat.format(sampleOrderEntry.getOrderTime())))
				.andExpect(jsonPath("$.orderLogId").value(1));
	}
}
