package com.thegutuproject.pizzashop.resource;

import com.thegutuproject.pizzashop.domain.OrderEntry;
import com.thegutuproject.pizzashop.model.OrderEntryModel;
import com.thegutuproject.pizzashop.service.OrderEntryService;
import io.swagger.annotations.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Api(value="Order Entry", description="API to get Order Entry details")
@RestController
public class OrderEntryResource {
	
	@Autowired
	private DozerBeanMapper dbBeanMapper;
	
	@Autowired
	ConversionService conversionService;
	
	@Autowired
	OrderEntryService orderEntryService;
	
	@RequestMapping(value="/orderentry/{orderEntryId}", method= RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get Order Entries by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad / Invalid input"),
			@ApiResponse(code = 401, message = "Authorization failed"),
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 700, message = "Business rule failure")
	})
	public List<OrderEntryModel> getOrderEntryByOrderEntryId(@PathVariable("orderEntryId") @ApiParam("The Id of the order entry to be retrieved")  final int orderEntryId) {
		// Domain object returned by backend.
		// This still needs to be null checked.
		// Ideas: static generic exception handler
		List<OrderEntry> orderEntry = orderEntryService.getOrderEntryByOrderEntryId(orderEntryId);
		List<OrderEntryModel> orderEntryModel = new ArrayList<>();
		dbBeanMapper.map(orderEntry, orderEntryModel);
		//Return whole model
		return orderEntryModel;
	}
}
